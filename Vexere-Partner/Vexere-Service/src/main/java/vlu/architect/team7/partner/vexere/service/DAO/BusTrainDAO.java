package vlu.architect.team7.partner.vexere.service.DAO;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import vlu.architect.team7.partner.vexere.service.Model.BusTrain;

@Repository
public interface BusTrainDAO extends PagingAndSortingRepository<BusTrain, Integer> {
}
