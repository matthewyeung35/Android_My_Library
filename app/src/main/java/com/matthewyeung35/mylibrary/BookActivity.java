package com.matthewyeung35.mylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;


public class BookActivity extends AppCompatActivity {
    private TextView txtBookName, txtAuthor, txtPages, txtDescription;
    private Button btnAddtoWantToRead, btnAddToAlreadyRead, btnAddToCurrentlyReading, btnAddToFavourite;
    private ImageView bookImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();
        Intent intent = getIntent();
        if (intent != null){
            int bookId = intent.getIntExtra("bookId", -1);
            if (bookId != -1){
                Book incomingBook = Utils.getInstance().getBookById(bookId);
                if (incomingBook != null){
                    setData(incomingBook);
                }

            }
        }
    }

    private void setData(Book book){
        txtBookName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDesc());
        Glide.with(this)
                .asBitmap().load(book.getImageUrl())
                .into(bookImage);
    }

    private void initViews() {
    txtAuthor = findViewById(R.id.txtBook4);
    txtBookName = findViewById(R.id.txtBook2);
    txtPages = findViewById(R.id.txtBook6);
    txtDescription = findViewById(R.id.txtBook8);

    btnAddToAlreadyRead = findViewById(R.id.btnAddToAlreadyRead);
    btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
    btnAddToFavourite = findViewById(R.id.btnAddToFavourite);
    btnAddtoWantToRead = findViewById(R.id.btnAddToWantToRead);

    bookImage = findViewById(R.id.imageBook);


    }

}
