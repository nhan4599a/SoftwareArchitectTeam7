package vlu.architect.team7.def_garage.def_garage_service.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vlu.architect.team7.def_garage.def_garage_service.Model.BusSeat;
import vlu.architect.team7.def_garage.def_garage_service.Model.CompositeBusSeatPK;

@Repository
public interface BusSeatDAO extends CrudRepository<BusSeat, CompositeBusSeatPK> {
}
