package org.tnt.scp.uiservices.events;

import org.tnt.scp.common.generated.ScriptType;


public class AddScriptEvent extends AbstractEvent<ScriptType> {
    public AddScriptEvent(ScriptType target) {
        super(target);
    }
}
