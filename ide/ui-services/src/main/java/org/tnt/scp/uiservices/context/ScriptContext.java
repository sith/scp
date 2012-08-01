package org.tnt.scp.uiservices.context;


import com.google.common.collect.Lists;
import org.tnt.scp.common.generated.Id;
import org.tnt.scp.common.generated.SceneRef;
import org.tnt.scp.common.generated.Script;

import java.util.*;

public class ScriptContext {
    private final Script script;
    private final Map<Id, SceneContext> sceneContexts;


    public ScriptContext(Script script) {
        this.script = script;
        this.sceneContexts = new HashMap<Id, SceneContext>();

        List<SceneRef> sceneRefs = script.getScenesRef().getScenesRef();
        for (SceneRef sceneRef : sceneRefs) {
            sceneContexts.put(sceneRef.getScenRef(), new SceneContext(sceneRef, script.getId()));
        }

    }

    public Script getScript() {
        return script;
    }

    public SceneContext getSceneContextById(Id id) {
        return sceneContexts.get(id);
    }

    public List<SceneContext> getSceneContexts() {
        ArrayList<SceneContext> sortedList = Lists.newArrayList(sceneContexts.values());
        Collections.sort(sortedList, new Comparator<SceneContext>() {
            @Override
            public int compare(SceneContext o1, SceneContext o2) {
                return o1.getSceneRef().getIndex().getValue() - o2.getSceneRef().getIndex().getValue();
            }
        });
        return sortedList;
    }

    public void updateSceneContext(SceneContext sceneContext) {
        sceneContexts.put(sceneContext.getSceneRef().getScenRef(), sceneContext);
    }

}
