package com.softicar.platform.emf;

import com.softicar.platform.common.core.user.CurrentBasicUser;
import com.softicar.platform.db.runtime.test.AbstractDbTest;
import com.softicar.platform.dom.elements.testing.engine.IDomTestEngine;
import com.softicar.platform.dom.elements.testing.engine.document.DomDocumentTestEngine;
import com.softicar.platform.emf.test.IEmfTestEngineMethods;
import com.softicar.platform.emf.test.module.EmfTestModuleInstance;
import com.softicar.platform.emf.test.user.EmfTestUser;
import org.junit.Rule;

/**
 * Abstract base class for UI tests with database access.
 *
 * @author Alexander Schmidt
 * @author Oliver Richers
 */
public abstract class AbstractEmfTest extends AbstractDbTest implements IEmfTestEngineMethods {

	@Rule public final IDomTestEngine engine = new DomDocumentTestEngine();

	protected final EmfTestModuleInstance moduleInstance;
	protected final EmfTestUser user;

	public AbstractEmfTest() {

		this.moduleInstance = new EmfTestModuleInstance();
		this.user = EmfTestUser.insert("current", "user");

		CurrentBasicUser.set(user);
	}

	@Override
	public IDomTestEngine getEngine() {

		return engine;
	}
}
