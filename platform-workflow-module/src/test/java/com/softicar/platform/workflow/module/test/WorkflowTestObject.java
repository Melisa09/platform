package com.softicar.platform.workflow.module.test;

import com.softicar.platform.common.core.i18n.IDisplayString;
import com.softicar.platform.common.core.user.IBasicUser;
import com.softicar.platform.core.module.user.AGUser;
import com.softicar.platform.db.runtime.field.IDbForeignField;
import com.softicar.platform.db.runtime.field.IDbIdField;
import com.softicar.platform.db.runtime.field.IDbStringField;
import com.softicar.platform.db.runtime.logic.AbstractDbObject;
import com.softicar.platform.db.runtime.object.DbObjectTableBuilder;
import com.softicar.platform.workflow.module.workflow.item.AGWorkflowItem;
import com.softicar.platform.workflow.module.workflow.item.IWorkflowableObject;

public class WorkflowTestObject extends AbstractDbObject<WorkflowTestObject> implements IWorkflowableObject<WorkflowTestObject> {

	// @formatter:off
	public static final DbObjectTableBuilder<WorkflowTestObject, WorkflowTestObject> BUILDER = new DbObjectTableBuilder<>("Workflow", "TestObject", WorkflowTestObject::new, WorkflowTestObject.class);
	static {
		BUILDER.setTitle(IDisplayString.create("Test Object"));
		BUILDER.setTitle(IDisplayString.create("Test Objects"));
	}
	public static final IDbIdField<WorkflowTestObject> ID = BUILDER.addIdField("id", o -> o.id, (o, v) -> o.id = v);
	public static final IDbStringField<WorkflowTestObject> NAME = BUILDER.addStringField("name", o -> o.name, (o, v) -> o.name = v);
	public static final IDbForeignField<WorkflowTestObject, AGWorkflowItem> ITEM = BUILDER.addForeignField("item", o -> o.item, (o, v) -> o.item = v,AGWorkflowItem.ID).setNullable();
	public static final WorkflowTestObjectTable TABLE = new WorkflowTestObjectTable(BUILDER);
	// @formatter:on

	private Integer id;
	private String name;
	private AGWorkflowItem item;

	@Override
	public WorkflowTestObjectTable table() {

		return TABLE;
	}

	public String getName() {

		return getValue(NAME);
	}

	public WorkflowTestObject setName(String name) {

		return setValue(NAME, name);
	}

	@Override
	public IDisplayString toDisplayWithoutId() {

		return IDisplayString.create(name);
	}

	@Override
	public Boolean isActive() {

		return true;
	}

	@Override
	public AGWorkflowItem getWorkflowItem() {

		return getValue(ITEM);
	}

	@Override
	public WorkflowTestObject setWorkflowItem(AGWorkflowItem item) {

		return setValue(ITEM, item);
	}

	public void addRoleMember(IBasicUser user, String role) {

		new WorkflowTestObjectRoleMember()//
			.setRole(role)
			.setObject(this)
			.setUser(AGUser.get(user))
			.save();
	}

	public boolean isRoleMember(IBasicUser user, String role) {

		return WorkflowTestObjectRoleMember.TABLE//
			.createSelect()
			.where(WorkflowTestObjectRoleMember.ROLE.isEqual(role))
			.where(WorkflowTestObjectRoleMember.OBJECT.isEqual(this))
			.where(WorkflowTestObjectRoleMember.USER.isEqual(AGUser.get(user)))
			.exists();
	}
}