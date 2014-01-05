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

package org.metawidget.inspector.faces;

import static org.metawidget.inspector.InspectionResultConstants.*;
import static org.metawidget.inspector.faces.FacesInspectionResultConstants.*;

import java.util.Map;
import java.util.regex.Pattern;

import org.metawidget.inspector.iface.InspectorException;
import org.metawidget.inspector.impl.BaseObjectInspector;
import org.metawidget.inspector.impl.BaseObjectInspectorConfig;
import org.metawidget.inspector.impl.propertystyle.Property;
import org.metawidget.util.CollectionUtils;

/**
 * Inspects annotations defined by Metawidget's Java Server Faces support (declared in this same
 * package).
 * 
 * @author <a href="http://kennardconsulting.com">Richard Kennard</a>
 */

public class FacesAnnotationInspector
	extends BaseObjectInspector {

	//
	// Constructor
	//

	public FacesAnnotationInspector() {

		this( new BaseObjectInspectorConfig() );
	}

	public FacesAnnotationInspector( BaseObjectInspectorConfig config ) {

		super( config );
	}

	//
	// Protected methods
	//

	@Override
	protected Map<String, String> inspectProperty( Property property )
		throws Exception {

		Map<String, String> attributes = CollectionUtils.newHashMap();

		// FacesLookup

		UiFacesLookup facesLookup = property.getAnnotation( UiFacesLookup.class );

		if ( facesLookup != null ) {
			putExpression( attributes, FACES_LOOKUP, facesLookup.value() );

			String var = facesLookup.var();

			if ( !"".equals( var ) ) {
				attributes.put( FACES_LOOKUP_VAR, var );
			}

			putExpression( attributes, FACES_LOOKUP_ITEM_VALUE, facesLookup.itemValue() );
			putExpression( attributes, FACES_LOOKUP_ITEM_LABEL, facesLookup.itemLabel() );
		}

		UiFacesSuggest facesSuggest = property.getAnnotation( UiFacesSuggest.class );

		if ( facesSuggest != null ) {
			putExpression( attributes, FACES_SUGGEST, facesSuggest.value() );
		}

		// Component

		UiFacesComponent component = property.getAnnotation( UiFacesComponent.class );

		if ( component != null ) {
			attributes.put( FACES_COMPONENT, component.value() );
		}

		// AJAX

		UiFacesAjax ajax = property.getAnnotation( UiFacesAjax.class );

		if ( ajax != null ) {
			attributes.put( FACES_AJAX_EVENT, ajax.event() );
			putExpression( attributes, FACES_AJAX_ACTION, ajax.action() );
		}

		// Converters

		UiFacesConverter converter = property.getAnnotation( UiFacesConverter.class );

		if ( converter != null ) {
			attributes.put( FACES_CONVERTER, converter.value() );
		}

		UiFacesNumberConverter numberConverter = property.getAnnotation( UiFacesNumberConverter.class );

		if ( numberConverter != null ) {
			if ( !"".equals( numberConverter.currencyCode() ) ) {
				attributes.put( CURRENCY_CODE, numberConverter.currencyCode() );
			}

			if ( !"".equals( numberConverter.currencySymbol() ) ) {
				attributes.put( CURRENCY_SYMBOL, numberConverter.currencySymbol() );
			}

			if ( numberConverter.groupingUsed() ) {
				attributes.put( NUMBER_USES_GROUPING_SEPARATORS, TRUE );
			}

			if ( numberConverter.minIntegerDigits() != -1 ) {
				attributes.put( MINIMUM_INTEGER_DIGITS, String.valueOf( numberConverter.minIntegerDigits() ) );
			}

			if ( numberConverter.maxIntegerDigits() != -1 ) {
				attributes.put( MAXIMUM_INTEGER_DIGITS, String.valueOf( numberConverter.maxIntegerDigits() ) );
			}

			if ( numberConverter.minFractionDigits() != -1 ) {
				attributes.put( MINIMUM_FRACTIONAL_DIGITS, String.valueOf( numberConverter.minFractionDigits() ) );
			}

			if ( numberConverter.maxFractionDigits() != -1 ) {
				attributes.put( MAXIMUM_FRACTIONAL_DIGITS, String.valueOf( numberConverter.maxFractionDigits() ) );
			}

			if ( !"".equals( numberConverter.locale() ) ) {
				attributes.put( LOCALE, numberConverter.locale() );
			}

			if ( !"".equals( numberConverter.pattern() ) ) {
				attributes.put( NUMBER_PATTERN, numberConverter.pattern() );
			}

			if ( !"".equals( numberConverter.type() ) ) {
				attributes.put( NUMBER_TYPE, numberConverter.type() );
			}
		}

		UiFacesDateTimeConverter dateTimeConverter = property.getAnnotation( UiFacesDateTimeConverter.class );

		if ( dateTimeConverter != null ) {
			if ( !"".equals( dateTimeConverter.dateStyle() ) ) {
				attributes.put( DATE_STYLE, dateTimeConverter.dateStyle() );
			}

			if ( !"".equals( dateTimeConverter.locale() ) ) {
				attributes.put( LOCALE, dateTimeConverter.locale() );
			}

			if ( !"".equals( dateTimeConverter.pattern() ) ) {
				attributes.put( DATETIME_PATTERN, dateTimeConverter.pattern() );
			}

			if ( !"".equals( dateTimeConverter.timeStyle() ) ) {
				attributes.put( TIME_STYLE, dateTimeConverter.timeStyle() );
			}

			if ( !"".equals( dateTimeConverter.timeZone() ) ) {
				attributes.put( TIME_ZONE, dateTimeConverter.timeZone() );
			}

			if ( !"".equals( dateTimeConverter.type() ) ) {
				attributes.put( DATETIME_TYPE, dateTimeConverter.type() );
			}
		}

		return attributes;
	}

	//
	// Private methods
	//

	/**
	 * Put a JSF expression into an attribute. Essentially the same as
	 * <code>attributes.put( name, value )</code> but performs a quick sanity check
	 * that the expression is of the form <code>#{...}</code>.
	 */

	private void putExpression( Map<String, String> attributes, String attributeName, String expression ) {

		if ( "".equals( expression ) ) {
			return;
		}

		// Sanity checks

		if ( !isExpression( expression ) ) {
			throw InspectorException.newException( "Expression '" + expression + "' (for '" + attributeName + "') is not of the form #{...}" );
		}

		// Put the expression

		attributes.put( attributeName, expression );
	}

	/**
	 * Copy of <code>FacesUtils.isExpression</code>. Needed here to avoid circular dependency.
	 */

	private boolean isExpression( String value ) {

		return PATTERN_EXPRESSION.matcher( value ).matches();
	}

	//
	// Private statics
	//

	/**
	 * Copy of <code>FacesUtils.PATTERN_EXPRESSION</code>. Needed here to avoid circular dependency.
	 */

	private static final Pattern	PATTERN_EXPRESSION	= Pattern.compile( "((#|\\$)\\{)(.*)(\\})" );
}
