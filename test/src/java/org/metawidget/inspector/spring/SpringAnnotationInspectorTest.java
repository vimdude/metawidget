// Metawidget
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

package org.metawidget.inspector.spring;

import static org.metawidget.inspector.InspectionResultConstants.*;
import static org.metawidget.inspector.spring.SpringInspectionResultConstants.*;
import junit.framework.TestCase;

import org.metawidget.inspector.spring.SpringAnnotationInspector;
import org.metawidget.inspector.spring.UiSpringLookup;
import org.metawidget.util.XmlUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author Richard Kennard
 */

public class SpringAnnotationInspectorTest
	extends TestCase
{
	//
	// Public methods
	//

	public void testInspection()
	{
		SpringAnnotationInspector inspector = new SpringAnnotationInspector();
		Document document = XmlUtils.documentFromString( inspector.inspect( new Foo(), Foo.class.getName() ));

		assertTrue( "inspection-result".equals( document.getFirstChild().getNodeName() ) );

		// Entity

		Element entity = (Element) document.getFirstChild().getFirstChild();
		assertTrue( ENTITY.equals( entity.getNodeName() ) );
		assertTrue( Foo.class.getName().equals( entity.getAttribute( TYPE ) ) );
		assertFalse( entity.hasAttribute( NAME ) );

		// Properties

		Element property = XmlUtils.getChildWithAttributeValue( entity, NAME, "object1" );
		assertTrue( PROPERTY.equals( property.getNodeName() ) );
		assertTrue( "bar".equals( property.getAttribute( SPRING_LOOKUP ) ) );

		property = XmlUtils.getChildWithAttributeValue( entity, NAME, "object2" );
		assertTrue( PROPERTY.equals( property.getNodeName() ) );
		assertTrue( "baz".equals( property.getAttribute( SPRING_LOOKUP ) ) );
		assertTrue( "abc".equals( property.getAttribute( SPRING_LOOKUP_ITEM_VALUE ) ) );
		assertTrue( "def".equals( property.getAttribute( SPRING_LOOKUP_ITEM_LABEL ) ) );

		assertTrue( entity.getChildNodes().getLength() == 2 );
	}

	//
	// Inner class
	//

	public static class Foo
	{
		@UiSpringLookup( "bar" )
		public Object	object1;

		@UiSpringLookup( value = "baz", itemValue = "abc", itemLabel = "def" )
		public Object	object2;
	}
}
