/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tnt.scp.scripteditor.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import javax.swing.*;

import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.filesystems.FileUtil;
import org.openide.util.Lookup;

import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.Lookups;
import org.tnt.scp.uiservices.service.ScriptService;

@ActionID(category = "Scripts/Editor",
        id = "org.tnt.scp.scripteditor.NewScriptAction")
@ActionRegistration(iconBase = "org/tnt/scp/scripteditor/new_script.png",
        displayName = "#CTL_NewScript")
@ActionReferences({
        @ActionReference(path = "Menu/File", position = 1300),
        @ActionReference(path = "Toolbars/ScriptsEditor", position = 3333),
        @ActionReference(path = "Shortcuts", name = "DO-N")
})
@Messages("CTL_NewScript=New Script")
public final class NewScriptAction extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent e) {

        ScriptService scriptService = Lookup.getDefault().lookup(ScriptService.class);

        NotifyDescriptor.InputLine dialog = new NotifyDescriptor.InputLine(
                "New Script",
                "Please enter script name");

        Object notify = DialogDisplayer.getDefault().notify(dialog);

        if (NotifyDescriptor.OK_OPTION == notify) {
            scriptService.createScript(dialog.getInputText());
        }


    }
}
