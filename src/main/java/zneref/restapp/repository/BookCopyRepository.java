package zneref.restapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import zneref.restapp.domain.BookCopy;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface BookCopyRepository extends CrudRepository<BookCopy, Integer> {

    List<BookCopy> findAll();

    Optional<BookCopy> findByBookCopyId(int id);

    List<BookCopy> findByBookIdAndStatus(int bookId, String status);

}
