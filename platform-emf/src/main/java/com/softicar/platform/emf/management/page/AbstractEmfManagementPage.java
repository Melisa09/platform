package com.softicar.platform.emf.management.page;

import com.softicar.platform.common.core.i18n.IDisplayString;
import com.softicar.platform.common.core.utils.DevNull;
import com.softicar.platform.common.io.resource.IResource;
import com.softicar.platform.dom.node.IDomNode;
import com.softicar.platform.emf.management.EmfManagementDivBuilder;
import com.softicar.platform.emf.module.IEmfModuleInstance;
import com.softicar.platform.emf.module.role.EmfDefaultModuleRoles;
import com.softicar.platform.emf.module.role.IEmfModuleRole;
import com.softicar.platform.emf.page.IEmfPage;
import com.softicar.platform.emf.table.IEmfTable;

public abstract class AbstractEmfManagementPage<I extends IEmfModuleInstance<I>> implements IEmfPage<I> {

	@Override
	public final IResource getIcon() {

		return getTable().getIcon();
	}

	@Override
	public final IDisplayString getTitle() {

		return getTable().getPluralTitle();
	}

	@Override
	public final IDomNode createContentNode(I moduleInstance) {

		EmfManagementDivBuilder<?, ?, I> builder = new EmfManagementDivBuilder<>(getTable(), moduleInstance);
		customize(builder);
		return builder.build();
	}

	@Override
	public IEmfModuleRole<I> getAuthorizedRole() {

		return EmfDefaultModuleRoles.getModuleViewer();
	}

	/**
	 * Applies customization to the internal builder.
	 * <p>
	 * Override if necessary.
	 *
	 * @param builder
	 *            the builder (never <i>null</i>)
	 */
	protected void customize(EmfManagementDivBuilder<?, ?, I> builder) {

		// nothing by default
		DevNull.swallow(builder);
	}

	protected abstract IEmfTable<?, ?, I> getTable();
}