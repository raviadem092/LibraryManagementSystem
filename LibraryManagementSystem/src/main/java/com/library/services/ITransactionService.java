package com.library.services;

import java.util.List;

import com.library.models.Transaction;

public interface ITransactionService {
	public void borrowBook(int bookId, int memberId);
    public void returnBook(int bookId, int memberId);
    public List<Transaction> getAllTransactions();
}

