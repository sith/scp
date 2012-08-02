package org.tnt.scp.ide.nodes;

import org.openide.actions.DeleteAction;
import org.openide.cookies.EditCookie;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.Lookup;
import org.openide.util.actions.SystemAction;
import org.tnt.scp.uiservices.context.SceneContext;
import org.tnt.scp.uiservices.cookies.Removable;
import org.tnt.scp.uiservices.service.ScriptService;
import org.tnt.scp.uiservices.service.impl.ScriptServiceImpl;

import javax.swing.*;


public class SceneNode extends AbstractNode implements Removable {

    private SceneContext sceneRef;

    public SceneNode(SceneContext scene) {
        super(Children.LEAF);
        sceneRef = scene;
        setIconBaseWithExtension("org/tnt/scp/ide/types/scene_type.png");
        setName(scene.getSceneRef().getShortName());


        getCookieSet().assign(EditCookie.class);
    }


    public SceneContext getSceneRef() {
        return sceneRef;
    }

    @Override
    public void remove() {
        ScriptService scriptService = Lookup.getDefault().lookup(ScriptService.class);
        scriptService.removeScene(sceneRef.getSceneRef(),sceneRef.getScriptId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        SceneNode sceneNode = (SceneNode) o;

        if (!sceneRef.equals(sceneNode.sceneRef)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + sceneRef.hashCode();
        return result;
    }
}
