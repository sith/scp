package org.tnt.scp.uiservices.service.impl;


import org.tnt.scp.common.generated.Script;

import java.util.List;

public class ScriptContext {
    private final Script script;
    private final List<SceneContext> sceneContexts;


    public ScriptContext(Script script, List<SceneContext> sceneContexts) {
        this.script = script;
        this.sceneContexts = sceneContexts;
    }

    public Script getScript() {
        return script;
    }

    public List<SceneContext> getSceneContexts() {
        return sceneContexts;
    }
}
