package org.tnt.scp.uiservices.events;

import org.tnt.scp.common.generated.Scene;

public class AddSceneEvent extends AbstractEvent<Scene> {

    public AddSceneEvent(Scene target) {
        super(target);
    }
}
