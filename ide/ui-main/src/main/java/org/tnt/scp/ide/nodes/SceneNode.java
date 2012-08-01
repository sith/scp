package org.tnt.scp.ide.nodes;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.tnt.scp.uiservices.context.SceneContext;


public class SceneNode extends AbstractNode {

    private SceneContext sceneRef;

    public SceneNode(SceneContext scene) {
        super(Children.LEAF);
        sceneRef = scene;
        setIconBaseWithExtension("org/tnt/scp/ide/types/scene_type.png");
        setName(scene.getSceneRef().getShortName());
    }

    public SceneContext getSceneRef() {
        return sceneRef;
    }
}
