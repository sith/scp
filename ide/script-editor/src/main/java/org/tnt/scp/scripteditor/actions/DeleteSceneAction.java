/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tnt.scp.scripteditor.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;
import org.tnt.scp.uiservices.cookies.Removable;

import javax.swing.*;

@ActionID(
        category = "Scripts/ScenesToolbar",
        id = "org.tnt.scp.scripteditor.actions.DeleteSceneAction")
@ActionRegistration(
        iconBase = "org/tnt/scp/scripteditor/actions/delete_scene.png",
        displayName = "#CTL_DeleteScene")
@Messages("CTL_DeleteScene=Delete Scene")
@ActionReferences({
        @ActionReference(path = "Actions/Scripts/ScenesToolbar", position = 200)
})
public final class DeleteSceneAction extends AbstractAction {

    private Removable removable;

    public DeleteSceneAction(Removable removable) {
        this.removable = removable;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        removable.remove();
    }
}
