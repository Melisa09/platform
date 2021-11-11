package com.softicar.platform.emf.test.module;

import com.softicar.platform.emf.module.AbstractEmfModule;
import java.util.Collection;
import java.util.Collections;

public abstract class AbstractEmfTestModule extends AbstractEmfModule<EmfTestModuleInstance> {

	@Override
	public Collection<EmfTestModuleInstance> getActiveModuleInstances() {

		return Collections.singleton(EmfTestModuleInstance.getInstance());
	}

	@Override
	public EmfTestModuleInstance getModuleInstanceById(Integer moduleInstanceId) {

		return EmfTestModuleInstance.getInstance();
	}
}