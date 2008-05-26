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

package org.metawidget.test.gwt.allwidgets.client.converter;

import org.metawidget.gwt.client.binding.simple.Converter;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Richard Kennard
 */

public class BooleanConverter
	extends Converter<Boolean>
{
	//
	//
	// Public methods
	//
	//


	@Override
	public Boolean convertFromWidget( Widget widget, Object value, Class<?> type )
	{
		if ( widget instanceof CheckBox )
		{
			// TODO: why is this a String?

			if ( value instanceof String )
				return Boolean.valueOf( (String) value );

			return (Boolean) value;
		}

		if ( value == null || "".equals( value ))
			return null;

		return Boolean.valueOf( (String) value );
	}

	@Override
	public Object convertForWidget( Widget widget, Boolean value )
	{
		if ( widget instanceof CheckBox )
			return value;

		return super.convertForWidget( widget, value );
	}
}
