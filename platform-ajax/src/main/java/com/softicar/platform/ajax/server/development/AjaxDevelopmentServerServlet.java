package com.softicar.platform.ajax.server.development;

import com.softicar.platform.ajax.framework.AjaxFramework;
import com.softicar.platform.common.core.utils.ReflectionUtils;
import com.softicar.platform.dom.node.IDomNode;
import java.util.function.Supplier;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class AjaxDevelopmentServerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final AjaxFramework ajaxFramework;

	public AjaxDevelopmentServerServlet(Class<? extends IDomNode> nodeClass) {

		this(() -> ReflectionUtils.newInstance(nodeClass));
	}

	public AjaxDevelopmentServerServlet(Supplier<IDomNode> nodeFactory) {

		this.ajaxFramework = new AjaxFramework(new AjaxDevelopmentServerStrategy(nodeFactory));
	}

	@Override
	public void init() {

		ajaxFramework.initialize(getServletContext());
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) {

		ajaxFramework.service(request, response);
	}
}
