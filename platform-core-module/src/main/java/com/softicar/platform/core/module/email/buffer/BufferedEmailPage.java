package com.softicar.platform.core.module.email.buffer;

import com.softicar.platform.core.module.CoreI18n;
import com.softicar.platform.core.module.CoreModule;
import com.softicar.platform.core.module.CoreRoles;
import com.softicar.platform.core.module.module.instance.system.SystemModuleInstance;
import com.softicar.platform.emf.management.page.AbstractEmfManagementPage;
import com.softicar.platform.emf.module.role.IEmfModuleRole;
import com.softicar.platform.emf.page.EmfPagePath;
import com.softicar.platform.emf.source.code.reference.point.EmfSourceCodeReferencePointUuid;
import com.softicar.platform.emf.table.IEmfTable;

@EmfSourceCodeReferencePointUuid("11467e4d-9647-43aa-b3c5-7ebd1b18b3b9")
public class BufferedEmailPage extends AbstractEmfManagementPage<SystemModuleInstance> {

	@Override
	public Class<CoreModule> getModuleClass() {

		return CoreModule.class;
	}

	@Override
	public EmfPagePath getPagePath(EmfPagePath modulePath) {

		return modulePath.append(CoreI18n.EMAIL);
	}

	@Override
	protected IEmfTable<?, ?, SystemModuleInstance> getTable() {

		return AGBufferedEmail.TABLE;
	}

	@Override
	public IEmfModuleRole<SystemModuleInstance> getAuthorizedRole() {

		return CoreRoles.SUPER_USER;
	}
}