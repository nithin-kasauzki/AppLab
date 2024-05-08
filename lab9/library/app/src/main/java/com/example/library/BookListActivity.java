package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

public class BookListActivity extends Activity {
    private ListView bookListView;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        bookListView = findViewById(R.id.bookListView);
        dbHelper = new DatabaseHelper(this);

        // Load books from the SQLite database
        Cursor bookCursor = loadBooks();
        if (bookCursor != null) {
            BookCursorAdapter adapter = new BookCursorAdapter(this, bookCursor);
            bookListView.setAdapter(adapter);
        }
    }
    private Cursor loadBooks() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return db.rawQuery("SELECT _id, title, author, available_copies FROM books ORDER BY title ASC", null);
    }


}
