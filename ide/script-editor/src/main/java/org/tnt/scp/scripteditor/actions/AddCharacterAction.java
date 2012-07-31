/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tnt.scp.scripteditor.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.commons.lang.StringUtils;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.*;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.tnt.scp.common.generated.Script;
import org.tnt.scp.scripteditor.panels.AddCharacterPanel;
import org.tnt.scp.scripteditor.panels.AddScenePanel;
import org.tnt.scp.uiservices.service.GlobalContext;
import org.tnt.scp.uiservices.service.ScriptService;

@ActionID(
        category = "Scripts/Editor",
        id = "org.tnt.scp.scripteditor.actions.AddCharacterAction")
@ActionRegistration(
        iconBase = "org/tnt/scp/scripteditor/actions/add_character.png",
        displayName = "#CTL_AddCharacterAction")
@ActionReferences({
        @ActionReference(path = "Menu/File", position = 1375),
        @ActionReference(path = "Toolbars/ScriptsEditor", position = 3533),
        @ActionReference(path = "Shortcuts", name = "DO-C")
})
@Messages("CTL_AddCharacterAction=Add Character")
public final class AddCharacterAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        GlobalContext.AwareSerice awareSerice = Lookup.getDefault().lookup(GlobalContext.AwareSerice.class);

        final AddCharacterPanel panel = new AddCharacterPanel(awareSerice.openedScripts());

        final DialogDescriptor d = new DialogDescriptor(panel, "Add New Scene", true, null);
        d.setClosingOptions(null);
        d.setButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() != NotifyDescriptor.OK_OPTION) {
                    return;
                }
                Notification notify = null;
                if (StringUtils.isEmpty(panel.getCharacterName())) {
                    d.setClosingOptions(new Object[]{});
                    NotificationDisplayer.getDefault().notify(
                            "Empty character name",
                            ImageUtilities.loadImageIcon("org/tnt/scp/scripteditor/panels/warning.png", true),
                            "Character name can't be an empty",
                            null);


                } else {
                    d.setClosingOptions(null);
                }
            }
        });


        Object notify = DialogDisplayer.getDefault().notify(d);

        if (notify == NotifyDescriptor.OK_OPTION) {
            ScriptService scriptService = Lookup.getDefault().lookup(ScriptService.class);
            scriptService.createCharacter(panel.getCharacterName(), panel.getDescription(), panel.getSelectedScript());


        }
    }
}
