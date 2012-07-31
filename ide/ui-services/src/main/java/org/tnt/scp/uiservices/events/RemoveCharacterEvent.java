package org.tnt.scp.uiservices.events;

import org.tnt.scp.common.generated.CharacterType;
import org.tnt.scp.common.generated.Id;
import org.tnt.scp.common.generated.Script;


public class RemoveCharacterEvent extends AbstractEvent<Id> {
    private Script script;

    public RemoveCharacterEvent(Id target, Script script) {
        super(target);
        this.script = script;
    }

    public Script getScript() {
        return script;
    }
}
