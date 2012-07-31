package org.tnt.scp.uiservices.events;

import org.tnt.scp.common.generated.CharacterType;
import org.tnt.scp.common.generated.Script;

public class AddCharacterEvent extends AbstractEvent<CharacterType> {
    private Script script;

    public AddCharacterEvent(CharacterType target,Script script) {
        super(target);
        this.script = script;
    }

    public Script getScript() {
        return script;
    }
}
