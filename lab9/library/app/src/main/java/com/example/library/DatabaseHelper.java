package com.example.library;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "library.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the tables
        String createBooksTable = "CREATE TABLE books (_id INTEGER PRIMARY KEY, title TEXT, author TEXT, available_copies INTEGER);";
        String createUsersTable = "CREATE TABLE users (_id INTEGER PRIMARY KEY, name TEXT, email TEXT, password TEXT);";
        String createBorrowHistoryTable = "CREATE TABLE borrow_history (id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER, book_id INTEGER, borrow_date TEXT, return_date TEXT);";

        db.execSQL(createBooksTable);
        db.execSQL(createUsersTable);
        db.execSQL(createBorrowHistoryTable);

        // Insert sample books
        String insertBooks = "INSERT INTO books (title, author, available_copies) VALUES " +
                "('1984', 'George Orwell', 3)," +
                "('To Kill a Mockingbird', 'Harper Lee', 5)," +
                "('Pride and Prejudice', 'Jane Austen', 4)," +
                "('Moby Dick', 'Herman Melville', 2)," +
                "('The Great Gatsby', 'F. Scott Fitzgerald', 6);";
        db.execSQL(insertBooks);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle upgrading schema by dropping tables and recreating
        db.execSQL("DROP TABLE IF EXISTS books");
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS borrow_history");
        onCreate(db);
    }
    // Method to reduce the count of available copies for a book
    public void reduceCopies(int bookId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE books SET available_copies = available_copies - 1 WHERE _id = ?", new Object[]{bookId});
    }

    // Method to save borrow history
    public void saveBorrowHistory(int userId, int bookId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_id", userId);
        values.put("book_id", bookId);
        values.put("borrow_date", System.currentTimeMillis());
        db.insert("borrow_history", null, values);
    }

    // Method to save a book request
    public void saveBookRequest(int userId, int bookId) {
        // You can either add a new request table or keep it simple by directly updating the books table
        // Example: Create a requests table and save it there
    }

}
