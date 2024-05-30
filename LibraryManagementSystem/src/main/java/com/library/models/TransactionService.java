package com.library.models;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.library.dao.TransactionDAO;
import com.library.services.ITransactionService;

public class TransactionService implements ITransactionService {
    private TransactionDAO transactionDAO = new TransactionDAO();

    @Override
    public void borrowBook(int bookId, int memberId) {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(transactionDAO.getAllTransactions().size() + 1);
        transaction.setBookId(bookId);
        transaction.setMemberId(memberId);
        transaction.setBorrowedDate(Date.valueOf(LocalDate.now()));
        transaction.setReturnDate(null);  // Initialize return date as null
        transactionDAO.addTransaction(transaction);
    }

    @Override
    public void returnBook(int bookId, int memberId) {
        List<Transaction> transactions = transactionDAO.getAllTransactions();
        for (Transaction transaction : transactions) {
            if (transaction.getBookId() == bookId && transaction.getMemberId() == memberId && transaction.getReturnDate() == null) {
                transaction.setReturnDate(Date.valueOf(LocalDate.now()));
                transactionDAO.updateTransaction(transaction);
                break;
            }
        }
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionDAO.getAllTransactions();
    }
}