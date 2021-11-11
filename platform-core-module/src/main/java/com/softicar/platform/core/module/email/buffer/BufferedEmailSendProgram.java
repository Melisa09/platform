package com.softicar.platform.core.module.email.buffer;

import com.softicar.platform.common.core.exception.ExceptionsCollector;
import com.softicar.platform.common.core.logging.Log;
import com.softicar.platform.core.module.program.IProgram;
import com.softicar.platform.core.module.server.AGServer;
import com.softicar.platform.emf.source.code.reference.point.EmfSourceCodeReferencePointUuid;

/**
 * Sends e-mails in {@link AGBufferedEmail} and removes old entries.
 *
 * @see BufferedEmailCleaner
 * @see BufferedEmailSender
 * @author Oliver Richers
 */
@EmfSourceCodeReferencePointUuid("0bec6bee-e588-47f7-81cc-ba4dfd9ca720")
public class BufferedEmailSendProgram implements IProgram {

	@Override
	public void executeProgram() {

		Log.finfo("Cleaning emails...");
		new BufferedEmailCleaner().cleanAll();

		Log.finfo("Sending emails...");
		new Sender().sendForAllServers();
	}

	private static class Sender {

		private final ExceptionsCollector collector;

		public Sender() {

			this.collector = new ExceptionsCollector();
		}

		public void sendForAllServers() {

			AGServer.TABLE
				.createSelect()
				.where(AGServer.ACTIVE)//
				.stream()
				.forEach(this::sendEmails);

			collector.throwExceptionIfNotEmpty();
		}

		private void sendEmails(AGServer server) {

			try {
				Log.finfo("Starting email sending for email server '%s'.", server.toDisplayWithoutId());
				new BufferedEmailSender(server).sendAll();
			} catch (Exception exception) {
				collector.add(exception);
			}
		}
	}
}