package org.tnt.scp.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SCRIPTS")
public class Script {

    private String id;
    private Project project;
    private ScriptType type;
    private List<Scene> scenes;

    @Id
    @GeneratedValue
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PROJECT_ID")
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }


    @Enumerated(EnumType.STRING)
    public ScriptType getType() {
        return type;
    }

    public void setType(ScriptType type) {
        this.type = type;
    }

    @OneToMany
    public List<Scene> getScenes() {
        return scenes;
    }

    public void setScenes(List<Scene> scenes) {
        this.scenes = scenes;
    }
}
