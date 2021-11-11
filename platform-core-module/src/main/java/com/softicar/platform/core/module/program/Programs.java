package com.softicar.platform.core.module.program;

import com.softicar.platform.common.core.i18n.IDisplayString;
import com.softicar.platform.core.module.uuid.AGUuidBasedSourceCodeReferencePoint;
import com.softicar.platform.core.module.uuid.AGUuidBasedSourceCodeReferencePoints;
import com.softicar.platform.emf.source.code.reference.point.EmfSourceCodeReferencePoints;
import java.util.Collection;
import java.util.UUID;

public class Programs {

	public static IDisplayString getProgramName(UUID uuid) {

		return EmfSourceCodeReferencePoints.getReferencePointOrThrow(uuid).toDisplay();
	}

	public static Collection<AGUuidBasedSourceCodeReferencePoint> getAllProgramsAsIndirectEntities() {

		return AGUuidBasedSourceCodeReferencePoints.getAll(IProgram.class);
	}

	public static <P extends IProgram> void enqueueExecution(Class<P> programClass) {

		new ProgramEnqueuer<>(programClass).enqueue();
	}
}