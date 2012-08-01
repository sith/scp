package org.tnt.scp.uiservices.context;


import org.tnt.scp.common.generated.Id;
import org.tnt.scp.common.generated.SceneRef;

public class SceneContext {

    private SceneRef sceneRef;
    private Id scriptId;

    public SceneContext(SceneRef sceneRef, Id scriptId) {
        this.sceneRef = sceneRef;
        this.scriptId = scriptId;
    }

    public SceneRef getSceneRef() {
        return sceneRef;
    }

    public Id getScriptId() {
        return scriptId;
    }
}
