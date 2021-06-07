package vlu.architect.team7.partner.vexere.service.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vlu.architect.team7.partner.vexere.service.Model.BusSeat;
import vlu.architect.team7.partner.vexere.service.Model.CompositeBusSeatPK;

@Repository
public interface BusSeatDAO extends CrudRepository<BusSeat, CompositeBusSeatPK> {
}
