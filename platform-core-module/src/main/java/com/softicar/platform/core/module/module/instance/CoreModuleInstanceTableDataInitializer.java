package com.softicar.platform.core.module.module.instance;

import com.softicar.platform.common.date.DayTime;
import com.softicar.platform.core.module.CoreModule;
import com.softicar.platform.core.module.access.module.instance.AGModuleInstance;
import com.softicar.platform.core.module.language.AGCoreLanguageEnum;
import com.softicar.platform.core.module.localization.AGLocalization;
import com.softicar.platform.core.module.server.AGServer;
import com.softicar.platform.core.module.transaction.AGTransaction;
import com.softicar.platform.core.module.user.AGUser;
import com.softicar.platform.core.module.uuid.AGUuid;
import com.softicar.platform.db.runtime.table.configuration.IDbTableDataInitializer;

/**
 * Initializes the singleton instance of the {@link AGCoreModuleInstance}.
 * <p>
 * A system user is also created and configured.
 *
 * @author Oliver Richers
 */
public class CoreModuleInstanceTableDataInitializer implements IDbTableDataInitializer {

	@Override
	public void initializeData() {

		var localizationPreset = insertLocalizationPreset();
		var systemUser = insertSystemUser(localizationPreset);
		var emailServer = insertEmailServer();

		AGModuleInstance.TABLE//
			.createInsert()
			.set(AGModuleInstance.ID, AGCoreModuleInstance.SINGLETON_INSTANCE_ID)
			.set(AGModuleInstance.TRANSACTION, insertTransaction(systemUser))
			.set(AGModuleInstance.ACTIVE, true)
			.set(AGModuleInstance.MODULE_UUID, AGUuid.getOrCreate(CoreModule.class))
			.execute();
		AGCoreModuleInstance.TABLE//
			.createInsert()
			.set(AGCoreModuleInstance.MODULE_INSTANCE, AGModuleInstance.TABLE.getStub(AGCoreModuleInstance.SINGLETON_INSTANCE_ID))
			.set(AGCoreModuleInstance.DEFAULT_LOCALIZATION, localizationPreset)
			.set(AGCoreModuleInstance.SYSTEM_USER, systemUser)
			.set(AGCoreModuleInstance.EMAIL_SERVER, emailServer)
			.executeWithoutIdGeneration();
	}

	private AGLocalization insertLocalizationPreset() {

		var id = AGLocalization.TABLE//
			.createInsert()
			.set(AGLocalization.NAME, "[DEFAULT]")
			.set(AGLocalization.LANGUAGE, AGCoreLanguageEnum.ENGLISH.getRecord())
			.set(AGLocalization.DECIMAL_SEPARATOR, ".")
			.set(AGLocalization.DIGIT_GROUP_SEPARATOR, "")
			.execute();
		return AGLocalization.TABLE.getStub(id);
	}

	private AGUser insertSystemUser(AGLocalization localizationPreset) {

		var id = AGUser.TABLE//
			.createInsert()
			.set(AGUser.LOGIN_NAME, "system.user")
			.set(AGUser.FIRST_NAME, "System")
			.set(AGUser.LAST_NAME, "User")
			.set(AGUser.EMAIL_ADDRESS, "system.user@example.com")
			.set(AGUser.LOCALIZATION, localizationPreset)
			.set(AGUser.SYSTEM_USER, true)
			.execute();
		return AGUser.TABLE.getStub(id);
	}

	private AGServer insertEmailServer() {

		var id = AGServer.TABLE//
			.createInsert()
			.set(AGServer.NAME, "default")
			.set(AGServer.ADDRESS, "0.0.0.0")
			.set(AGServer.PORT, 0)
			.set(AGServer.USERNAME, "")
			.set(AGServer.PASSWORD, "")
			.execute();
		return AGServer.TABLE.getStub(id);
	}

	private AGTransaction insertTransaction(AGUser user) {

		var id = AGTransaction.TABLE//
			.createInsert()
			.set(AGTransaction.AT, DayTime.now())
			.set(AGTransaction.BY, user)
			.execute();
		return AGTransaction.TABLE.getStub(id);
	}
}
