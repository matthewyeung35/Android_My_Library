package com.matthewyeung35.mylibrary;

import java.util.ArrayList;


//Temporary database class
public class Utils {
    private static Utils instance;

    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> alreadyReadBooks;
    private static ArrayList<Book> wantToReadBooks;
    private static ArrayList<Book> currentlyReadingBooks;
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
        if (null==currentlyReadingBooks){
            currentlyReadingBooks = new ArrayList<>();
        }
        if (null==favouriteBooks){
            favouriteBooks = new ArrayList<>();
        }
    }

    private void initData() {
        ArrayList<Book> books = new ArrayList<>();
        String longdesc = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ac risus id arcu suscipit ultrices ut ut metus. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nullam vitae accumsan purus. Nullam lacinia tortor ut turpis ultrices, nec pulvinar nisi sagittis. Nulla non est nisl. Pellentesque rhoncus metus ex, et pulvinar felis commodo a. Morbi aliquam tellus enim, quis ultrices libero sollicitudin hendrerit. Donec id congue odio. In erat lectus, consequat a porta a, fringilla vitae justo. Praesent sed posuere turpis. Praesent lacinia velit nec nibh porttitor fermentum.";

        allBooks.add(new Book(1,"1984","george",30,"https://images-na.ssl-images-amazon.com/images/I/41t+5gUqXYL._SX277_BO1,204,203,200_.jpg","short desc",longdesc));
        allBooks.add(new Book(2,"Harry potter","JK",300,"https://m.media-amazon.com/images/M/MV5BNjQ3NWNlNmQtMTE5ZS00MDdmLTlkZjUtZTBlM2UxMGFiMTU3XkEyXkFqcGdeQXVyNjUwNzk3NDc@._V1_.jpg","short desc", "long desc"));
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

    public static ArrayList<Book> getCurrentlyReadingBooks() {
        return currentlyReadingBooks;
    }

    public static ArrayList<Book> getFavouriteBooks() {
        return favouriteBooks;
    }

    public Book getBookById(int id){
        for (Book b: allBooks){
            if (b.getId()==id){
                return b;
            }
        }
        return null;
    }
}
