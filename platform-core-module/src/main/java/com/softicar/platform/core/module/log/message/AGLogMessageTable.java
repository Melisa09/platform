package com.softicar.platform.core.module.log.message;

import com.softicar.platform.common.container.comparator.OrderDirection;
import com.softicar.platform.core.module.CoreRoles;
import com.softicar.platform.core.module.module.instance.system.SystemModuleInstance;
import com.softicar.platform.db.runtime.object.IDbObjectTableBuilder;
import com.softicar.platform.emf.EmfImages;
import com.softicar.platform.emf.authorization.role.EmfRoles;
import com.softicar.platform.emf.authorizer.EmfAuthorizer;
import com.softicar.platform.emf.management.EmfManagementConfiguration;
import com.softicar.platform.emf.object.table.EmfObjectTable;
import com.softicar.platform.emf.table.configuration.EmfTableConfiguration;

public class AGLogMessageTable extends EmfObjectTable<AGLogMessage, SystemModuleInstance> {

	public AGLogMessageTable(IDbObjectTableBuilder<AGLogMessage> builder) {

		super(builder);
	}

	@Override
	public void customizeEmfTableConfiguration(EmfTableConfiguration<AGLogMessage, Integer, SystemModuleInstance> configuration) {

		configuration.setIcon(EmfImages.ENTITY_LOG);
	}

	@Override
	public void customizeManagementConfiguraton(EmfManagementConfiguration<AGLogMessage> configuration) {

		configuration.addOrderBy(AGLogMessage.ID, OrderDirection.DESCENDING);
	}

	@Override
	public void customizeAuthorizer(EmfAuthorizer<AGLogMessage, SystemModuleInstance> authorizer) {

		authorizer.setViewRole(CoreRoles.SUPER_USER.toOtherEntityRole());
		authorizer.setEditRole(EmfRoles.nobody());
		authorizer.setCreationRole(EmfRoles.nobody());
	}
}