package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.models.Book;
import com.library.models.Transaction;
import com.library.utils.DatabaseManager;

public class TransactionDAO {

	public void addTransaction(Transaction transaction) {
        String sql = "INSERT INTO transactions (transaction_id, book_id, member_id, borrowed_date, return_date) VALUES (transaction_id_seq.nextval, ?, ?, ?, ?)";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, transaction.getBookId());
            statement.setInt(2, transaction.getMemberId());
            statement.setDate(3, transaction.getBorrowedDate());
            statement.setDate(4, transaction.getReturnDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
    public void updateTransaction(Transaction transaction) {
        String sql = "UPDATE transactions SET return_date = ? WHERE transaction_id = ?";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, transaction.getReturnDate());
            statement.setInt(2, transaction.getTransactionId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransactionId(resultSet.getInt("transaction_id"));
                transaction.setBookId(resultSet.getInt("book_id"));
                transaction.setMemberId(resultSet.getInt("member_id"));
                transaction.setBorrowedDate(resultSet.getDate("borrowed_date"));
                transaction.setReturnDate(resultSet.getDate("return_date"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
