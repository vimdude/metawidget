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

package org.metawidget.android;

import java.util.Iterator;
import java.util.LinkedHashMap;

import android.util.AttributeSet;

/**
 * @author <a href="http://kennardconsulting.com">Richard Kennard</a>
 */

public class AndroidMetawidgetTests {

	//
	// Inner class
	//

	public static class MockAttributeSet
		implements AttributeSet {

		//
		// Private members
		//

		private LinkedHashMap<String, String>	mAttributes	= new LinkedHashMap<String, String>();

		//
		// Supported public methods
		//

		public void setAttributeValue( String key, String value ) {

			mAttributes.put( key, value );
		}

		public String getAttributeValue( String object, String key ) {

			return mAttributes.get( key );
		}

		public int getAttributeCount() {

			return mAttributes.size();
		}

		public String getAttributeValue( int index ) {

			Iterator<String> iterator = mAttributes.values().iterator();

			for ( int loop = 0; loop < index - 1; loop++ ) {
				iterator.next();
			}

			return iterator.next();
		}

		public String getAttributeName( int index ) {

			Iterator<String> iterator = mAttributes.keySet().iterator();

			for ( int loop = 0; loop < index - 1; loop++ ) {
				iterator.next();
			}

			return iterator.next();
		}

		public int getAttributeResourceValue( String object, String string, int i ) {

			return 0;
		}

		//
		// Unsupported public methods
		//

		public boolean getAttributeBooleanValue( int arg0, boolean arg1 ) {

			throw new UnsupportedOperationException();
		}

		public boolean getAttributeBooleanValue( String arg0, String arg1, boolean arg2 ) {

			throw new UnsupportedOperationException();
		}

		public float getAttributeFloatValue( int arg0, float arg1 ) {

			throw new UnsupportedOperationException();
		}

		public float getAttributeFloatValue( String arg0, String arg1, float arg2 ) {

			throw new UnsupportedOperationException();
		}

		public int getAttributeIntValue( int arg0, int arg1 ) {

			throw new UnsupportedOperationException();
		}

		public int getAttributeIntValue( String arg0, String arg1, int arg2 ) {

			throw new UnsupportedOperationException();
		}

		public int getAttributeListValue( int arg0, String[] arg1, int arg2 ) {

			throw new UnsupportedOperationException();
		}

		public int getAttributeListValue( String arg0, String arg1, String[] arg2, int arg3 ) {

			throw new UnsupportedOperationException();
		}

		public int getAttributeNameResource( int arg0 ) {

			throw new UnsupportedOperationException();
		}

		public int getAttributeResourceValue( int arg0, int arg1 ) {

			throw new UnsupportedOperationException();
		}

		public int getAttributeUnsignedIntValue( int arg0, int arg1 ) {

			throw new UnsupportedOperationException();
		}

		public int getAttributeUnsignedIntValue( String arg0, String arg1, int arg2 ) {

			throw new UnsupportedOperationException();
		}

		public String getClassAttribute() {

			throw new UnsupportedOperationException();
		}

		public String getIdAttribute() {

			throw new UnsupportedOperationException();
		}

		public int getIdAttributeResourceValue( int arg0 ) {

			throw new UnsupportedOperationException();
		}

		public String getPositionDescription() {

			throw new UnsupportedOperationException();
		}

		public int getStyleAttribute() {

			throw new UnsupportedOperationException();
		}
	}

	//
	// Private constructor
	//

	private AndroidMetawidgetTests() {

		// Can never be called
	}
}
