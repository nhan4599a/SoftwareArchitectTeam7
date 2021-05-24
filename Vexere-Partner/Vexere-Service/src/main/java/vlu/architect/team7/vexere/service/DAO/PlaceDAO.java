package vlu.architect.team7.vexere.service.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vlu.architect.team7.vexere.service.Model.Place;

@Repository
public interface PlaceDAO extends CrudRepository<Place, String> {
}
