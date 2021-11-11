package com.softicar.platform.core.module.test;

import com.softicar.platform.core.module.test.fixture.CoreModuleTestFixtureMethods;
import com.softicar.platform.core.module.user.AGUser;
import com.softicar.platform.core.module.user.CurrentUser;
import com.softicar.platform.db.runtime.test.AbstractDbTest;
import com.softicar.platform.dom.elements.testing.engine.IDomTestEngine;
import com.softicar.platform.dom.elements.testing.engine.IDomTestEngineMethods;
import com.softicar.platform.dom.elements.testing.engine.document.DomDocumentTestEngine;
import org.junit.Rule;

public abstract class AbstractCoreTest extends AbstractDbTest implements IDomTestEngineMethods, CoreModuleTestFixtureMethods {

	@Rule public final IDomTestEngine engine = new DomDocumentTestEngine();

	public AbstractCoreTest() {

		CurrentUser.set(insertUserWithoutLogging());
	}

	@Override
	public IDomTestEngine getEngine() {

		return engine;
	}

	// Bootstrapping Problem
	// ---------------------
	// We need to insert the user without triggering the logging,
	// since the logging depends on the current user.
	private AGUser insertUserWithoutLogging() {

		int userId = AGUser.TABLE//
			.createInsert()
			.set(AGUser.LOGIN_NAME, "current.user")
			.set(AGUser.FIRST_NAME, "Current")
			.set(AGUser.LAST_NAME, "User")
			.execute();
		return AGUser.get(userId);
	}
}