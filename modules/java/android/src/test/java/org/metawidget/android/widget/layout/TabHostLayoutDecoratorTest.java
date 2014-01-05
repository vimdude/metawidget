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

package org.metawidget.android.widget.layout;

import junit.framework.TestCase;

import org.metawidget.android.widget.AndroidMetawidget;
import org.metawidget.android.widget.Facet;
import org.metawidget.inspector.annotation.UiComesAfter;
import org.metawidget.inspector.annotation.UiLookup;
import org.metawidget.inspector.annotation.UiSection;
import org.metawidget.layout.decorator.LayoutDecoratorConfig;

import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * @author <a href="http://kennardconsulting.com">Richard Kennard</a>
 */

public class TabHostLayoutDecoratorTest
	extends TestCase {

	//
	// Public methods
	//

	public void testTabHostLayoutDecorator() {

		AndroidMetawidget androidMetawidget = new AndroidMetawidget( null );
		androidMetawidget.setLayout( new TabHostLayoutDecorator( new LayoutDecoratorConfig<View, ViewGroup, AndroidMetawidget>().setLayout( new TableLayout() ) ) );
		androidMetawidget.setToInspect( new Foo() );

		Facet facet = new Facet( null );
		facet.setName( "buttons" );
		androidMetawidget.addView( facet );

		android.widget.TableLayout tableLayout = (android.widget.TableLayout) androidMetawidget.getChildAt( 0 );
		TableRow tableRow = (TableRow) tableLayout.getChildAt( 0 );
		assertEquals( "Bar: ", ( (TextView) tableRow.getChildAt( 0 ) ).getText() );
		assertTrue( tableRow.getChildAt( 1 ) instanceof EditText );

		// Tab Host #1

		tableRow = (TableRow) tableLayout.getChildAt( 1 );
		TabHost tabHost = (TabHost) tableRow.getChildAt( 0 );
		android.widget.LinearLayout tabLayout = (android.widget.LinearLayout) tabHost.getChildAt( 0 );
		assertEquals( android.widget.LinearLayout.VERTICAL, tabLayout.getOrientation() );
		assertEquals( android.widget.LinearLayout.HORIZONTAL, new android.widget.LinearLayout( null ).getOrientation() );
		assertTrue( tabLayout.getChildAt( 0 ) instanceof TabWidget );
		assertTrue( tabLayout.getChildAt( 1 ) instanceof FrameLayout );

		// Tab 1

		assertEquals( "tab1", tabHost.getTabSpec( 0 ).getIndicator() );
		android.widget.LinearLayout tab = (android.widget.LinearLayout) tabHost.getTabSpec( 0 ).getContent().createTabContent( null );
		assertEquals( android.widget.LinearLayout.VERTICAL, tab.getOrientation() );
		android.widget.TableLayout tabTableLayout = (android.widget.TableLayout) tab.getChildAt( 0 );
		tableRow = (TableRow) tabTableLayout.getChildAt( 0 );
		assertEquals( "Baz: ", ( (TextView) tableRow.getChildAt( 0 ) ).getText() );
		assertTrue( tableRow.getChildAt( 1 ) instanceof CheckBox );
		tableRow = (TableRow) tabTableLayout.getChildAt( 1 );
		assertEquals( "Abc: ", ( (TextView) tableRow.getChildAt( 0 ) ).getText() );
		assertTrue( tableRow.getChildAt( 1 ) instanceof EditText );
		assertEquals( 2, tabTableLayout.getChildCount() );

		// Tab 2

		assertEquals( "tab2", tabHost.getTabSpec( 1 ).getIndicator() );
		tab = (android.widget.LinearLayout) tabHost.getTabSpec( 1 ).getContent().createTabContent( null );
		assertEquals( android.widget.LinearLayout.VERTICAL, tab.getOrientation() );
		tabTableLayout = (android.widget.TableLayout) tab.getChildAt( 0 );
		tableRow = (TableRow) tabTableLayout.getChildAt( 0 );
		assertEquals( "Def: ", ( (TextView) tableRow.getChildAt( 0 ) ).getText() );
		assertTrue( tableRow.getChildAt( 1 ) instanceof EditText );
		assertEquals( 1, tabTableLayout.getChildCount() );

		// Separate component

		tableRow = (TableRow) tableLayout.getChildAt( 2 );
		assertEquals( "Ghi: ", ( (TextView) tableRow.getChildAt( 0 ) ).getText() );
		assertTrue( tableRow.getChildAt( 1 ) instanceof Spinner );

		// Tab Host #2

		tableRow = (TableRow) tableLayout.getChildAt( 3 );
		tabHost = (TabHost) tableRow.getChildAt( 0 );
		tabLayout = (android.widget.LinearLayout) tabHost.getChildAt( 0 );
		assertTrue( tabLayout.getChildAt( 0 ) instanceof TabWidget );
		assertTrue( tabLayout.getChildAt( 1 ) instanceof FrameLayout );

		// Tab A

		assertEquals( "tabA", tabHost.getTabSpec( 0 ).getIndicator() );
		tab = (android.widget.LinearLayout) tabHost.getTabSpec( 0 ).getContent().createTabContent( null );
		assertEquals( android.widget.LinearLayout.VERTICAL, tab.getOrientation() );
		tableLayout = (android.widget.TableLayout) tab.getChildAt( 0 );
		tableRow = (TableRow) tableLayout.getChildAt( 0 );
		assertEquals( "Jkl: ", ( (TextView) tableRow.getChildAt( 0 ) ).getText() );
		assertTrue( tableRow.getChildAt( 1 ) instanceof EditText );
		assertEquals( 1, tableLayout.getChildCount() );

		assertEquals( facet, androidMetawidget.getChildAt( 1 ) );
		assertEquals( 2, androidMetawidget.getChildCount() );
	}

	//
	// Inner class
	//

	public static class Foo {

		public String getBar() {

			return null;
		}

		public void setBar( @SuppressWarnings( "unused" ) String bar ) {

			// Do nothing
		}

		@UiComesAfter( "bar" )
		@UiSection( "tab1" )
		public boolean isBaz() {

			return false;
		}

		public void setBaz( @SuppressWarnings( "unused" ) boolean baz ) {

			// Do nothing
		}

		@UiComesAfter( "baz" )
		public String getAbc() {

			return null;
		}

		public void setAbc( @SuppressWarnings( "unused" ) String abc ) {

			// Do nohting
		}

		@UiSection( "tab2" )
		public String getDef() {

			return null;
		}

		public void setDef( @SuppressWarnings( "unused" ) String def ) {

			// Do nothing
		}

		@UiSection( "" )
		@UiLookup( { "foo", "bar" } )
		public String getGhi() {

			return null;
		}

		public void setGhi( @SuppressWarnings( "unused" ) String ghi ) {

			// Do nothing
		}

		@UiSection( "tabA" )
		public String getJkl() {

			return null;
		}

		public void setJkl( @SuppressWarnings( "unused" ) String jkl ) {

			// Do nothing
		}
	}
}
