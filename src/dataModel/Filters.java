package dataModel;

import enums.BookStatus;

public class Filters {
	public String author;
	public String genre;
	public int yearFrom = -1;
	public int yearTo = -1;
	public BookStatus status = null;
	
	public Filters() {}
	
}
