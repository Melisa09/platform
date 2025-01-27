package com.softicar.platform.core.module;

import com.softicar.platform.common.container.tuple.Tuple2;
import com.softicar.platform.common.core.annotations.Generated;
import com.softicar.platform.core.module.file.stored.AGStoredFile;
import com.softicar.platform.core.module.file.stored.repository.AGStoredFileRepository;
import com.softicar.platform.core.module.localization.AGLocalization;
import com.softicar.platform.core.module.module.instance.AGModuleInstanceBase;
import com.softicar.platform.core.module.server.AGServer;
import com.softicar.platform.core.module.transaction.AGTransaction;
import com.softicar.platform.core.module.user.AGUser;
import com.softicar.platform.db.runtime.field.IDbBooleanField;
import com.softicar.platform.db.runtime.field.IDbForeignField;
import com.softicar.platform.db.runtime.field.IDbForeignRowField;
import com.softicar.platform.db.runtime.field.IDbStringField;
import com.softicar.platform.db.runtime.key.DbTableKeyFactory;
import com.softicar.platform.db.runtime.key.IDbKey;
import com.softicar.platform.db.runtime.key.IDbTableKey;
import com.softicar.platform.db.runtime.record.AbstractDbRecord;
import com.softicar.platform.db.runtime.record.DbRecordTable;
import com.softicar.platform.db.runtime.table.DbTableBuilder;

/**
 * This is the automatically generated class AGCoreModuleInstanceLog for
 * database table <i>Core.CoreModuleInstanceLog</i>.
 */
@Generated
@SuppressWarnings("all")
public class AGCoreModuleInstanceLog extends AbstractDbRecord<AGCoreModuleInstanceLog, Tuple2<AGCoreModuleInstance, AGTransaction>> {

	// -------------------------------- STATIC CONSTANTS -------------------------------- //

	// @formatter:off
	private static final DbTableBuilder<AGCoreModuleInstanceLog, AGCoreModuleInstanceLog, Tuple2<AGCoreModuleInstance, AGTransaction>> BUILDER = new DbTableBuilder<>("Core", "CoreModuleInstanceLog", AGCoreModuleInstanceLog::new, AGCoreModuleInstanceLog.class);
	static {
		BUILDER.setTitle(CoreI18n.CORE_MODULE_INSTANCE_LOG);
		BUILDER.setPluralTitle(CoreI18n.CORE_MODULE_INSTANCE_LOGS);
	}

