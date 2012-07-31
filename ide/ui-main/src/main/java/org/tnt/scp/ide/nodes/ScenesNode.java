package org.tnt.scp.ide.nodes;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.tnt.scp.common.generated.Scene;
import org.tnt.scp.common.generated.Script;


public class ScenesNode extends AbstractNode {

    private Script script;

    public ScenesNode(Script script) {
        super(Children.LEAF);
        this.script = script;
        setIconBaseWithExtension("org/tnt/scp/ide/types/scene_type.png");
        setName("Scenes");
    }
}
