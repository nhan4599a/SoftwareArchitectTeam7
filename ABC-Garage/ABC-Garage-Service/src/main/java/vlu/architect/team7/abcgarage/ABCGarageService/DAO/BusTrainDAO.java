package vlu.architect.team7.abcgarage.ABCGarageService.DAO;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import vlu.architect.team7.abcgarage.ABCGarageService.Model.BusTrain;

@Repository
public interface BusTrainDAO extends PagingAndSortingRepository<Integer, BusTrain> {
}
