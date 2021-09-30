package com.softicar.platform.db.runtime.table.statements;

import com.softicar.platform.db.core.statement.DbStatement;
import com.softicar.platform.db.sql.ISqlTable;
import com.softicar.platform.db.sql.statement.base.AbstractSqlUpdate;

public class DbTableUpdate<R> extends AbstractSqlUpdate<R> {

	public DbTableUpdate(ISqlTable<R> table) {

		super(table);
	}

	@Override
	public int execute(int limitCount) {

		// get text and add limit
		StringBuilder textBuilder = getBuilder().getText();
		if (limitCount > 0) {
			textBuilder.append(" LIMIT " + limitCount);
		}

		// execute statement
		return new DbStatement()//
			.addTables(getBuilder().getTables())
			.addText(textBuilder.toString())
			.addParameters(getBuilder().getParameters())
			.executeUpdate();
	}
}
