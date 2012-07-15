package org.tnt.scp.domain;

import javax.persistence.*;

@Entity
@Table(name = "SCENES")
public class Scene {
    private String id;
    private Script script;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "SCRIPT_ID")
    public Script getScript() {
        return script;
    }

    public void setScript(Script script) {
        this.script = script;
    }
}
