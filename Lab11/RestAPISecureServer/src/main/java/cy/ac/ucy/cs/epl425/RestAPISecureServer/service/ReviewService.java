package cy.ac.ucy.cs.epl425.RestAPISecureServer.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cy.ac.ucy.cs.epl425.RestAPISecureServer.repository.ReviewRepository;
import cy.ac.ucy.cs.epl425.RestAPISecureServer.model.Review;

@Service
public class ReviewService {
    @Autowired ReviewRepository reviewRepository;

    public List<Review> getReviewsByBookId(Long bookId) {
        return this.reviewRepository.findByBookId(bookId);
    }
    
    public Review getReviewById(Long id) {
        Optional<Review> review = this.reviewRepository.findById(id);
        if(review.isPresent())
	        return review.get();
        else 
            return null;
    }

    public List<Review> getReviewsByBookIdAndAuthorName(Long bookId, String val) {
        return this.reviewRepository.findByBookIdAndAuthorNameContaining(bookId, val);
    }

    public List<Review> getReviewsByBookIdAndTitle(Long bookId, String val) {
        return this.reviewRepository.findByBookIdAndTitleContaining(bookId, val);
    }

    public List<Review> getReviewsByBookIdAndAuthorNameAndTitle(Long bookId, String authorName, String title) {
        return this.reviewRepository.findByBookIdAndAuthorNameContainingAndTitleContaining(bookId, authorName, title);
    }

    public List<Review> getReviewsByBookIdAndStars(Long bookId, Integer val) {
        return this.reviewRepository.findByStarsEquals(val);
    }
    
    public Review saveReview(Review review) {
        return this.reviewRepository.save(review);
    }

    public void deleteReviewsByBookId(Long bookId) {
        this.reviewRepository.deleteByBookId(bookId);
    }

    public void deleteReviewById(Long id) {
        this.reviewRepository.deleteById(id);
    }
}
