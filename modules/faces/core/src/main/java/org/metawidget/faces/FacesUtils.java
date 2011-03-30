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

package org.metawidget.faces;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 * Utilities for working with Java Server Faces.
 *
 * @author Richard Kennard
 */

public final class FacesUtils {

	//
	// Public statics
	//

	/**
	 * Return <code>true</code> if the specified value conforms to the syntax requirements of a
	 * value binding expression.
	 * <p>
	 * This method is a mirror of the one in <code>UIComponentTag.isValueReference</code>, but that
	 * one is deprecated so may be removed in the future.
	 *
	 * @param value
	 *            The value to evaluate
	 * @throws NullPointerException
	 *             if <code>value</code> is <code>null</code>
	 */

	public static boolean isExpression( String value ) {

		return PATTERN_EXPRESSION.matcher( value ).matches();
	}

	/**
	 * @return the original String, not wrapped in #{...}. If the original String was not wrapped,
	 *         returns the original String
	 */

	public static String unwrapExpression( String value ) {

		Matcher matcher = PATTERN_EXPRESSION.matcher( value );

		if ( !matcher.matches() ) {
			return value;
		}

		return matcher.group( 3 );
	}

	/**
	 * @return the original String, wrapped in #{...}. If the original String was already wrapped,
	 *         returns the original String
	 */

	public static String wrapExpression( String value ) {

		if ( isExpression( value ) ) {
			return value;
		}

		return EXPRESSION_START + unwrapExpression( value ) + EXPRESSION_END;
	}

	public static void render( FacesContext context, UIComponent component )
		throws IOException {

		if ( component == null || !component.isRendered() ) {
			return;
		}

		component.encodeBegin( context );

		if ( component.getRendersChildren() ) {
			component.encodeChildren( context );
		} else {
			renderChildren( context, component );
		}

		component.encodeEnd( context );
	}

	/**
	 * @return	true if the JSF version is 2 or above, false otherwise
	 */

	public static boolean isJsf2() {

		try {

			Class.forName( "javax.faces.event.PreRenderViewEvent" );
			return true;

		} catch ( ClassNotFoundException e ) {

			return false;
		}
	}

	public static boolean isPartialStateSavingDisabled() {

		FacesContext context = FacesContext.getCurrentInstance();
		return ( "false".equals( context.getExternalContext().getInitParameter( "javax.faces.PARTIAL_STATE_SAVING" )));
	}

	//
	// Private statics
	//

	private static void renderChildren( FacesContext context, UIComponent component )
		throws IOException {

		for ( UIComponent componentChild : component.getChildren() ) {
			render( context, componentChild );
		}
	}

	/**
	 * Match #{...} and ${...}. This mirrors the approach in
	 * <code>UIComponentTag.isValueReference</code>, but that one is deprecated so may be removed in
	 * the future.
	 * <p>
	 * Like <code>UIComponentTag.isValueReference</code> we allow nested #{...} blocks, because this
	 * can still be a legitimate value reference:
	 * <p>
	 * <code>
	 * #{!empty bar ? '' : '#{foo}'}
	 * </code>
	 */

	private static final Pattern	PATTERN_EXPRESSION	= Pattern.compile( "((#|\\$)\\{)(.*)(\\})" );

	private static final String		EXPRESSION_START	= "#{";

	private static final String		EXPRESSION_END		= "}";

	//
	// Private constructor
	//

	private FacesUtils() {

		// Can never be called
	}
}