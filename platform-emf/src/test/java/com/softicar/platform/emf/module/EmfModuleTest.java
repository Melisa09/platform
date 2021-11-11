package com.softicar.platform.emf.module;

import com.softicar.platform.common.core.annotations.TestingOnly;
import com.softicar.platform.emf.source.code.reference.point.EmfSourceCodeReferencePointUuid;
import com.softicar.platform.emf.test.module.AbstractEmfTestModule;
import java.util.UUID;
import org.junit.Assert;
import org.junit.Test;

public class EmfModuleTest extends Assert {

	private static final String SOME_UUID_STRING = "7bf69cea-cd11-4008-8d4a-36b76c47a091";
	private static final UUID SOME_UUID = UUID.fromString(SOME_UUID_STRING);
	private final TestModule module;

	public EmfModuleTest() {

		this.module = new TestModule();
	}

	@Test
	public void testGetUuid() {

		assertEquals(SOME_UUID, module.getAnnotatedUuid());
	}

	@Test
	public void testGetName() {

		assertEquals("TestModule", module.getClassName());
	}

	@TestingOnly
	@EmfSourceCodeReferencePointUuid(SOME_UUID_STRING)
	private static class TestModule extends AbstractEmfTestModule {

		// nothing to add
	}
}