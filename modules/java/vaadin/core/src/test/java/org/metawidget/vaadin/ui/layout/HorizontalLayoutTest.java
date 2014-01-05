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

package org.metawidget.vaadin.ui.layout;

import static org.metawidget.inspector.InspectionResultConstants.*;
import junit.framework.TestCase;

import org.metawidget.vaadin.ui.Stub;
import org.metawidget.vaadin.ui.VaadinMetawidget;

import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Slider;
import com.vaadin.ui.TextField;

/**
 * @author <a href="http://kennardconsulting.com">Richard Kennard</a>
 */

public class HorizontalLayoutTest
	extends TestCase {

	//
	// Public methods
	//

	public void testLayout()
		throws Exception {

		VaadinMetawidget metawidget = new VaadinMetawidget();
		ComponentContainer container = (ComponentContainer) ( new Panel( new com.vaadin.ui.VerticalLayout() ) ).getContent();

		// startLayout

		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.startContainerLayout( container, metawidget );

		assertTrue( container.iterator().next() instanceof com.vaadin.ui.HorizontalLayout );
		assertTrue( ( (com.vaadin.ui.HorizontalLayout) container.iterator().next() ).isSpacing() );
		assertFalse( metawidget.iterator().hasNext() );

		// layoutWidget

		assertEquals( 0, ( (com.vaadin.ui.HorizontalLayout) container.iterator().next() ).getComponentCount() );

		Stub stub = new Stub();
		horizontalLayout.layoutWidget( stub, PROPERTY, null, container, metawidget );
		assertEquals( 0, ( (com.vaadin.ui.HorizontalLayout) container.iterator().next() ).getComponentCount() );

		stub.addComponent( new Slider() );
		horizontalLayout.layoutWidget( stub, PROPERTY, null, container, metawidget );
		assertEquals( stub, ( (com.vaadin.ui.HorizontalLayout) container.iterator().next() ).getComponent( 0 ) );
		assertEquals( 1, ( (com.vaadin.ui.HorizontalLayout) container.iterator().next() ).getComponentCount() );

		horizontalLayout.layoutWidget( new TextField(), PROPERTY, null, container, metawidget );
		assertTrue( ( (com.vaadin.ui.HorizontalLayout) container.iterator().next() ).getComponent( 1 ) instanceof TextField );
		assertEquals( 2, ( (com.vaadin.ui.HorizontalLayout) container.iterator().next() ).getComponentCount() );
	}
}
