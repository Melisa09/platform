package com.softicar.platform.core.module.file.stored;

import com.softicar.platform.core.module.CoreImages;
import com.softicar.platform.core.module.CoreRoles;
import com.softicar.platform.core.module.module.instance.system.SystemModuleInstance;
import com.softicar.platform.db.runtime.object.IDbObjectTableBuilder;
import com.softicar.platform.emf.authorization.role.EmfRoles;
import com.softicar.platform.emf.authorizer.EmfAuthorizer;
import com.softicar.platform.emf.object.table.EmfObjectTable;
import com.softicar.platform.emf.table.configuration.EmfTableConfiguration;

public class AGStoredFileLogTable extends EmfObjectTable<AGStoredFileLog, SystemModuleInstance> {

	public AGStoredFileLogTable(IDbObjectTableBuilder<AGStoredFileLog> builder) {

		super(builder);
	}

	@Override
	public void customizeEmfTableConfiguration(EmfTableConfiguration<AGStoredFileLog, Integer, SystemModuleInstance> configuration) {

		configuration.setIcon(CoreImages.STORED_FILE);
	}

	@Override
	public void customizeAuthorizer(EmfAuthorizer<AGStoredFileLog, SystemModuleInstance> authorizer) {

		authorizer//
			.setCreationRole(EmfRoles.nobody())
			.setEditRole(EmfRoles.nobody())
			.setDeleteRole(EmfRoles.nobody())
			.setViewRole(CoreRoles.SUPER_USER.toOtherEntityRole());
	}
}