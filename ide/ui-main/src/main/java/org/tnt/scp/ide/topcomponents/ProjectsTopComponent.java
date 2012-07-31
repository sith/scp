/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tnt.scp.ide.topcomponents;

import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.explorer.view.BeanTreeView;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;
import org.tnt.scp.common.generated.Id;
import org.tnt.scp.common.generated.Scene;
import org.tnt.scp.common.generated.Script;
import org.tnt.scp.ide.nodes.ChildrenMap;
import org.tnt.scp.ide.nodes.RootNode;
import org.tnt.scp.ide.nodes.ScriptNode;
import org.tnt.scp.uiservices.events.AddSceneEvent;
import org.tnt.scp.uiservices.events.AddScriptEvent;
import org.tnt.scp.uiservices.events.ScriptSelectedEvent;
import org.tnt.scp.uiservices.service.EventSystemService;
import org.tnt.scp.uiservices.service.ScriptService;
import org.tnt.scp.uiservices.service.SystemEventListener;

import javax.swing.tree.TreeSelectionModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.List;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//org.tnt.scp.ide.topcomponents//Projects//EN",
        autostore = false)
@TopComponent.Description(preferredID = "ProjectsTopComponent",
        iconBase = "org/tnt/scp/ide/topcomponents/folder-green.png",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "explorer", openAtStartup = true)
@ActionID(category = "Window", id = "org.tnt.scp.ide.topcomponents.ProjectsTopComponent")
@ActionReference(path = "Menu/Window" /*
 * , position = 333
 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_ProjectsAction",
        preferredID = "ProjectsTopComponent")
@Messages({
        "CTL_ProjectsAction=Projects",
        "CTL_ProjectsTopComponent=Projects Window",
        "HINT_ProjectsTopComponent=This is a Projects window"
})
public final class ProjectsTopComponent extends TopComponent implements ExplorerManager.Provider {
    private final ExplorerManager manager = new ExplorerManager();


    public ProjectsTopComponent() {

        initComponents();
        setName(Bundle.CTL_ProjectsTopComponent());
        setToolTipText(Bundle.HINT_ProjectsTopComponent());
        putClientProperty(TopComponent.PROP_CLOSING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_DRAGGING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_MAXIMIZATION_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_SLIDING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_UNDOCKING_DISABLED, Boolean.TRUE);


        final EventSystemService.ProducerService eventProducerService = Lookup.getDefault().lookup(EventSystemService.ProducerService.class);
        final EventSystemService.SubscriberService subscriberService = Lookup.getDefault().lookup(EventSystemService.SubscriberService.class);
        subscriberService.subscribeAddScriptEvent(new SystemEventListener<AddScriptEvent>() {
            @Override
            public void onEvent(AddScriptEvent event) {
                ScriptNode scriptNode = new ScriptNode(event.getTarget());
                manager.getRootContext().getChildren().add(new Node[]{scriptNode});
            }
        });
        subscriberService.subscribeAddSceneEvent(new SystemEventListener<AddSceneEvent>() {
            @Override
            public void onEvent(AddSceneEvent event) {

                Children children = manager.getRootContext().getChildren();

                Node[] nodes = children.getNodes();
                for (Node node : nodes) {

                }
            }
        });


        initTree();
        initActions();
        Lookup lookup = ExplorerUtils.createLookup(manager, getActionMap());

        Lookup.Result<ScriptNode> scriptNodesResult = lookup.lookupResult(ScriptNode.class);
        scriptNodesResult.addLookupListener(new LookupListener() {
            @Override
            public void resultChanged(LookupEvent ev) {
                Lookup.Result<Node> res = (Lookup.Result<Node>) ev.getSource();
                Collection<ScriptNode> scriptNodes = (Collection<ScriptNode>) res.allInstances();
                eventProducerService.selectScript(new ScriptSelectedEvent(scriptNodes.iterator().next().getScript()));

            }
        });




        associateLookup(lookup);


    }

    private BeanTreeView createTreeView() {
        BeanTreeView beanTreeView = new BeanTreeView();
        beanTreeView.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        return beanTreeView;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        projectsPane = createTreeView();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(projectsPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(projectsPane, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane projectsPane;

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

    private void initTree() {


        /*Node[] nodes = new Node[scriptTypes.size()];
        for (Script scriptType : scriptTypes) {
            childrenMap.add(scriptType.getId(), new ScriptNode(scriptType));
        }*/

        ChildrenMap childrenMap = new ChildrenMap();
        RootNode value = new RootNode(childrenMap);
        manager.setRootContext(value);

        List<Script> scriptTypes = Lookup.getDefault().lookup(ScriptService.class).loadScripts();
    }

    private void initActions() {

    }


    @Override
    public ExplorerManager getExplorerManager() {
        return manager;
    }

}
