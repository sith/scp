package org.tnt.scp.ide.nodes;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.tnt.scp.common.generated.Script;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;


public class CharactersNode extends AbstractNode {

    private Script script;

    public CharactersNode(Script script) {
        super(Children.LEAF);
        this.script = script;
        setName("Characters");
        setIconBaseWithExtension("org/tnt/scp/ide/types/characters.png");
    }
}
