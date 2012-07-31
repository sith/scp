/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tnt.scp.ide.nodes;

import com.google.common.collect.Lists;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.tnt.scp.common.generated.Scene;
import org.tnt.scp.common.generated.SceneRef;
import org.tnt.scp.common.generated.Script;

import java.util.ArrayList;

/**
 * @author sith
 */
public class ScriptNode extends AbstractNode {

    private final Script script;


    public ScriptNode(Script script) {
        super(new Children.Array());
        this.script = script;
        setIconBaseWithExtension("org/tnt/scp/ide/types/script_type.png");
        setName(script.getName());

        getChildren().add(new Node[]{new ScenesNode(script), new CharactersNode(script)});

    }
    public Script getScript() {
        return script;
    }
}
