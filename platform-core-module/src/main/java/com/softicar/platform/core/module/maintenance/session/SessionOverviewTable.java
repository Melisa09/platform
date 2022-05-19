package com.softicar.platform.core.module.maintenance.session;

import com.softicar.platform.common.container.data.table.in.memory.AbstractInMemoryDataTable;
import com.softicar.platform.common.date.DayTime;
import com.softicar.platform.core.module.CoreI18n;
import com.softicar.platform.core.module.ajax.session.SofticarAjaxSession;
import com.softicar.platform.core.module.user.AGUser;
import java.util.Date;
import javax.servlet.http.HttpSession;

public class SessionOverviewTable extends AbstractInMemoryDataTable<HttpSession> {

	public SessionOverviewTable() {

		newColumn(AGUser.class)//
			.setGetter(this::getUserFromSession)
			.setTitle(CoreI18n.USER)
			.addColumn();
		newColumn(String.class)//
			.setGetter(HttpSession::getId)
			.setTitle(CoreI18n.SESSION_ID)
			.addColumn();
		newColumn(DayTime.class)//
			.setGetter(session -> convertToDayTime(session.getCreationTime()))
			.setTitle(CoreI18n.CREATED_AT)
			.addColumn();
		newColumn(DayTime.class)//
			.setGetter(session -> convertToDayTime(session.getLastAccessedTime()))
			.setTitle(CoreI18n.LAST_ACCESS)
			.addColumn();
	}

	@Override
	protected Iterable<HttpSession> getTableRows() {

		return new SessionManager().getAllSessions();
	}

	private AGUser getUserFromSession(HttpSession session) {

		return SofticarAjaxSession//
			.getInstance(session)
			.map(SofticarAjaxSession::getUser)
			.orElse(null);
	}

	private DayTime convertToDayTime(long time) {

		return DayTime.fromDate(new Date(time));
	}

}