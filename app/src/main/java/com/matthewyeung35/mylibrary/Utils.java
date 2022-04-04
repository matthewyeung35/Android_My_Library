package com.matthewyeung35.mylibrary;

import java.util.ArrayList;


//Temporary database class
public class Utils {
    private static Utils instance;

    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> alreadyReadBooks;
    private static ArrayList<Book> wantToReadBooks;
    private static ArrayList<Book> currentlyReadBooks;
    private static  ArrayList<Book> favouriteBooks;

    private Utils() {
        if (null==allBooks){
            allBooks = new ArrayList<>();
            initData();
        }
        if (null==alreadyReadBooks){
            alreadyReadBooks = new ArrayList<>();
        }
        if (null==wantToReadBooks){
            wantToReadBooks = new ArrayList<>();
        }
        if (null==currentlyReadBooks){
            currentlyReadBooks = new ArrayList<>();
        }
        if (null==favouriteBooks){
            favouriteBooks = new ArrayList<>();
        }
    }

    private void initData() {
        ArrayList<Book> books = new ArrayList<>();
        String longdesc = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ac risus id arcu suscipit ultrices ut ut metus. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nullam vitae accumsan purus. Nullam lacinia tortor ut turpis ultrices, nec pulvinar nisi sagittis. Nulla non est nisl. Pellentesque rhoncus metus ex, et pulvinar felis commodo a. Morbi aliquam tellus enim, quis ultrices libero sollicitudin hendrerit. Donec id congue odio. In erat lectus, consequat a porta a, fringilla vitae justo. Praesent sed posuere turpis. Praesent lacinia velit nec nibh porttitor fermentum.";

        allBooks.add(new Book(1,"1984","george",30,"https://images-na.ssl-images-amazon.com/images/I/41t+5gUqXYL._SX277_BO1,204,203,200_.jpg","short desc",longdesc, "https://en.wikipedia.org/wiki/Nineteen_Eighty-Four"));
        allBooks.add(new Book(2,"Harry potter","JK",300,"https://m.media-amazon.com/images/M/MV5BNjQ3NWNlNmQtMTE5ZS00MDdmLTlkZjUtZTBlM2UxMGFiMTU3XkEyXkFqcGdeQXVyNjUwNzk3NDc@._V1_.jpg","short desc", "long desc", "https://en.wikipedia.org/wiki/Harry_Potter"));
    }

    public static synchronized Utils getInstance() {
        if (null!= instance){
            return instance;
        }else{
            instance = new Utils();
            return instance;
        }
    }

    public static ArrayList<Book> getAllBooks() {
        return allBooks;
    }

    public static ArrayList<Book> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public static ArrayList<Book> getWantToReadBooks() {
        return wantToReadBooks;
    }

    public static ArrayList<Book> getCurrentlyReadBooks() {
        return currentlyReadBooks;
    }

    public static ArrayList<Book> getFavouriteBooks() {
        return favouriteBooks;
    }

    // return a book class item
    public Book getBookById(int id){
        for (Book b: allBooks){
            if (b.getId()==id){
                return b;
            }
        }
        return null;
    }

    public boolean addToAlreadyRead(Book book){
        return alreadyReadBooks.add(book);
    }

    public boolean addToWantToRead(Book book){
        return wantToReadBooks.add(book);
    }
    public boolean addToCurrentlyRead(Book book){
        return currentlyReadBooks.add(book);
    }
    public boolean addToFavourite(Book book){
        return favouriteBooks.add(book);
    }

    public boolean removeFromAlreadyRead(Book book){
        return alreadyReadBooks.remove(book);
    }

    public boolean removeFromWantToRead(Book book){
        return wantToReadBooks.remove(book);
    }

    public boolean removeFromCurrentlyRead(Book book){
        return currentlyReadBooks.remove(book);
    }

    public boolean removeFromFavourite(Book book){
        return favouriteBooks.remove(book);
    }


}