	public static final IDbForeignRowField<AGCoreModuleInstanceLog, AGCoreModuleInstance, AGModuleInstanceBase> CORE_MODULE_INSTANCE = BUILDER.addForeignRowField("coreModuleInstance", o->o.m_coreModuleInstance, (o,v)->o.m_coreModuleInstance=v, AGCoreModuleInstance.BASE).setTitle(CoreI18n.CORE_MODULE_INSTANCE).setCascade(true, true).setForeignKeyName("CoreModuleInstanceLog_ibfk_1");
	public static final IDbForeignField<AGCoreModuleInstanceLog, AGTransaction> TRANSACTION = BUILDER.addForeignField("transaction", o->o.m_transaction, (o,v)->o.m_transaction=v, AGTransaction.ID).setTitle(CoreI18n.TRANSACTION).setCascade(true, true).setForeignKeyName("CoreModuleInstanceLog_ibfk_2");
	public static final IDbForeignField<AGCoreModuleInstanceLog, AGUser> SYSTEM_USER = BUILDER.addForeignField("systemUser", o->o.m_systemUser, (o,v)->o.m_systemUser=v, AGUser.ID).setTitle(CoreI18n.SYSTEM_USER).setNullable().setDefault(null).setForeignKeyName("CoreModuleInstanceLog_ibfk_3");
	public static final IDbForeignField<AGCoreModuleInstanceLog, AGStoredFileRepository> PRIMARY_FILE_REPOSITORY = BUILDER.addForeignField("primaryFileRepository", o->o.m_primaryFileRepository, (o,v)->o.m_primaryFileRepository=v, AGStoredFileRepository.ID).setTitle(CoreI18n.PRIMARY_FILE_REPOSITORY).setNullable().setDefault(null).setForeignKeyName("CoreModuleInstanceLog_ibfk_4");
	public static final IDbForeignField<AGCoreModuleInstanceLog, AGServer> EMAIL_SERVER = BUILDER.addForeignField("emailServer", o->o.m_emailServer, (o,v)->o.m_emailServer=v, AGServer.ID).setTitle(CoreI18n.EMAIL_SERVER).setNullable().setDefault(null).setForeignKeyName("CoreModuleInstanceLog_ibfk_5");
	public static final IDbStringField<AGCoreModuleInstanceLog> SUPPORT_EMAIL_ADDRESS = BUILDER.addStringField("supportEmailAddress", o->o.m_supportEmailAddress, (o,v)->o.m_supportEmailAddress=v).setTitle(CoreI18n.SUPPORT_EMAIL_ADDRESS).setNullable().setDefault(null).setMaximumLength(255);
	public static final IDbStringField<AGCoreModuleInstanceLog> NO_REPLY_EMAIL_ADDRESS = BUILDER.addStringField("noReplyEmailAddress", o->o.m_noReplyEmailAddress, (o,v)->o.m_noReplyEmailAddress=v).setTitle(CoreI18n.NO_REPLY_EMAIL_ADDRESS).setNullable().setDefault(null).setMaximumLength(255);
	public static final IDbStringField<AGCoreModuleInstanceLog> PORTAL_PROTOCOL = BUILDER.addStringField("portalProtocol", o->o.m_portalProtocol, (o,v)->o.m_portalProtocol=v).setTitle(CoreI18n.PORTAL_PROTOCOL).setNullable().setDefault(null).setMaximumLength(255);
	public static final IDbStringField<AGCoreModuleInstanceLog> PORTAL_HOST = BUILDER.addStringField("portalHost", o->o.m_portalHost, (o,v)->o.m_portalHost=v).setTitle(CoreI18n.PORTAL_HOST).setNullable().setDefault(null).setMaximumLength(255);
	public static final IDbStringField<AGCoreModuleInstanceLog> PORTAL_APPLICATION = BUILDER.addStringField("portalApplication", o->o.m_portalApplication, (o,v)->o.m_portalApplication=v).setTitle(CoreI18n.PORTAL_APPLICATION).setNullable().setDefault(null).setMaximumLength(255);
	public static final IDbStringField<AGCoreModuleInstanceLog> SYSTEM_NAME = BUILDER.addStringField("systemName", o->o.m_systemName, (o,v)->o.m_systemName=v).setTitle(CoreI18n.SYSTEM_NAME).setNullable().setDefault(null).setMaximumLength(255);
	public static final IDbForeignField<AGCoreModuleInstanceLog, AGStoredFile> PORTAL_LOGO = BUILDER.addForeignField("portalLogo", o->o.m_portalLogo, (o,v)->o.m_portalLogo=v, AGStoredFile.ID).setTitle(CoreI18n.PORTAL_LOGO).setNullable().setDefault(null).setForeignKeyName("CoreModuleInstanceLog_ibfk_6");
	public static final IDbForeignField<AGCoreModuleInstanceLog, AGLocalization> DEFAULT_LOCALIZATION = BUILDER.addForeignField("defaultLocalization", o->o.m_defaultLocalization, (o,v)->o.m_defaultLocalization=v, AGLocalization.ID).setTitle(CoreI18n.DEFAULT_LOCALIZATION).setNullable().setDefault(null).setForeignKeyName("CoreModuleInstanceLog_ibfk_7");
	public static final IDbBooleanField<AGCoreModuleInstanceLog> TEST_SYSTEM = BUILDER.addBooleanField("testSystem", o->o.m_testSystem, (o,v)->o.m_testSystem=v).setTitle(CoreI18n.TEST_SYSTEM).setNullable().setDefault(null);
	public static final IDbTableKey<AGCoreModuleInstanceLog, Tuple2<AGCoreModuleInstance, AGTransaction>> PRIMARY_KEY = BUILDER.setPrimaryKey(DbTableKeyFactory.createKey(CORE_MODULE_INSTANCE, TRANSACTION));
	public static final IDbKey<AGCoreModuleInstanceLog> IK_TRANSACTION = BUILDER.addIndexKey("transaction", TRANSACTION);
	public static final IDbKey<AGCoreModuleInstanceLog> IK_PRIMARY_FILE_REPOSITORY = BUILDER.addIndexKey("primaryFileRepository", PRIMARY_FILE_REPOSITORY);
	public static final IDbKey<AGCoreModuleInstanceLog> IK_SYSTEM_USER = BUILDER.addIndexKey("systemUser", SYSTEM_USER);
	public static final IDbKey<AGCoreModuleInstanceLog> IK_EMAIL_SERVER = BUILDER.addIndexKey("emailServer", EMAIL_SERVER);
	public static final IDbKey<AGCoreModuleInstanceLog> IK_PORTAL_LOGO = BUILDER.addIndexKey("portalLogo", PORTAL_LOGO);
	public static final IDbKey<AGCoreModuleInstanceLog> IK_DEFAULT_LOCALIZATION = BUILDER.addIndexKey("defaultLocalization", DEFAULT_LOCALIZATION);
	public static final DbRecordTable<AGCoreModuleInstanceLog, Tuple2<AGCoreModuleInstance, AGTransaction>> TABLE = new DbRecordTable<>(BUILDER);
	// @formatter:on

	// -------------------------------- CONSTRUCTORS -------------------------------- //

