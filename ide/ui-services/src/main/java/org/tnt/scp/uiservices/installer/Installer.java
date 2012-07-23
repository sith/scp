/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tnt.scp.uiservices.installer;

import org.openide.modules.ModuleInstall;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Installer extends ModuleInstall {

    @Override
    public void restored() {
        new ClassPathXmlApplicationContext("classpath:services-module-context.xml");
    }


}
