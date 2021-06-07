package vlu.architect.team7.partner.futa.service.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vlu.architect.team7.partner.futa.service.Model.BusTrain;

@Repository
public interface BusTrainDAO extends CrudRepository<BusTrain, Integer> {
}
