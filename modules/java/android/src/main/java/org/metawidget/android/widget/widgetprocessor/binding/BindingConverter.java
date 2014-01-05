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

package org.metawidget.android.widget.widgetprocessor.binding;

/**
 * Android support: binding
 *
 * @author <a href="http://kennardconsulting.com">Richard Kennard</a>
 */

public interface BindingConverter {

	//
	// Methods
	//

	/**
	 * Convert the given String to the given expected type, if possible. If not possible, just
	 * return the original String.
	 */

	Object convertFromString( String value, Class<?> expectedType );
}
