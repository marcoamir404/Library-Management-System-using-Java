package managers;
import java.util.ArrayList;
import java.util.List;

import dataModel.Book;
import dataModel.Filters;
import enums.BookStatus;
import filemanager.BookFileHandler;

public class BookManager {
	
	private BookFileHandler bf = new BookFileHandler();
	private List<Book> books = Book.books;
	
	public BookManager() {
		if (books.isEmpty()) {
				books.addAll(bf.loadBooks());
        }
	}
	
	public boolean addBook(Book book) { 
		if(book == null || book.getBookId() == null) 
			return false;
		
		for(Book b : books) {
			if(b.getBookId().equals(book.getBookId())) {
				System.out.println("Book Id is already exict!\n");
				return false;
			}
		}
		books.add(book);
		bf.saveBooks(books);
		return true;
	}

	public boolean updateBook(Book book) {
		if(book ==null) return false;
		
		for(Book b : books) {
			if(b.getBookId().equals(book.getBookId())) {
				b.updateDetails(book);
				bf.saveBooks(books);
				return true;
			}
		}
		return false;
	}
	
	public boolean removeBook(String bookId) {
		if(bookId ==null) return false;
		
		boolean removed = books.removeIf(b -> b.getBookId().equals(bookId));
		if(removed) {bf.saveBooks(books);}
		return removed;
	}
	
	public Book searchBookById(String bookId) {
		Book targetBook = null;
		for(Book book : books) {
			if(book.getBookId().equalsIgnoreCase(bookId)) {
				targetBook = book;
				return targetBook;
			}
		}
		return null;
	}
	
	public List<Book> searchBooks(String query, Filters filters) { 
		List<Book> result = new ArrayList<>();
		
		if(query==null) query = "";
		
		query = query.toLowerCase();
		
		for(Book b : books) {
			
			boolean mathcQuery = b.getAuthor().toLowerCase().contains(query)||
					b.getTitle().toLowerCase().contains(query)||
					b.getBookId().toLowerCase().contains(query)||
					b.getGenre().toLowerCase().contains(query);
			
			if(!mathcQuery) continue;
			
			if(!applyFilters(b,filters)) continue;
			
			result.add(b);
		}
		
		return result;
	}
	

	private boolean applyFilters(Book b, Filters f) {
	    if (f == null) return true;

	    if (f.author != null && !f.author.isEmpty()) {
	        if (b.getAuthor() == null || !b.getAuthor().toLowerCase().contains(f.author.toLowerCase())) {
	            return false;
	        }
	    }

	    if (f.genre != null && !f.genre.isEmpty()) {
	        if (b.getGenre() == null || !b.getGenre().equalsIgnoreCase(f.genre)) {
	            return false;
	        }
	    }

	    if (f.yearFrom != -1 && b.getPublicationYear() < f.yearFrom) {
	        return false;
	    }
	    if (f.yearTo != -1 && b.getPublicationYear() > f.yearTo) {
	        return false;
	    }

	    if (f.status != null && b.getStatus() != f.status) {
	        return false;
	    }

	    return true;
	}

	
	public BookStatus trackBookStatus(String bookId) {
		for(Book b : books) {
			if(b.getBookId().equalsIgnoreCase(bookId)) {
				return b.getStatus();
			}
		}
		return null;
	}
	
	public List<Book> getAllBooks() {
		return books;
	}
}

