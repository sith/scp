package org.tnt.scp.data.repository;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tnt.scp.domain.Project;
import org.tnt.scp.services.ProjectService;

import javax.persistence.EntityManager;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class ProjectRepository extends SimpleJpaRepository<Project,Long> implements ProjectService {
    public ProjectRepository(Class<Project> domainClass, EntityManager em) {
        super(domainClass, em);
    }
}
