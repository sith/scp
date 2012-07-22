/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tnt.scp.ide.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.tnt.scp.uiservices.service.ScriptService;

@ActionID(category = "Scripts",
id = "org.tnt.scp.ide.actions.ImportScriptAction")
@ActionRegistration(iconBase = "org/tnt/scp/ide/actions/import_script.png",
displayName = "#CTL_ImportScriptAction")
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 1750),
    @ActionReference(path = "Toolbars/File", position = 300),
    @ActionReference(path = "Shortcuts", name = "DO-I")
})
@Messages("CTL_ImportScriptAction=Import Script")
public final class ImportScriptAction implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        ScriptService scriptService = Lookup.getDefault().lookup(ScriptService.class);
//        scriptService.importScript
    }
}
