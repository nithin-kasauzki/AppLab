package com.example.library;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class BookCursorAdapter extends CursorAdapter {

    public BookCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate a new view for each item in the ListView
        return LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Bind the data in the cursor to the view
        TextView titleTextView = view.findViewById(R.id.bookTitle);
        TextView authorTextView = view.findViewById(R.id.bookAuthor);
        TextView availableTextView = view.findViewById(R.id.bookAvailability);

        // Retrieve data from the cursor
        String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
        String author = cursor.getString(cursor.getColumnIndexOrThrow("author"));
        int availableCopies = cursor.getInt(cursor.getColumnIndexOrThrow("available_copies"));

        // Set data to views
        titleTextView.setText(title);
        authorTextView.setText(author);
        availableTextView.setText("Available: " + availableCopies);
    }
}
