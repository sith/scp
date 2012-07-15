package org.tnt.scp.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "PROJECTS")
public class Project implements Serializable {

    private String id;
    private List<Script> scripts;

    @Id
    @GeneratedValue
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "project",fetch = FetchType.EAGER)
    public List<Script> getScripts() {
        return scripts;
    }

    public void setScripts(List<Script> scripts) {
        this.scripts = scripts;
    }


    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", scripts=" + scripts +
                '}';
    }
}
