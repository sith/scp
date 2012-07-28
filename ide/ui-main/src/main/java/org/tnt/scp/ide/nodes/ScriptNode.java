/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tnt.scp.ide.nodes;

import com.google.common.collect.Lists;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.tnt.scp.common.generated.SceneType;
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

        ArrayList<SceneNode> nodes = Lists.newArrayList();

        for (SceneType scene : script.getScenes().getScene()) {
            nodes.add(new SceneNode(scene));
        }

        Children children = getChildren();
        children.add(nodes.toArray(new Node[nodes.size()]));
    }

//    public Image getIcon(int type) {
//        return Utilities.loadImage("org/netbeans/myfirstexplorer/right-rectangle.png");
//    }
//    
//    public Image getOpenedIcon(int type) {
//        return Utilities.loadImage("org/netbeans/myfirstexplorer/down-rectangle.png");
//    }


    public Script getScript() {
        return script;
    }
}
