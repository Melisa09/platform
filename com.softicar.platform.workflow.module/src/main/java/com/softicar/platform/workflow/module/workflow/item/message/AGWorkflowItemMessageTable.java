package com.softicar.platform.workflow.module.workflow.item.message;

import com.softicar.platform.db.runtime.object.IDbObjectTableBuilder;
import com.softicar.platform.emf.authorization.IEmfTableRowMapper;
import com.softicar.platform.emf.authorizer.EmfAuthorizer;
import com.softicar.platform.emf.object.table.EmfObjectTable;
import com.softicar.platform.emf.table.configuration.EmfTableConfiguration;
import com.softicar.platform.workflow.module.WorkflowI18n;
import com.softicar.platform.workflow.module.WorkflowImages;
import com.softicar.platform.workflow.module.WorkflowRoles;
import com.softicar.platform.workflow.module.workflow.item.AGWorkflowItem;

public class AGWorkflowItemMessageTable extends EmfObjectTable<AGWorkflowItemMessage, AGWorkflowItem> {

	public AGWorkflowItemMessageTable(IDbObjectTableBuilder<AGWorkflowItemMessage> builder) {

		super(builder);
	}

	@Override
	public void customizeAuthorizer(EmfAuthorizer<AGWorkflowItemMessage, AGWorkflowItem> authorizer) {

		authorizer//
			.setCreationRole(
				WorkflowRoles.OPERATOR.of(IEmfTableRowMapper.nonOptional(WorkflowI18n.WORKFLOW_MODULE_INSTANCE, it -> it.getWorkflow().getModuleInstance())))
			.setViewRole(
				WorkflowRoles.VIEWER
					.of(IEmfTableRowMapper.nonOptional(WorkflowI18n.WORKFLOW_MODULE_INSTANCE, it -> it.getWorkflowItem().getWorkflow().getModuleInstance())))
			.setEditRole(
				WorkflowRoles.OPERATOR
					.of(IEmfTableRowMapper.nonOptional(WorkflowI18n.WORKFLOW_MODULE_INSTANCE, it -> it.getWorkflowItem().getWorkflow().getModuleInstance())));
	}

	@Override
	public void customizeEmfTableConfiguration(EmfTableConfiguration<AGWorkflowItemMessage, Integer, AGWorkflowItem> configuration) {

		configuration.setScopeField(AGWorkflowItemMessage.WORKFLOW_ITEM);
		configuration.setIcon(WorkflowImages.WORKFLOW_ITEM_MESSAGE);
	}
}
