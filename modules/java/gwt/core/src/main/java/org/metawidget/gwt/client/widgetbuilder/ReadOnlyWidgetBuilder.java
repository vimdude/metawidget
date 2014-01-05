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

package org.metawidget.gwt.client.widgetbuilder;

import static org.metawidget.inspector.InspectionResultConstants.*;

import java.util.Date;
import java.util.Map;

import org.metawidget.gwt.client.ui.GwtMetawidget;
import org.metawidget.gwt.client.ui.GwtUtils;
import org.metawidget.gwt.client.ui.GwtValueAccessor;
import org.metawidget.gwt.client.ui.Stub;
import org.metawidget.util.simple.StringUtils;
import org.metawidget.widgetbuilder.iface.WidgetBuilder;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * WidgetBuilder for read-only widgets in GWT environments.
 *
 * @author <a href="http://kennardconsulting.com">Richard Kennard</a>
 */

public class ReadOnlyWidgetBuilder
	implements WidgetBuilder<Widget, GwtMetawidget>, GwtValueAccessor {

	//
	// Public methods
	//

	public Object getValue( Widget widget ) {

		// Label (not HasText, because CheckBox is a HasText)

		if ( widget instanceof Label ) {
			return ( (HasText) widget ).getText();
		}

		return null;
	}

	public boolean setValue( Widget widget, Object value ) {

		// Label (not HasText, because CheckBox is a HasText)

		if ( widget instanceof Label ) {
			( (HasText) widget ).setText( StringUtils.quietValueOf( value ) );
			return true;
		}

		// Panel (fail gracefully for MASKED fields)

		if ( widget instanceof SimplePanel ) {
			return true;
		}

		// Not for us

		return false;
	}

	public Widget buildWidget( String elementName, Map<String, String> attributes, GwtMetawidget metawidget ) {

		// Not read-only?

		if ( !GwtUtils.isReadOnly( attributes ) ) {
			return null;
		}

		// Hidden

		if ( TRUE.equals( attributes.get( HIDDEN ) ) ) {
			return new Stub();
		}

		// Action

		if ( ACTION.equals( elementName ) ) {
			Button button = new Button( metawidget.getLabelString( attributes ) );
			button.setEnabled( false );

			return button;
		}

		// Masked (return a Panel, so that we DO still render a label)

		if ( TRUE.equals( attributes.get( MASKED ) ) ) {
			return new SimplePanel();
		}

		String type = GwtUtils.getActualClassOrType( attributes );

		// If no type, assume a String

		if ( type == null ) {
			type = String.class.getName();
		}

		String lookup = attributes.get( LOOKUP );

		if ( lookup != null && !"".equals( lookup ) ) {
			return new Label();
		}

		if ( GwtUtils.isPrimitive( type ) || GwtUtils.isPrimitiveWrapper( type ) ) {
			return new Label();
		}

		if ( String.class.getName().equals( type ) ) {
			return new Label();
		}

		if ( Date.class.getName().equals( type ) ) {
			return new Label();
		}

		// Collections

		if ( GwtUtils.isCollection( type ) ) {
			return new Stub();
		}

		// Not simple, but don't expand

		if ( TRUE.equals( attributes.get( DONT_EXPAND ) ) ) {
			return new Label();
		}

		// Nested Metawidget

		return null;
	}
}
