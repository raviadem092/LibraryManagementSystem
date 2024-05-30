package com.library.models;

import java.util.List;

import com.library.dao.BookDAO;
import com.library.services.IBookService;

public class BookService implements IBookService {
    private BookDAO bookDAO = new BookDAO();

    @Override
    public void addBook(Book book) {
        bookDAO.addBook(book);
    }

    @Override
    public void removeBook(int bookId) {
        bookDAO.removeBook(bookId);
    }

    @Override
    public Book findBookById(int bookId) {
        return bookDAO.findBookById(bookId);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }
}


