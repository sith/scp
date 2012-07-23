package org.tnt.scp.ide.nodes;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;

public class RootNode extends AbstractNode {

    public RootNode(Children children) {
        super(children);
        setIconBaseWithExtension("org/tnt/scp/ide/types/folder_scripts.png");
        setDisplayName("Scripts");
    }

}
