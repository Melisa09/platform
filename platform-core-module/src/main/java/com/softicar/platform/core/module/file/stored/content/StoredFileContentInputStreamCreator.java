package com.softicar.platform.core.module.file.stored.content;

import com.softicar.platform.common.core.exception.ExceptionsCollector;
import com.softicar.platform.common.io.StreamUtils;
import com.softicar.platform.core.module.file.stored.AGStoredFile;
import com.softicar.platform.core.module.file.stored.chunk.AGStoredFileChunk;
import com.softicar.platform.core.module.file.stored.content.database.IStoredFileDatabase;
import com.softicar.platform.core.module.file.stored.content.database.StoredFileDatabase;
import com.softicar.platform.core.module.file.stored.content.store.IStoredFileContentStore;
import com.softicar.platform.core.module.file.stored.content.store.StoredFileSmbContentStore;
import com.softicar.platform.core.module.file.stored.hash.IStoredFileHash;
import com.softicar.platform.core.module.file.stored.server.AGStoredFileServer;
import com.softicar.platform.core.module.log.LogDb;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Creates an input stream to read the content of an {@link AGStoredFile}.
 * <p>
 * This class first tries to read the file content from one of the content
 * stores. Only if none of the content stores contains the file, the content
 * will be fetched from {@link AGStoredFileChunk}.
 *
 * @author Oliver Richers
 */
public class StoredFileContentInputStreamCreator {

	private final AGStoredFile storedFile;
	private final IStoredFileDatabase database;
	private final Collection<IStoredFileContentStore> contentStores;

	public StoredFileContentInputStreamCreator(AGStoredFile storedFile) {

		this.storedFile = storedFile;
		this.database = new StoredFileDatabase();
		this.contentStores = new ArrayList<>();

		AGStoredFileServer.TABLE//
			.createSelect()
			.where(AGStoredFileServer.ACTIVE)
			.orderBy(AGStoredFileServer.ID)
			.forEach(server -> addContentStore(new StoredFileSmbContentStore(server)));
	}

	public StoredFileContentInputStreamCreator addContentStore(IStoredFileContentStore store) {

		contentStores.add(store);
		return this;
	}

	public InputStream create() {

		IStoredFileHash hash = getFileHash();
		if (hash != null) {
			return readFromSmbStore(hash);
		} else {
			InputStream inputStream = readFromDatabase(storedFile);
			if (inputStream != null) {
				return inputStream;
			} else {
				throw new StoredFileContentNotFoundException(storedFile);
			}
		}
	}

	public byte[] getBytes() {

		InputStream inputStream = create();

		if (inputStream != null) {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			StreamUtils.copy(inputStream, outputStream);
			return outputStream.toByteArray();
		} else {
			return null;
		}
	}

	private IStoredFileHash getFileHash() {

		return database.getFileHash(storedFile);
	}

	private InputStream readFromSmbStore(IStoredFileHash hash) {

		ExceptionsCollector exceptionsCollector = new ExceptionsCollector();
		StoredFileContentName contentName = new StoredFileContentName(hash.getHash());
		for (IStoredFileContentStore store: contentStores) {
			try {
				if (store.exists(contentName.getFullFilename())) {
					return store.readFile(contentName.getFullFilename());
				}
			} catch (Exception exception) {
				exceptionsCollector.add(exception);
			}
		}
		exceptionsCollector.setPreamble("Failed to locate content of stored file %s '%s'", storedFile.getId(), contentName.getFullFilename());
		exceptionsCollector.consumeIfNotEmpty(LogDb::panic);
		throw new StoredFileContentNotFoundException(storedFile);
	}

	private InputStream readFromDatabase(AGStoredFile storedFile) {

		int chunkCount = database.getChunkCount(storedFile);

		if (chunkCount > 0) {
			return database.createChunksInputStream(storedFile);
		} else {
			return null;
		}
	}
}