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

package org.metawidget.inspector.xsd;

import java.io.InputStream;

import org.metawidget.inspector.impl.BaseXmlInspectorConfig;

/**
 * Configures an XmlSchemaInspector prior to use. Once instantiated, Inspectors are immutable.
 *
 * @author <a href="http://kennardconsulting.com">Richard Kennard</a>
 */

public class XmlSchemaInspectorConfig
	extends BaseXmlInspectorConfig {

	//
	// Public methods
	//

	/**
	 * Overridden to provide a covariant return type for our fluent interface.
	 */

	@Override
	public XmlSchemaInspectorConfig setInputStream( InputStream stream ) {

		return (XmlSchemaInspectorConfig) super.setInputStream( stream );
	}
}