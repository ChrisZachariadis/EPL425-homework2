package cy.ac.ucy.cs.epl425.RestAPISecureServer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cy.ac.ucy.cs.epl425.RestAPISecureServer.model.Book;
import java.util.List;

// We can use CrudRepository’s methods: save(), findOne(), findById(), 
// findAll(), count(), delete(), deleteById() without implementing these methods.
@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    // Define custom finder methods
    // ============================
    // returns all Book entities whose title column contains input val.
    List<Book> findByTitleContaining(String val);
    // returns all Book entities whose is_published column has the value val.
    List<Book> findByIsPublished(Boolean val);
}