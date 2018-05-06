package zneref.restapp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import zneref.restapp.domain.User;
import zneref.restapp.domain.dto.UserDto;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query
    List<User> findByNameOrLastName(@Param("ARG") String arg);

    List<User> findAll();

    Optional<User> findByUserId(int id);

}
