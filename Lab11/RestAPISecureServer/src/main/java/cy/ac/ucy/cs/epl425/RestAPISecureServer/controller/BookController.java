package cy.ac.ucy.cs.epl425.RestAPISecureServer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cy.ac.ucy.cs.epl425.RestAPISecureServer.model.Book;
import cy.ac.ucy.cs.epl425.RestAPISecureServer.service.BookService;

@RequestMapping("/api")
@RestController
public class BookController {

  @Autowired
  BookService bookService;

  @GetMapping("/books")
  //@RequestMapping(value = "/books", method = RequestMethod.GET, produces = "application/json")
  public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false) String title) {
    try {
      List<Book> books;

      if (title == null)
        books = bookService.getAllBooks();
      else
        books = bookService.getBooksByTitle(title);

      if (books.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(books, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @GetMapping("/books/{id}")
  public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
    Book book = bookService.getBookById(id);

    if (book != null) {
      return new ResponseEntity<>(book, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/books")
  public ResponseEntity<Book> createBook(@RequestBody Book book) {  
    try {
      Book _book = bookService
          .saveBook(new Book(book.getTitle(), book.getIsPublished()));
      return new ResponseEntity<>(_book, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/books/{id}")
  public ResponseEntity<Book> updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
    Book _book = bookService.getBookById(id);

    if (_book != null) {
      _book.setTitle(book.getTitle());
      _book.setIsPublished(book.getIsPublished());
      return new ResponseEntity<>(bookService.saveBook(_book), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/books")
  public ResponseEntity<HttpStatus> deleteAllBooks() {
    try {
      bookService.deleteAllBooks();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @DeleteMapping("/books/{id}")
  public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") Long id) {
    try {
      bookService.deleteBookById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/books/published")
  public ResponseEntity<List<Book>> findByIsPublished() {
    try {
      List<Book> books = bookService.getPublishedBooks();

      if (books.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(books, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
