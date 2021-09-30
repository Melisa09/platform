package com.softicar.platform.dom.style;

import org.junit.Assert;
import org.junit.Test;

public class CssPixelTest extends Assert {

	@Test
	public void testToString() {

		assertEquals("-22px", new CssPixel(-22).toString());
		assertEquals("0px", new CssPixel(0).toString());
		assertEquals("33px", new CssPixel(33).toString());
	}
}
