package vlu.architect.team7.abcgarage.ABCGarageService.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vlu.architect.team7.abcgarage.ABCGarageService.Model.Place;

@Repository
public interface PlaceDAO extends CrudRepository<Place, String> {
}
