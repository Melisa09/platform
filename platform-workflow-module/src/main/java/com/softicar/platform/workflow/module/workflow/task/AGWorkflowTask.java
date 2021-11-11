package com.softicar.platform.workflow.module.workflow.task;

import com.softicar.platform.common.core.i18n.IDisplayString;
import com.softicar.platform.core.module.user.AGUser;
import com.softicar.platform.emf.object.IEmfObject;
import com.softicar.platform.workflow.module.workflow.item.AGWorkflowItem;
import com.softicar.platform.workflow.module.workflow.substitute.AGWorkflowSubstitute;
import com.softicar.platform.workflow.module.workflow.task.delegation.AGWorkflowTaskDelegation;
import com.softicar.platform.workflow.module.workflow.transition.execution.AGWorkflowTransitionExecution;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

public class AGWorkflowTask extends AGWorkflowTaskGenerated implements IEmfObject<AGWorkflowTask> {

	public static final CreationTransactionField<AGWorkflowTask, AGWorkflowTaskLog> CREATION_TRANSACTION =
			new CreationTransactionField<>(AGWorkflowTaskLog.WORKFLOW_TASK, AGWorkflowTaskLog.TRANSACTION);

	@Override
	public IDisplayString toDisplayWithoutId() {

		return IDisplayString
			.create(
				getWorkflowItem().getWorkflow().getTableReferencePointOrThrow().getTable().getFullName().getSimpleName() // 
						+ " " //
						+ getWorkflowItem().getEntityOrThrow().toDisplay().toString() + // 
						" :: " // 
						+ getWorkflowItem().getWorkflowNode().getName());
	}

	public void close() {

		setClosed(true);
		save();

		Optional//
			.ofNullable(AGWorkflowTaskDelegation.TABLE.load(this))
			.ifPresent(it -> it.setActive(false).save());
	}

	public static Collection<AGWorkflowTask> getAllWorkflowTasksAndDelegationTasksAndSubstituteTasksToCloseForUserAndItem(AGUser user,
			AGWorkflowItem workflowItem) {

		HashSet<AGWorkflowTask> tasksToClose = new HashSet<>();

		tasksToClose.addAll(getAllWorkflowTasksAndDelegationTasksToCloseForUserAndItem(user, workflowItem));
		AGWorkflowSubstitute.loadAllActiveForUser(user).forEach(it -> {
			tasksToClose.addAll(getAllWorkflowTasksAndDelegationTasksToCloseForUserAndItem(it.getUser(), workflowItem));
		});

		return tasksToClose;

	}

	public static Collection<AGWorkflowTask> getAllWorkflowTasksAndDelegationTasksToCloseForUserAndItem(AGUser user, AGWorkflowItem workflowItem) {

		HashSet<AGWorkflowTask> tasksToClose = new HashSet<>();
		AGWorkflowTask
			.createSelect()
			.where(AGWorkflowTask.CLOSED.isFalse())
			.where(AGWorkflowTask.USER.equal(user))
			.where(AGWorkflowTask.WORKFLOW_ITEM.equal(workflowItem))
			.forEach(tasksToClose::add);

		AGWorkflowTask.TABLE
			.createSelect()
			.where(AGWorkflowTask.CLOSED.isFalse())
			.where(AGWorkflowTask.WORKFLOW_ITEM.equal(workflowItem))
			.joinReverse(AGWorkflowTaskDelegation.WORKFLOW_TASK)
			.where(AGWorkflowTaskDelegation.ACTIVE)
			.where(AGWorkflowTaskDelegation.TARGET_USER.equal(user))
			.forEach(tasksToClose::add);

		return tasksToClose;
	}

	public boolean wasNotExecuted() {

		return !AGWorkflowTransitionExecution//
			.createSelect()
			.where(AGWorkflowTransitionExecution.WORKFLOW_TASK.equal(this))
			.exists();
	}
}