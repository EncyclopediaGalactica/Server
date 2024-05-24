package com.andrascsanyi.encyclopediagalactica.document.repositories;

import com.andrascsanyi.encyclopediagalactica.document.entities.ApplicationEntity;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationRepository extends CrudRepository<ApplicationEntity, Long> {
}
