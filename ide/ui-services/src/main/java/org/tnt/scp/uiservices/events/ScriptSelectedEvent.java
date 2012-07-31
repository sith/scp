package org.tnt.scp.uiservices.events;

import org.tnt.scp.common.generated.Script;


public class ScriptSelectedEvent extends AbstractEvent<Script> {
    public ScriptSelectedEvent(Script target) {
        super(target);
    }
}
