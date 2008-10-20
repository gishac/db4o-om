/* Copyright (C) 2004 - 2007 db4objects Inc. http://www.db4o.com */
/**
 * User: gishac
 * Date: Apr 14, 2008
 * Time: 10:02:14 PM
 */
package com.db4o.objectManager.v2.uiHelper;

import java.awt.*;

public class UIHelper {
	
	public static void CenterFormInScreen(Component component, Toolkit toolkit){
        Dimension dimension = component.getSize();
        Dimension screenSize = toolkit.getScreenSize();
        component.setLocation(
                (screenSize.width - dimension.width) / 2,
                (screenSize.height - dimension.height) / 2);
    }

}
