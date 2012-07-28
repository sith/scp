package org.tnt.scp.uiservices.events;

import org.tnt.scp.common.generated.Script;


public class AddScriptEvent extends AbstractEvent<Script> {
    public AddScriptEvent(Script target) {
        super(target);
    }
}
