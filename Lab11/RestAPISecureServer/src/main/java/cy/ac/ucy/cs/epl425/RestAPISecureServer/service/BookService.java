package cy.ac.ucy.cs.epl425.RestAPISecureServer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cy.ac.ucy.cs.epl425.RestAPISecureServer.repository.BookRepository;
import cy.ac.ucy.cs.epl425.RestAPISecureServer.model.Book;

@Service
public class BookService {
    @Autowired BookRepository bookRepository;

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<Book>();
        this.bookRepository.findAll().forEach(books::add);
        return books;
    }
    
    public Book getBookById(Long id) {
        Optional<Book> book = this.bookRepository.findById(id);
        if(book.isPresent())
	        return book.get();
        else 
            return null;

    }

    public List<Book> getBooksByTitle(String title) {
        return this.bookRepository.findByTitleContaining(title);
    }

    public List<Book> getPublishedBooks() {
        return this.bookRepository.findByIsPublished(true);
    }
    
    public Book saveBook(Book book) {
        return this.bookRepository.save(book);
    }

    public void deleteAllBooks() {
        this.bookRepository.deleteAll();
    }

    public void deleteBookById(Long id) {
        this.bookRepository.deleteById(id);
    }
}
