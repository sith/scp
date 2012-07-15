package org.tnt.scp.services;

import org.springframework.data.repository.CrudRepository;
import org.tnt.scp.domain.Scene;

public interface SceneService extends CrudRepository<Scene, Long> {
}
