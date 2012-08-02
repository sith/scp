/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tnt.scp.ide.topcomponents;

import com.google.common.collect.Lists;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.explorer.view.ListView;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;
import org.openide.windows.TopComponent;
import org.tnt.scp.common.generated.Id;
import org.tnt.scp.ide.nodes.SceneNode;
import org.tnt.scp.uiservices.context.SceneContext;
import org.tnt.scp.uiservices.context.ScriptContext;
import org.tnt.scp.uiservices.events.AddSceneEvent;
import org.tnt.scp.uiservices.events.RemoveSceneEvent;
import org.tnt.scp.uiservices.events.ScriptSelectedEvent;
import org.tnt.scp.uiservices.service.EventSystemService;
import org.tnt.scp.uiservices.service.GlobalContext;
import org.tnt.scp.uiservices.service.SystemEventListener;

import java.awt.*;
import java.beans.PropertyVetoException;
import java.util.*;

import org.openide.nodes.AbstractNode;

import javax.swing.*;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//org.tnt.scp.ide.topcomponents//Scenes//EN",
        autostore = false)
@TopComponent.Description(
        preferredID = "ScenesTopComponent",
        iconBase = "org/tnt/scp/ide/topcomponents/scenes_folder.png",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "leftSlidingSide", openAtStartup = true)
@ActionID(category = "Window", id = "org.tnt.scp.ide.topcomponents.ScenesTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_ScenesAction",
        preferredID = "ScenesTopComponent")
@Messages({
        "CTL_ScenesAction=Scenes",
        "CTL_ScenesTopComponent=Scenes",
        "HINT_ScenesTopComponent=This is a Scenes window"
})
public final class ScenesTopComponent extends TopComponent implements ExplorerManager.Provider {
    private final Map<Id, ScenesRootNode> rootNodes = new HashMap<Id, ScenesRootNode>();
    private ExplorerManager explorerManager = new ExplorerManager();
    private BorderLayout mainPanelLayout;

    public ScenesTopComponent() {
        initComponents();
        setName(Bundle.CTL_ScenesTopComponent());
        setToolTipText(Bundle.HINT_ScenesTopComponent());
        putClientProperty(TopComponent.PROP_MAXIMIZATION_DISABLED, Boolean.TRUE);
        final GlobalContext.AwareService globalContextAware =
                Lookup.getDefault().lookup(GlobalContext.AwareService.class);

        final EventSystemService.SubscriberService subscriberService = Lookup.getDefault().lookup(EventSystemService.SubscriberService.class);
        subscriberService.subscribeScriptSelectedEvent(new SystemEventListener<ScriptSelectedEvent>() {
            @Override
            public void onEvent(ScriptSelectedEvent event) {
                Id id = event.getTarget().getId();
                ScenesRootNode rootRootNode = rootNodes.get(id);
                if (rootRootNode == null) {
                    ScriptContext scriptContext = globalContextAware.findScriptContext(id);
                    Collection<Node> scenes = Lists.newArrayList();
                    for (SceneContext scene : scriptContext.getSceneContexts()) {
                        SceneNode sceneNode = new SceneNode(scene);
                        scenes.add(sceneNode);
                    }

                    Children.Array children = new Children.Array();
                    children.add(scenes.toArray(new Node[scenes.size()]));

                    rootRootNode = new ScenesRootNode(children, scriptContext);

                    rootNodes.put(id, rootRootNode);
                }

                explorerManager.setRootContext(rootRootNode);


            }
        });

        subscriberService.subscribeRemoveSceneEvent(new SystemEventListener<RemoveSceneEvent>() {
            @Override
            public void onEvent(RemoveSceneEvent event) {
                ScenesRootNode rootContext = (ScenesRootNode) explorerManager.getRootContext();
                Children children = rootContext.getChildren();


                for (int i = 0; i < children.getNodesCount(); i++) {
                    SceneNode nodeAt = (SceneNode) children.getNodeAt(i);
                    if (nodeAt.getSceneRef().getSceneRef().getScenRef().equals(event.getTarget().getScenRef())) {
                        children.remove(new Node[]{nodeAt});
                        break;
                    }
                }
                try {
                    explorerManager.setSelectedNodes(new Node[]{explorerManager.getRootContext()});
                } catch (PropertyVetoException e) {
                    throw new IllegalStateException(e);
                }

//                children.remove(new Node[]{new SceneNode(new SceneContext(event.getTarget(), rootContext.getScriptContext().getScript().getId()))});

            }
        });
        subscriberService.subscribeAddSceneEvent(new SystemEventListener<AddSceneEvent>() {
            @Override
            public void onEvent(AddSceneEvent event) {

                Id scriptRef = event.getTarget().getScriptRef();
                ScenesRootNode abstractRootNode = rootNodes.get(scriptRef);
                if (abstractRootNode == null) {
                    return;
                }
                ScriptContext scriptContext = globalContextAware.findScriptContext(scriptRef);
                if (abstractRootNode.getScriptContext().getScript().getId().equals(scriptRef)) {
                    abstractRootNode.getChildren().add(new Node[]{new SceneNode(scriptContext.getSceneContextById(event.getTarget().getId()))});
                }
            }
        });


        java.util.List<? extends Action> alist = Utilities.actionsForPath("Actions/Scripts/ScenesToolbar/");
        for (Action action : alist) {
            scenesToolbar.add(action);
        }
        associateLookup(ExplorerUtils.createLookup(explorerManager, getActionMap()));


    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scenesToolbar = new javax.swing.JToolBar();
        explorerPane = createPane();

        scenesToolbar.setRollover(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(scenesToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                        .addComponent(explorerPane)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(scenesToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(explorerPane, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private JScrollPane createPane() {
        ListView listView = new ListView();
        listView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return listView;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane explorerPane;
    private javax.swing.JToolBar scenesToolbar;

    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    @Override
    public ExplorerManager getExplorerManager() {
        return explorerManager;
    }

    private static class ScenesRootNode extends AbstractNode {
        private ScriptContext scriptContext;

        public ScenesRootNode(Children.Array children, ScriptContext scriptContext) {
            super(children);
            this.scriptContext = scriptContext;
        }

        public ScriptContext getScriptContext() {
            return scriptContext;
        }
    }

}
