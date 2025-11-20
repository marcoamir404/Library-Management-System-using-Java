package dataModel;

import java.util.ArrayList;

import enums.BookStatus;

public class Book {
	
	public static ArrayList<Book> books = new ArrayList<>();
	
	private String bookId;
	private String title;
	private String author;
	private String genre;
	private int publicationYear;
	private String summary;
	private BookStatus status = BookStatus.AVAILABLE; 
	
	public Book(){}
	
	public Book(String bookId, String title, String author, String genre, int publicationYear, String summary) {
		setBookId(bookId);
		setTitle(title);
		setAuthor(author);
		setGenre(genre);
		setPublicationYear(publicationYear);
		setSummary(summary);
	}
	
	public String getBookId() {return bookId;}
	public void setBookId(String bookId ) {this.bookId = bookId;}
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
	public String getAuthor() {return author;}
	public void setAuthor(String author) {this.author = author;}
	public String getGenre() {return genre;}
	public void setGenre(String genre) {this.genre = genre;}
	public int getPublicationYear() {return publicationYear;}
	public void setPublicationYear(int publicationYear ) {this.publicationYear = publicationYear;}
	public String getSummary() {return summary;}
	public void setSummary(String summary) {this.summary = summary;}
	public BookStatus getStatus() {return status;}
	public void setStatus(BookStatus status ) {this.status = status;}
	
	public void updateDetails(String title, String author, String genre, int publicationYear, String summary) {
		setTitle(title);
		setAuthor(author);
		setGenre(genre);
		setPublicationYear(publicationYear);
		setSummary(summary);
	}
}
