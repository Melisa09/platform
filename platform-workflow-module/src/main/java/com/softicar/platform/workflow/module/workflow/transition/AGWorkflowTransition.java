package com.softicar.platform.workflow.module.workflow.transition;

import com.softicar.platform.common.core.i18n.IDisplayString;
import com.softicar.platform.common.io.resource.IResource;
import com.softicar.platform.core.module.file.stored.StoredFileResource;
import com.softicar.platform.core.module.user.AGUser;
import com.softicar.platform.emf.EmfImages;
import com.softicar.platform.emf.object.IEmfObject;
import com.softicar.platform.emf.source.code.reference.point.EmfSourceCodeReferencePoints;
import com.softicar.platform.workflow.module.workflow.image.AGWorkflowIcon;
import com.softicar.platform.workflow.module.workflow.item.AGWorkflowItem;
import com.softicar.platform.workflow.module.workflow.item.IWorkflowableObject;
import com.softicar.platform.workflow.module.workflow.transition.role.AGWorkflowTransitionRole;
import com.softicar.platform.workflow.module.workflow.transition.side.effect.IWorkflowTransitionSideEffect;
import java.util.List;
import java.util.Optional;

public class AGWorkflowTransition extends AGWorkflowTransitionGenerated implements IEmfObject<AGWorkflowTransition> {

	@Override
	public IDisplayString toDisplayWithoutId() {

		return IDisplayString.create(getName());
	}

	public List<AGWorkflowTransitionRole> getAllActiveWorkflowTransitionRoles() {

		return AGWorkflowTransitionRole.TABLE//
			.createSelect()
			.where(AGWorkflowTransitionRole.ACTIVE)
			.where(AGWorkflowTransitionRole.TRANSITION.equal(this))
			.list();
	}

	public boolean isUserAllowedToSeeTransition(AGUser user, AGWorkflowItem item) {

		return getRoles().stream().anyMatch(role -> role.testUserAssignmentForItem(user, item));
	}

	public List<AGWorkflowTransitionRole> getRoles() {

		return AGWorkflowTransitionRole.createSelect().where(AGWorkflowTransitionRole.TRANSITION.equal(this)).list();
	}

	public IResource getIcon() {

		return Optional//
			.ofNullable(getTransitionIcon())
			.map(AGWorkflowIcon::getIcon)
			.map(StoredFileResource::new)
			.map(it -> (IResource) it)
			.orElse(EmfImages.INPUT_PREVIEW.getResource());
	}

	public void executeSideEffect(AGWorkflowItem workflowItem) {

		if (getSideEffect() != null) {
			IWorkflowTransitionSideEffect<?> sideEffect =
					EmfSourceCodeReferencePoints.getReferencePointOrThrow(getSideEffect().getUuid(), IWorkflowTransitionSideEffect.class);
			executeSideEffect(sideEffect, workflowItem.getEntityOrThrow());
		}
	}

	private <T extends IWorkflowableObject<T>> void executeSideEffect(IWorkflowTransitionSideEffect<T> sideEffect, Object object) {

		sideEffect.execute(sideEffect.getValueClass().cast(object), this);
	}
}