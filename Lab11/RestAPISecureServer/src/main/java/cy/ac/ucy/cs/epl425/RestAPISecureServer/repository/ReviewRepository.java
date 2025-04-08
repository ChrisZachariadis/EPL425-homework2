package cy.ac.ucy.cs.epl425.RestAPISecureServer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cy.ac.ucy.cs.epl425.RestAPISecureServer.model.Review;
import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Review> findByBookId(Long bookId);
    List<Review> findByBookIdAndAuthorNameContaining(Long bookId, String authorName);
    List<Review> findByBookIdAndTitleContaining(Long bookId, String val);
    List<Review> findByBookIdAndAuthorNameContainingAndTitleContaining(Long bookId, String authorName, String title);
    List<Review> findByStarsEquals(Integer num);
    Long deleteByBookId(Long bookid);
}