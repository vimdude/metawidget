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

package org.metawidget.util;

import static org.metawidget.inspector.InspectionResultConstants.*;
import static org.metawidget.inspector.propertytype.PropertyTypeInspectionResultConstants.*;

import java.util.Map;

/**
 * Utilities for working with WidgetBuilders.
 * <p>
 * Some of the logic behind WidgetBuilder decisions can be a little involved, so we refactor it
 * here.
 * <p>
 * Not located under <code>org.metawidget.widgetbuilder.impl</code> because GWT cannot compile this
 * class.
 *
 * @author <a href="http://kennardconsulting.com">Richard Kennard</a>
 */

public final class WidgetBuilderUtils {

	//
	// Public methods
	//

	/**
	 * Returns whether the attributes have READ_ONLY or NO_SETTER set to TRUE.
	 * <p>
	 * The latter case relies on complex attributes being rendered by nested Metawidgets: the nested
	 * Metawidgets will <em>not</em> have setReadOnly set on them, which gets us the desired result.
	 * Namely, primitive types without a setter are rendered as read-only, complex types without a
	 * setter are rendered as writeable (because their nested primitives are writeable).
	 * <p>
	 * Furthermore, what is considered 'primitive' is up to the platform. Some platforms may
	 * consider, say, an Address as 'primitive', using a dedicated Address widget. Other platforms
	 * may consider an Address as complex, using a nested Metawidget.
	 *
	 * @return true if the attributes have READ_ONLY set to TRUE, or NO_SETTER set to true.
	 */

	public static boolean isReadOnly( Map<String, String> attributes ) {

		if ( TRUE.equals( attributes.get( READ_ONLY ) ) ) {
			return true;
		}

		if ( TRUE.equals( attributes.get( NO_SETTER ) ) ) {
			return true;
		}

		return false;
	}

	/**
	 * Looks up the TYPE attribute, but first checks the ACTUAL_CLASS attribute.
	 *
	 * @param defaultClass
	 *            class to default to if both ACTUAL_CLASS and TYPE are null
	 * @return ACTUAL_CLASS or, if none, TYPE or, if none, defaultClass. If class is found but
	 *         cannot be instantiated, returns null
	 */

	public static Class<?> getActualClassOrType( Map<String, String> attributes, Class<?> defaultClass ) {

		return getActualClassOrType( attributes, defaultClass, null );
	}

	public static Class<?> getActualClassOrType( Map<String, String> attributes, Class<?> defaultClass, ClassLoader classLoader ) {

		String type = attributes.get( ACTUAL_CLASS );

		if ( type == null || "".equals( type ) ) {

			type = attributes.get( TYPE );

			if ( type == null || "".equals( type ) ) {
				return defaultClass;
			}
		}

		return ClassUtils.niceForName( type, classLoader );
	}

	/**
	 * Looks up the type of the components in an Array or Collection.
	 */

	public static String getComponentType( Map<String, String> attributes ) {

		String parameterizedType = attributes.get( PARAMETERIZED_TYPE );

		if ( parameterizedType != null ) {
			return parameterizedType;
		}

		Class<?> clazz = WidgetBuilderUtils.getActualClassOrType( attributes, null );

		if ( clazz == null ) {
			return null;
		}

		if ( clazz.isArray() ) {
			return clazz.getComponentType().getName();
		}

		return null;
	}

	/**
	 * Returns true if the lookup is nullable, not required, or has a forced empty choice.
	 */

	public static boolean needsEmptyLookupItem( Map<String, String> attributes ) {

		if ( TRUE.equals( attributes.get( LOOKUP_HAS_EMPTY_CHOICE ) ) ) {
			return true;
		}

		if ( TRUE.equals( attributes.get( REQUIRED ) ) ) {
			return false;
		}

		Class<?> clazz = getActualClassOrType( attributes, null );

		// Type can be null if this lookup was specified by a metawidget-metadata.xml
		// and the type was omitted from the XML. In that case, assume nullable
		//
		// Note: there's an extra caveat for Groovy dynamic types: if we can't load
		// the class, assume it is non-primitive and therefore add a null choice

		if ( clazz != null && clazz.isPrimitive() ) {
			return false;
		}

		return true;
	}

	//
	// Private constructor
	//

	private WidgetBuilderUtils() {

		// Can never be called
	}
}
