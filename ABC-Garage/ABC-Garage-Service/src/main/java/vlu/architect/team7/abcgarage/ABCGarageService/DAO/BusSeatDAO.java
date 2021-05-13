package vlu.architect.team7.abcgarage.ABCGarageService.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vlu.architect.team7.abcgarage.ABCGarageService.Model.BusSeat;
import vlu.architect.team7.abcgarage.ABCGarageService.Model.CompositeBusSeatPK;

@Repository
public interface BusSeatDAO extends CrudRepository<CompositeBusSeatPK, BusSeat> {
}