	protected AGCoreModuleInstanceLog() {

		// protected
	}

	// -------------------------------- GETTERS AND SETTERS -------------------------------- //

	public final AGCoreModuleInstance getCoreModuleInstance() {

		return getValue(CORE_MODULE_INSTANCE);
	}

	public final Integer getTransactionID() {

		return getValueId(TRANSACTION);
	}

	public final AGTransaction getTransaction() {

		return getValue(TRANSACTION);
	}

	public final Integer getSystemUserID() {

		return getValueId(SYSTEM_USER);
	}

	public final AGUser getSystemUser() {

		return getValue(SYSTEM_USER);
	}

	public final AGCoreModuleInstanceLog setSystemUser(AGUser value) {

		return setValue(SYSTEM_USER, value);
	}

	public final Integer getPrimaryFileRepositoryID() {

		return getValueId(PRIMARY_FILE_REPOSITORY);
	}

	public final AGStoredFileRepository getPrimaryFileRepository() {

		return getValue(PRIMARY_FILE_REPOSITORY);
	}

	public final AGCoreModuleInstanceLog setPrimaryFileRepository(AGStoredFileRepository value) {

		return setValue(PRIMARY_FILE_REPOSITORY, value);
	}

	public final Integer getEmailServerID() {

		return getValueId(EMAIL_SERVER);
	}

	public final AGServer getEmailServer() {

		return getValue(EMAIL_SERVER);
	}

	public final AGCoreModuleInstanceLog setEmailServer(AGServer value) {

		return setValue(EMAIL_SERVER, value);
	}

	public final String getSupportEmailAddress() {

		return getValue(SUPPORT_EMAIL_ADDRESS);
	}

	public final AGCoreModuleInstanceLog setSupportEmailAddress(String value) {

		return setValue(SUPPORT_EMAIL_ADDRESS, value);
	}

	public final String getNoReplyEmailAddress() {

		return getValue(NO_REPLY_EMAIL_ADDRESS);
	}

	public final AGCoreModuleInstanceLog setNoReplyEmailAddress(String value) {

		return setValue(NO_REPLY_EMAIL_ADDRESS, value);
	}

	public final String getPortalProtocol() {

		return getValue(PORTAL_PROTOCOL);
	}

	public final AGCoreModuleInstanceLog setPortalProtocol(String value) {

		return setValue(PORTAL_PROTOCOL, value);
	}

	public final String getPortalHost() {

		return getValue(PORTAL_HOST);
	}

	public final AGCoreModuleInstanceLog setPortalHost(String value) {

		return setValue(PORTAL_HOST, value);
	}

	public final String getPortalApplication() {

		return getValue(PORTAL_APPLICATION);
	}

	public final AGCoreModuleInstanceLog setPortalApplication(String value) {

		return setValue(PORTAL_APPLICATION, value);
	}

	public final String getSystemName() {

		return getValue(SYSTEM_NAME);
	}

	public final AGCoreModuleInstanceLog setSystemName(String value) {

		return setValue(SYSTEM_NAME, value);
	}

	public final Integer getPortalLogoID() {

		return getValueId(PORTAL_LOGO);
	}

	public final AGStoredFile getPortalLogo() {

		return getValue(PORTAL_LOGO);
	}

	public final AGCoreModuleInstanceLog setPortalLogo(AGStoredFile value) {

		return setValue(PORTAL_LOGO, value);
	}

	public final Integer getDefaultLocalizationID() {

		return getValueId(DEFAULT_LOCALIZATION);
	}

	public final AGLocalization getDefaultLocalization() {

		return getValue(DEFAULT_LOCALIZATION);
	}

	public final AGCoreModuleInstanceLog setDefaultLocalization(AGLocalization value) {

		return setValue(DEFAULT_LOCALIZATION, value);
	}

	public final Boolean isTestSystem() {

		return getValue(TEST_SYSTEM);
	}

	public final AGCoreModuleInstanceLog setTestSystem(Boolean value) {

		return setValue(TEST_SYSTEM, value);
	}

	// -------------------------------- UTILS -------------------------------- //

	@Override
	public final DbRecordTable<AGCoreModuleInstanceLog, Tuple2<AGCoreModuleInstance, AGTransaction>> table() {

		return TABLE;
	}

	// -------------------------------- FIELD MEMBERS -------------------------------- //

	private AGCoreModuleInstance m_coreModuleInstance;
	private AGTransaction m_transaction;
	private AGUser m_systemUser;
	private AGStoredFileRepository m_primaryFileRepository;
	private AGServer m_emailServer;
	private String m_supportEmailAddress;
	private String m_noReplyEmailAddress;
	private String m_portalProtocol;
	private String m_portalHost;
	private String m_portalApplication;
	private String m_systemName;
	private AGStoredFile m_portalLogo;
	private AGLocalization m_defaultLocalization;
	private Boolean m_testSystem;
}

