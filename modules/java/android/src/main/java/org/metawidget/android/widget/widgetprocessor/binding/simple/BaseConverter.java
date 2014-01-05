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

package org.metawidget.android.widget.widgetprocessor.binding.simple;

import org.metawidget.util.simple.StringUtils;

import android.view.View;

/**
 * Convenience implementation.
 *
 * @author <a href="http://kennardconsulting.com">Richard Kennard</a>
 */

public abstract class BaseConverter<T>
	implements Converter<T> {

	//
	// Public methods
	//

	/**
	 * Convert the given value to a form that can be displayed by the given View.
	 * <p>
	 * By default, uses <code>StringUtils.quietValueOf</code>.
	 */

	public Object convertForView( View view, T value ) {

		return StringUtils.quietValueOf( value );
	}
}
