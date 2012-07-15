package org.tnt.scp.services;

import org.springframework.data.repository.CrudRepository;
import org.tnt.scp.domain.Project;

public interface ProjectService extends CrudRepository<Project, Long> {
}
