package org.tnt.scp.ide.nodes;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.tnt.scp.common.generated.SceneType;


public class SceneNode extends AbstractNode {

    public SceneNode(SceneType scene) {
        super(Children.LEAF);
        setIconBaseWithExtension("org/tnt/scp/ide/types/scene_type.png");
        setName(scene.getName());
    }
}
