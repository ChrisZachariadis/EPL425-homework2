package cy.ac.ucy.cs.epl425.RestAPISecureServer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cy.ac.ucy.cs.epl425.RestAPISecureServer.model.Book;
import cy.ac.ucy.cs.epl425.RestAPISecureServer.model.Review;
import cy.ac.ucy.cs.epl425.RestAPISecureServer.service.BookService;
import cy.ac.ucy.cs.epl425.RestAPISecureServer.service.ReviewService;

@RequestMapping("/api")
@RestController
public class ReviewController {

  @Autowired
  ReviewService reviewService;
  @Autowired
  BookService bookService;

  @GetMapping("/books/{bid}/reviews")
  public ResponseEntity<List<Review>> getAllReviews(@PathVariable("bid") Long bookId, @RequestParam(required = false) String authorName, @RequestParam(required = false) String title) {
    try {
      List<Review> reviews;

      if (authorName == null && title == null)
        reviews = reviewService.getReviewsByBookId(bookId);
      else if (authorName != null && title == null)
        reviews = reviewService.getReviewsByBookIdAndAuthorName(bookId, authorName);
      else if (authorName == null && title != null)
        reviews = reviewService.getReviewsByBookIdAndTitle(bookId, title);
      else
        reviews = reviewService.getReviewsByBookIdAndAuthorNameAndTitle(bookId, authorName, title);

      if (reviews.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(reviews, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @GetMapping("/books/{bid}/reviews/{id}")
  public ResponseEntity<Review> getReviewById(@PathVariable("bid") Long bookId, @PathVariable("id") long id) {
    Book book = bookService.getBookById(bookId);
    if (book != null) {
      Review review = reviewService.getReviewById(id);

      if (review != null) {
        if (review.getBookId() == bookId) {
          return new ResponseEntity<>(review, HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/books/{bid}/reviews")
  public ResponseEntity<Review> createReview(@PathVariable("bid") Long bookId, @RequestBody Review review) {  
    try {
       Review _review = reviewService
          .saveReview(new Review(bookId, review.getAuthorName(), review.getTitle(), review.getStars()));
      return new ResponseEntity<>(_review, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/books/{bid}/reviews/{id}")
  public ResponseEntity<Review> updateBook(@PathVariable("bid") Long bookId, @PathVariable("bid") Long id, @RequestBody Review review) {
    Book book = bookService.getBookById(bookId);
    if (book != null) {
      Review _review = reviewService.getReviewById(id);

      if (_review != null) {
        if (_review.getBookId() == bookId) {
          _review.setAuthorName(review.getAuthorName());
          _review.setTitle(review.getTitle());
          _review.setStars(review.getStars());
          return new ResponseEntity<>(reviewService.saveReview(_review), HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/books/{bid}/reviews")
  public ResponseEntity<HttpStatus> deleteAllReviews(@PathVariable("bid") Long bookId) {
    try {
      reviewService.deleteReviewsByBookId(bookId);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @DeleteMapping("/books/{bid}/reviews/{id}")
  public ResponseEntity<HttpStatus> deleteAuthor(@PathVariable("bid") Long bookId, @PathVariable("id") Long id) {
    try {
      reviewService.deleteReviewById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
