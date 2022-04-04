package com.matthewyeung35.mylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


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

                    handleAlreadyRead(incomingBook);
                    handleWantToReadBooks(incomingBook);
                    handleCurrentlyReadingBooks(incomingBook);
                    handleFavouriteBooks(incomingBook);
                }

            }
        }
    }

    private void handleWantToReadBooks(final Book book) {
        ArrayList<Book> wantToReadBooks = Utils.getInstance().getWantToReadBooks();
        boolean existInBooks = false;
        for (Book b: wantToReadBooks){
            if (b.getId()==book.getId()){
                existInBooks = true;
            }
        }
        if (existInBooks){
            btnAddtoWantToRead.setEnabled(false);
        }else{
            btnAddtoWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToWantToRead(book)){
                        Toast.makeText(BookActivity.this, "Book added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, WantToReadActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Wrong, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleFavouriteBooks(final Book book) {
        ArrayList<Book> favouriteBooks = Utils.getInstance().getFavouriteBooks();
        boolean existInBooks = false;
        for (Book b: favouriteBooks){
            if (b.getId()==book.getId()){
                existInBooks = true;
            }
        }
        if (existInBooks){
            btnAddToFavourite.setEnabled(false);
        }else{
            btnAddToFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToFavourite(book)){
                        Toast.makeText(BookActivity.this, "Book added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, FavouriteActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Wrong, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleCurrentlyReadingBooks(final Book book) {
        ArrayList<Book> currentlyReadBooks = Utils.getInstance().getCurrentlyReadBooks();
        boolean existInBooks = false;
        for (Book b: currentlyReadBooks){
            if (b.getId()==book.getId()){
                existInBooks = true;
            }
        }
        if (existInBooks){
            btnAddToCurrentlyReading.setEnabled(false);
        }else{
            btnAddToCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToCurrentlyRead(book)){
                        Toast.makeText(BookActivity.this, "Book added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, CurrentlyReadActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Wrong, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }



    // enable and disable the already read button, and add them into the already read array
    private void handleAlreadyRead(final Book book) {
        ArrayList<Book> alreadyReadBook = Utils.getInstance().getAlreadyReadBooks();
        boolean existInBooks = false;
        for (Book b: alreadyReadBook){
            if (b.getId()==book.getId()){
                existInBooks = true;
            }
        }
        if (existInBooks){
            btnAddToAlreadyRead.setEnabled(false);
        }else{
            btnAddToAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToAlreadyRead(book)){
                        Toast.makeText(BookActivity.this, "Book added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, AlreadyReadBookActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Wrong, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
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
    btnAddtoWantToRead = findViewById(R.id.btnAddtoWantToRead);

    bookImage = findViewById(R.id.imageBook);


    }

}
