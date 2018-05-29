package br.com.database.stay;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StayRepo extends CrudRepository<StayDef, StayId> {

}
