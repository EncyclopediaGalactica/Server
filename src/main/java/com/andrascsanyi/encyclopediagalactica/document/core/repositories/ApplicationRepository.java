package com.andrascsanyi.encyclopediagalactica.document.core.repositories;

import com.andrascsanyi.encyclopediagalactica.document.core.entities.ApplicationEntity;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationRepository extends CrudRepository<ApplicationEntity, Long> {
}
