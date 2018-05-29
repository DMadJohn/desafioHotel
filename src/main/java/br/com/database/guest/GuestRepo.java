package br.com.database.guest;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepo extends CrudRepository<GuestDef, GuestId> {
}
