package com.softicar.platform.workflow.module.workflow.transition;

import com.softicar.platform.db.runtime.object.IDbObjectTableBuilder;
import com.softicar.platform.emf.attribute.IEmfAttributeList;
import com.softicar.platform.emf.attribute.dependency.EmfAttributeDependencyMap;
import com.softicar.platform.emf.authorization.IEmfTableRowMapper;
import com.softicar.platform.emf.authorizer.EmfAuthorizer;
import com.softicar.platform.emf.form.tab.factory.EmfFormTabConfiguration;
import com.softicar.platform.emf.log.EmfChangeLoggerSet;
import com.softicar.platform.emf.object.table.EmfObjectTable;
import com.softicar.platform.emf.predicate.EmfPredicates;
import com.softicar.platform.emf.table.configuration.EmfTableConfiguration;
import com.softicar.platform.workflow.module.WorkflowI18n;
import com.softicar.platform.workflow.module.WorkflowImages;
import com.softicar.platform.workflow.module.WorkflowRoles;
import com.softicar.platform.workflow.module.workflow.WorkflowPredicates;
import com.softicar.platform.workflow.module.workflow.image.AGWorkflowIcon;
import com.softicar.platform.workflow.module.workflow.transition.role.AGWorkflowTransitionRole;
import com.softicar.platform.workflow.module.workflow.transition.side.effect.WorkflowTransitionSideEffects;
import com.softicar.platform.workflow.module.workflow.version.AGWorkflowVersion;

public class AGWorkflowTransitionTable extends EmfObjectTable<AGWorkflowTransition, AGWorkflowVersion> {

	public AGWorkflowTransitionTable(IDbObjectTableBuilder<AGWorkflowTransition> builder) {

		super(builder);
	}

	@Override
	public void customizeAuthorizer(EmfAuthorizer<AGWorkflowTransition, AGWorkflowVersion> authorizer) {

		authorizer//
			.setCreationRole(
				WorkflowRoles.ADMINISTRATOR
					.of(IEmfTableRowMapper.nonOptional(WorkflowI18n.WORKFLOW_MODULE_INSTANCE, it -> it.getWorkflow().getModuleInstance())))
			.setViewRole(
				WorkflowRoles.VIEWER
					.of(IEmfTableRowMapper.nonOptional(WorkflowI18n.WORKFLOW_MODULE_INSTANCE, it -> it.getWorkflowVersion().getWorkflow().getModuleInstance())))
			.setEditRole(
				WorkflowRoles.ADMINISTRATOR
					.of(
						IEmfTableRowMapper
							.nonOptional(WorkflowI18n.WORKFLOW_MODULE_INSTANCE, it -> it.getWorkflowVersion().getWorkflow().getModuleInstance())));
	}

	@Override
	public void customizeEmfTableConfiguration(EmfTableConfiguration<AGWorkflowTransition, Integer, AGWorkflowVersion> configuration) {

		configuration.setScopeField(AGWorkflowTransition.WORKFLOW_VERSION);
		configuration.setCreationPredicate(WorkflowPredicates.WORKFLOW_VERSION_FINALIZED.not());
		configuration.setEditPredicate(WorkflowPredicates.WORKFLOW_VERSION_FINALIZED.of(AGWorkflowTransition.WORKFLOW_VERSION).not());
		configuration.addValidator(WorkflowTransitionValidator::new);
		configuration.setIcon(WorkflowImages.WORKFLOW_TRANSITION);
	}

	@Override
	public void customizeFormTabs(EmfFormTabConfiguration<AGWorkflowTransition> tabConfiguration) {

		tabConfiguration.addManagementTab(AGWorkflowTransitionRole.TABLE, WorkflowI18n.ROLES);
	}

	@Override
	public void customizeAttributeProperties(IEmfAttributeList<AGWorkflowTransition> attributes) {

		attributes//
			.editAttribute(AGWorkflowTransition.NAME)
			.setPredicateMandatory(EmfPredicates.always());
		attributes//
			.editAttribute(AGWorkflowTransition.SOURCE_NODE)
			.setPredicateMandatory(EmfPredicates.always());
		attributes//
			.editAttribute(AGWorkflowTransition.TARGET_NODE)
			.setPredicateMandatory(EmfPredicates.always());
		attributes//
			.editEntityAttribute(AGWorkflowTransition.TRANSITION_ICON)
			.setScope(//
				it -> it.getWorkflowVersion().getWorkflow().getModuleInstance(),
				AGWorkflowIcon::getModuleInstance)
			.setDisplayFactory(AGWorkflowIcon::getImage);
		attributes//
			.editIndirectEntityAttribute(AGWorkflowTransition.SIDE_EFFECT)
			.setEntityLoader(WorkflowTransitionSideEffects::getAllSideEffectsAsIndirectEntities);
		attributes//
			.editAttribute(AGWorkflowTransition.REQUIRED_VOTES)
			.setInputFactoryByEntity(RequiredVotesInput::new);
		attributes//
			.editAttribute(AGWorkflowTransition.NOTIFY)
			.setInputFactoryByEntity(NotifyInput::new);
	}

	@Override
	public void customizeAttributeDependencies(EmfAttributeDependencyMap<AGWorkflowTransition> dependencyMap) {

		dependencyMap//
			.editAttribute(AGWorkflowTransition.AUTO_TRANSITION)
			.addDepender(AGWorkflowTransition.REQUIRED_VOTES)
			.addDepender(AGWorkflowTransition.NOTIFY);
	}

	@Override
	public void customizeLoggers(EmfChangeLoggerSet<AGWorkflowTransition> loggerSet) {

		loggerSet
			.addPlainChangeLogger(AGWorkflowTransitionLog.WORKFLOW_TRANSITION, AGWorkflowTransitionLog.TRANSACTION)//
			.addMapping(AGWorkflowTransition.NAME, AGWorkflowTransitionLog.NAME)
			.addMapping(AGWorkflowTransition.HTML_COLOR, AGWorkflowTransitionLog.HTML_COLOR)
			.addMapping(AGWorkflowTransition.ACTIVE, AGWorkflowTransitionLog.ACTIVE)
			.addMapping(AGWorkflowTransition.NOTIFY, AGWorkflowTransitionLog.NOTIFY)
			.addMapping(AGWorkflowTransition.AUTO_TRANSITION, AGWorkflowTransitionLog.AUTO_TRANSITION)
			.addMapping(AGWorkflowTransition.REQUIRED_VOTES, AGWorkflowTransitionLog.REQUIRED_VOTES)
			.addMapping(AGWorkflowTransition.TRANSITION_ICON, AGWorkflowTransitionLog.TRANSITION_ICON)
			.addMapping(AGWorkflowTransition.SIDE_EFFECT, AGWorkflowTransitionLog.SIDE_EFFECT)
			.addMapping(AGWorkflowTransition.SOURCE_NODE, AGWorkflowTransitionLog.SOURCE_NODE)
			.addMapping(AGWorkflowTransition.TARGET_NODE, AGWorkflowTransitionLog.TARGET_NODE);
	}
}