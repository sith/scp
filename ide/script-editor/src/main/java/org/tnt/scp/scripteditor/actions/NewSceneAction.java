/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tnt.scp.scripteditor.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "Scripts/Editor",
id = "org.tnt.scp.scripteditor.actions.NewSceneAction")
@ActionRegistration(iconBase = "org/tnt/scp/scripteditor/actions/new_scene.png",
displayName = "#CTL_NewSceneAction")
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 1350),
    @ActionReference(path = "Toolbars/ScriptsEditor", position = 3433),
    @ActionReference(path = "Shortcuts", name = "DO-S")
})
@Messages("CTL_NewSceneAction=New Scene")
public final class NewSceneAction extends  AbstractAction {

    public void actionPerformed(ActionEvent e) {
        // TODO implement action body
    }
}
