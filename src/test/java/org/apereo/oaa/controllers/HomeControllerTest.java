/**
 * Copyright 2013 Unicon (R) Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.apereo.oaa.controllers;

import junit.framework.Assert;

import org.apereo.oaa.controllers.HomeController;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

public class HomeControllerTest {

    @Test
    public void testController() {
        HomeController controller = new HomeController();
        Model model = new ExtendedModelMap();
        Assert.assertEquals("home", controller.home(model));

        Object message = model.asMap().get("controllerMessage");
        Assert.assertEquals("This is the message from the controller!", message);

    }
}
