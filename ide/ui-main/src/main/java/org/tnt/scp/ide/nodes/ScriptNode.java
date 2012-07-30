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
    private static Children children = new Children.Array();

    public ScriptNode(Script script) {
        super(children);
        this.script = script;
        setIconBaseWithExtension("org/tnt/scp/ide/types/script_type.png");
        setName(script.getName());

        ArrayList<SceneNode> nodes = Lists.newArrayList();

        for (SceneRef sceneRef : script.getScenesRef().getScenesRef()) {
            nodes.add(new SceneNode(sceneRef));

        }

        children.add(nodes.toArray(new Node[nodes.size()]));
    }

    public void addScene(Scene sceneRef) {
        children.add(new Node[]{new SceneNode(sceneRef)});
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
