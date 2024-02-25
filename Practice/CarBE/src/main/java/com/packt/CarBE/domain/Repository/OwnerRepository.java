package com.packt.CarBE.domain.Repository;

import com.packt.CarBE.domain.Entity.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
