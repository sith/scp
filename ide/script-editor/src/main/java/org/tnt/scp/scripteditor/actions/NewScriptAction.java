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

import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.filesystems.FileUtil;
import org.openide.util.Lookup;

import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.Lookups;

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

//
//    public void actionPerformed(ActionEvent e) {
//        // TODO implement action body
//        Lookup lookup = Lookups.forPath("Action/Scripts/Editor");
//        
//        Collection<? extends Action> lookupAll = lookup.lookupAll(Action.class);
//        for (Action action : lookupAll) {
//            System.out.println(action);
//        }
//     
//    }
    @Override
    public void actionPerformed(ActionEvent e) {


        Lookup lookup = Lookups.forPath("Actions/Scripts/Editor");

        Collection<? extends Action> lookupAll = lookup.lookupAll(Action.class);
        for (Action action : lookupAll) {
            System.out.println(action);
        }
    }
}
