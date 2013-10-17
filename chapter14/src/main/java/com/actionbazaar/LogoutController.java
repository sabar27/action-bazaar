/**
 *  SelectedItem.java
 *  EJB 3 in Action
 *  Book: http://manning.com/panda2/
 *  Code: http://code.google.com/p/action-bazaar/
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.actionbazaar;

import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author rcuprak
 */
@Named
@RequestScoped
public class LogoutController {
    
    /**
     * Logger
     */
    private static final Logger logger = Logger.getLogger("LogoutController");
    
    /**
     * Injects the FacesContext
     */
    @Inject
    private FacesContext facesContext;
    
    /**
     * Invalidates the current session
     * @return login page
     */
    public String logout() {
        logger.info("Performing logout..");
        facesContext.getExternalContext().invalidateSession();
        return NavigationRules.HOME.getRule();
    }
    
}
