package org.tnt.scp.data.repository;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tnt.scp.domain.Project;
import org.tnt.scp.domain.Scene;
import org.tnt.scp.services.ProjectService;
import org.tnt.scp.services.SceneService;

import javax.persistence.EntityManager;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class SceneRepository extends SimpleJpaRepository<Scene,Long> implements SceneService {
    public SceneRepository(Class<Scene> domainClass, EntityManager em) {
        super(domainClass, em);
    }
}
