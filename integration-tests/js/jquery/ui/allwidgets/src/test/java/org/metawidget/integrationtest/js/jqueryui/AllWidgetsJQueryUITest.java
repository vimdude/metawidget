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

package org.metawidget.integrationtest.js.jqueryui;

import org.metawidget.util.JavaScriptTestCase;

/**
 * @author <a href="http://kennardconsulting.com">Richard Kennard</a>
 */

public class AllWidgetsJQueryUITest
	extends JavaScriptTestCase {

	//
	// Public methods
	//

	public void testAllWidgets()
		throws Exception {

		evaluateHtml( "target/allwidgets-jqueryui/index.html" );
		run( "src/test/js/allwidgets-jqueryui-tests.js" );
	}
}