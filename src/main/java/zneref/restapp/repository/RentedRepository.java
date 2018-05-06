package zneref.restapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import zneref.restapp.domain.Rented;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface RentedRepository extends CrudRepository<Rented, Integer> {

    List<Rented> findByUserIdAndBookCopyId(int userId, int copyId);
}
