package cy.ac.ucy.cs.epl425.RestAPISecureServer.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Table("reviews")
public class Review {

	@Id
	private Long id;

	@JsonIgnore
	@Column("book_id")
	private Long bookId;

	@Column("author_name")
	private String authorName;

	@Column("title")
	private String title;

    @Column("stars")
	private Integer stars;

	public Review() {

	}

	public Review(Long bookId, String authorName, String title, Integer stars) {
		this.bookId = bookId;
		this.authorName = authorName;
		this.title = title;
        this.stars = stars;
	}

    // getters
	public Long getId() {
		return id;
	}

	public Long getBookId() {
		return bookId;
	}

	public String getAuthorName() {
		return authorName;
	}
	
	public String getTitle() {
		return title;
	}

    public Integer getStars() {
		return stars;
	}

	// setters
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

    public void setStars(Integer stars) {
		this.stars = stars;
	}
	
	@Override
	public String toString() {
		return "Review [id=" + id + ", book id =" + bookId + ", author name=" + authorName + ", title=" + title + ", stars=" + stars + "]";
	}
    
}
