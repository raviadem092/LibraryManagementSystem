package com.library.services;

import java.util.List;

import com.library.models.Book;

public interface IBookService {
	public void addBook(Book book);
	public void removeBook(int bookId);
	public Book findBookById(int bookId);
	public List<Book> getAllBooks();
	
}
