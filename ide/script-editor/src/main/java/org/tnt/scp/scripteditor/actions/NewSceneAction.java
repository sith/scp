/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tnt.scp.scripteditor.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import javax.swing.AbstractAction;

import org.apache.commons.lang.StringUtils;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.tnt.scp.common.generated.Script;
import org.tnt.scp.scripteditor.panels.AddScenePanel;
import org.tnt.scp.uiservices.service.EntryStatisticsService;
import org.tnt.scp.uiservices.service.ScriptService;

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
public final class NewSceneAction extends AbstractAction {

    public void actionPerformed(ActionEvent e) {
        EntryStatisticsService.AwareSerice awareSerice = Lookup.getDefault().lookup(EntryStatisticsService.AwareSerice.class);
        Lookup lookup = awareSerice.openedScriptsLookup();

        Collection<? extends Script> scripts = lookup.lookupAll(Script.class);
        final AddScenePanel panel = new AddScenePanel(scripts);

        final DialogDescriptor d = new DialogDescriptor(panel, "Add New Scene", true, null);
        d.setClosingOptions(null);
        d.setButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() != NotifyDescriptor.OK_OPTION) {
                    return;
                }
                if (StringUtils.isEmpty(panel.getSceneName())) {
                    d.setClosingOptions(new Object[]{});
                    panel.showError();
                } else {
                    d.setClosingOptions(null);
                }
            }
        });
        Object notify = DialogDisplayer.getDefault().notify(d);

        if (notify == NotifyDescriptor.OK_OPTION) {
            ScriptService scriptService = Lookup.getDefault().lookup(ScriptService.class);
            scriptService.createScene(panel.getSelectedScript(), panel.getSceneName());
        }


    }
}
