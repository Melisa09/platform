package com.softicar.platform.dom.elements;

import com.softicar.platform.common.io.resource.IResource;
import com.softicar.platform.common.io.resource.IResourceUrl;
import com.softicar.platform.dom.element.DomElement;
import com.softicar.platform.dom.element.DomElementTag;

/**
 * Represents an Html image.
 *
 * @author Oliver Richers
 */
public class DomImage extends DomElement {

	private IResource resource;

	/**
	 * Constructs an image showing the specified resource.
	 * <p>
	 * This constructor simply calls {@link #setResource(IResource)}.
	 *
	 * @param resource
	 *            the resource containing the actual image data
	 */
	public DomImage(IResource resource) {

		setResource(resource);
	}

	/**
	 * Defines the resource shown by this image.
	 * <p>
	 * The given resource will be registered in the dom engine and the returned
	 * URL will be referenced by the <i>src</i> attribute of this image.
	 *
	 * @param resource
	 *            the resource containing the actual image data
	 */
	public void setResource(IResource resource) {

		this.resource = resource;

		IResourceUrl resourceUrl = getDomEngine().getResourceUrl(resource);
		setAttribute("src", resourceUrl.toString());
	}

	/**
	 * Returns the resources shown by this image element.
	 *
	 * @return the resource or null if no resource has been defined
	 */
	public IResource getResource() {

		return resource;
	}

	/**
	 * Constructs an image with the specified source address.
	 *
	 * @param src
	 *            the source URL of the image
	 */
	public DomImage(String src) {

		setAttribute("src", src);
	}

	@Override
	public DomElementTag getTag() {

		return DomElementTag.IMG;
	}
}