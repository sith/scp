package org.tnt.scp.ide.nodes;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.tnt.scp.common.generated.Scene;
import org.tnt.scp.common.generated.SceneRef;


public class SceneNode extends AbstractNode {

    public SceneNode(Scene scene) {
        super(Children.LEAF);
        setIconBaseWithExtension("org/tnt/scp/ide/types/scene_type.png");
        setName(scene.getId().getValue());
    }

    @Deprecated
    public SceneNode(SceneRef scene) {
        super(Children.LEAF);
        setIconBaseWithExtension("org/tnt/scp/ide/types/scene_type.png");
        setName(scene.getScenRef().getValue());
    }
}
