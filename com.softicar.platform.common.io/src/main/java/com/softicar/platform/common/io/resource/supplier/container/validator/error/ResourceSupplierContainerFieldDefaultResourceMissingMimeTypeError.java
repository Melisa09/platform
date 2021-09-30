package com.softicar.platform.common.io.resource.supplier.container.validator.error;

import com.softicar.platform.common.core.constant.container.field.IConstantContainerField;
import com.softicar.platform.common.core.constant.container.validator.error.AbstractConstantContainerValidationError;
import com.softicar.platform.common.io.resource.supplier.IResourceSupplier;

public class ResourceSupplierContainerFieldDefaultResourceMissingMimeTypeError extends AbstractConstantContainerValidationError<IResourceSupplier> {

	public ResourceSupplierContainerFieldDefaultResourceMissingMimeTypeError(IConstantContainerField<IResourceSupplier> field) {

		super(field, "Default resource: Missing mime type.");
	}
}
