package org.tnt.scp.uiservices.events;

import org.tnt.scp.common.generated.SceneRef;

public class RemoveSceneEvent extends AbstractEvent<SceneRef> {
    public RemoveSceneEvent(SceneRef target) {
        super(target);
    }
}
