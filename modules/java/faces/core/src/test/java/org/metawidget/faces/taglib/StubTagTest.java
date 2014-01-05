// Metawidget
//
// This file is dual licensed under both the LGPL
// (http://www.gnu.org/licenses/lgpl-2.1.html) and the EPL
// (http://www.eclipse.org/org/documents/epl-v10.php). As a
// recipient of Metawidget, you may choose to receive it under either
// the LGPL or the EPL.
//
// Commercial licenses are also available. See http://metawidget.org
// for details.

package org.metawidget.faces.taglib;

import javax.faces.context.FacesContext;

import junit.framework.TestCase;

import org.metawidget.faces.FacesMetawidgetTests.MockFacesContext;
import org.metawidget.faces.FacesMetawidgetTests.MockPageContext;
import org.metawidget.iface.MetawidgetException;

/**
 * StubTag test cases.
 * <p>
 * These are just some fringe-case tests. Most of the testing is done by WebTest.
 *
 * @author <a href="http://kennardconsulting.com">Richard Kennard</a>
 */

public class StubTagTest
	extends TestCase {

	//
	// Private members
	//

	private FacesContext	mContext;

	//
	// Public methods
	//

	public void testStubTag()
		throws Exception {

		StubTag stubTag = new StubTag();
		stubTag.setPageContext( new MockPageContext() );

		// Value

		stubTag.setValue( "foo" );

		try {
			stubTag.setProperties( null );
			fail();
		} catch( MetawidgetException e ) {
			assertEquals( "Value 'foo' must be an EL expression", e.getMessage() );
		}

		// Action

		stubTag.setAction( "bar" );

		try {
			stubTag.setProperties( null );
			fail();
		} catch( MetawidgetException e ) {
			assertEquals( "Action 'bar' must be an EL expression", e.getMessage() );
		}
	}

	//
	// Protected methods
	//

	@Override
	protected final void setUp()
		throws Exception {

		super.setUp();

		mContext = new MockFacesContext();
	}

	@Override
	protected final void tearDown()
		throws Exception {

		super.tearDown();

		mContext.release();
	}
}
