package cy.ac.ucy.cs.epl425.RestAPISecureServer.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Table("books")
public class Book {

	@Id
	private Long id;

	@Column("title")
	private String title;

	@Column("is_published")
	private Boolean isPublished;

	@MappedCollection(idColumn = "book_id")
  	private Set<Review> reviews;

	public Book() {

	}

	public Book(String title, Boolean isPublished) {
		this.title = title;
		this.isPublished = isPublished;
		reviews = new HashSet<>();
	}

	// getters
	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}
	
	public Boolean getIsPublished() {
		return isPublished;
	}

	public Set<Review> getReviews() {
		return reviews;
	}

	// setters
	public void setTitle(String title) {
		this.title = title;
    }

	public void setIsPublished(Boolean isPublished) {
		this.isPublished = isPublished;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	public void setReview(Review review) {
		this.reviews.add(review);
	}
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", published date=" + isPublished + "]";
	}
}
