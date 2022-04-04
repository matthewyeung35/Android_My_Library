package com.matthewyeung35.mylibrary;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;


//Temporary database class
public class Utils {
    private static final String All_BOOKS_KEY = "all_books";
    private static final String ALREADY_READ_KEY = "already_read_books";
    private static final String WANT_TO_READ_KEY = "want_to_read_books";
    private static final String CURRENTLY_KEY = "currently_reading_books";
    private static final String FAVOURITE_KEY = "favourite_books";
    private static Utils instance;
    private SharedPreferences sharedPreferences;

//    private static ArrayList<Book> allBooks;
//    private static ArrayList<Book> alreadyReadBooks;
//    private static ArrayList<Book> wantToReadBooks;
//    private static ArrayList<Book> currentlyReadBooks;
//    private static  ArrayList<Book> favouriteBooks;

    private Utils(Context context) {

        sharedPreferences = context.getSharedPreferences("alternate_db", Context.MODE_PRIVATE);
        if (null==getAllBooks()){
            initData();
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        if (null==getAlreadyReadBooks()){
            editor.putString(ALREADY_READ_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null==getWantToReadBooks()){
            editor.putString(WANT_TO_READ_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null==getCurrentlyReadBooks()){
            editor.putString(CURRENTLY_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null==getFavouriteBooks()){
            editor.putString(FAVOURITE_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
    }

    // convert data to json
    private void initData() {
        ArrayList<Book> books = new ArrayList<>();

        String longdesc = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ac risus id arcu suscipit ultrices ut ut metus. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nullam vitae accumsan purus. Nullam lacinia tortor ut turpis ultrices, nec pulvinar nisi sagittis. Nulla non est nisl. Pellentesque rhoncus metus ex, et pulvinar felis commodo a. Morbi aliquam tellus enim, quis ultrices libero sollicitudin hendrerit. Donec id congue odio. In erat lectus, consequat a porta a, fringilla vitae justo. Praesent sed posuere turpis. Praesent lacinia velit nec nibh porttitor fermentum.";

        books.add(new Book(1,"1984","george",30,"https://images-na.ssl-images-amazon.com/images/I/41t+5gUqXYL._SX277_BO1,204,203,200_.jpg","short desc",longdesc, "https://en.wikipedia.org/wiki/Nineteen_Eighty-Four"));
        books.add(new Book(2,"Harry potter","JK",300,"https://m.media-amazon.com/images/M/MV5BNjQ3NWNlNmQtMTE5ZS00MDdmLTlkZjUtZTBlM2UxMGFiMTU3XkEyXkFqcGdeQXVyNjUwNzk3NDc@._V1_.jpg","short desc", "long desc", "https://en.wikipedia.org/wiki/Harry_Potter"));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(All_BOOKS_KEY, gson.toJson(books));
        editor.commit();
    }

    public static synchronized Utils getInstance(Context context) {
        if (null!= instance){
            return instance;
        }else{
            instance = new Utils(context);
            return instance;
        }
    }

    //get data from json
    public ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(All_BOOKS_KEY,null), type);
        return books;
    }

    public ArrayList<Book> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALREADY_READ_KEY,null), type);
        return books;
    }

    public ArrayList<Book> getWantToReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(WANT_TO_READ_KEY,null), type);
        return books;
    }

    public ArrayList<Book> getCurrentlyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(CURRENTLY_KEY,null), type);
        return books;
    }

    public ArrayList<Book> getFavouriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(FAVOURITE_KEY,null), type);
        return books;
    }

    // return a book class item
    public Book getBookById(int id){
        ArrayList<Book> books = getAllBooks();
        if (null!= books){
            for (Book b: books){
                if (b.getId()==id){
                    return b;
                }
            }
        }

        return null;
    }

    public boolean addToAlreadyRead(Book book){
        ArrayList<Book> books = getAlreadyReadBooks();
        if (null!=books){
            if(books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_KEY);
                editor.putString(ALREADY_READ_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
            }
        return false;
        }


    public boolean addToWantToRead(Book book){
        ArrayList<Book> books = getWantToReadBooks();
        if (null!=books){
            if(books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WANT_TO_READ_KEY);
                editor.putString(WANT_TO_READ_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }
    public boolean addToCurrentlyRead(Book book){
        ArrayList<Book> books = getCurrentlyReadBooks();
        if (null!=books){
            if(books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_KEY);
                editor.putString(CURRENTLY_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }
    public boolean addToFavourite(Book book){
        ArrayList<Book> books = getFavouriteBooks();
        if (null!=books){
            if(books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVOURITE_KEY);
                editor.putString(FAVOURITE_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }
    public boolean removeFromAlreadyRead(Book book){
        ArrayList<Book> books = getAlreadyReadBooks();
        if (null!=books){
            for (Book b: books){
                if(b.getId() == book.getId()){
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALREADY_READ_KEY);
                        editor.putString(ALREADY_READ_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromWantToRead(Book book){
        ArrayList<Book> books = getWantToReadBooks();
        if (null!=books){
            for (Book b: books){
                if(b.getId() == book.getId()){
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WANT_TO_READ_KEY);
                        editor.putString(WANT_TO_READ_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromCurrentlyRead(Book book){
        ArrayList<Book> books = getCurrentlyReadBooks();
        if (null!=books){
            for (Book b: books){
                if(b.getId() == book.getId()){
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_KEY);
                        editor.putString(CURRENTLY_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromFavourite(Book book){
        ArrayList<Book> books = getFavouriteBooks();
        if (null!=books){
            for (Book b: books){
                if(b.getId() == book.getId()){
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAVOURITE_KEY);
                        editor.putString(FAVOURITE_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }


}
