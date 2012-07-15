package org.tnt.scp;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tnt.scp.services.ProjectService;
import org.junit.Test;
import org.tnt.scp.domain.Project;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:scp-context.xml")
public class SimpleTest {

    @Autowired
    ProjectService projectService;

    @Test
    public void test2() throws Exception {
        Project project = new Project();
        projectService.save(project);
        Iterable<Project> projects = projectService.findAll();

        for (Project project1 : projects) {
            System.out.println(project1);
        }
    }
}
