

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Library {

    private Map<String, Double> totalPriceByAuthor;
    private ArrayList<Book> books;

    public Library() {
        this.totalPriceByAuthor = new TreeMap<>();
        this.books = new ArrayList<>();
    }

    public void addBook(Book book){
        this.books.add(book);
        this.totalPriceByAuthor.putIfAbsent(book.getAuthor(),0d);
        this.totalPriceByAuthor.put(book.getAuthor(),this.totalPriceByAuthor.get(book.getAuthor())+book.getPrice());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        this.totalPriceByAuthor.entrySet().stream().sorted((a,b)->{
            int result = Double.compare(b.getValue(),a.getValue());
            return result;

        }).forEach(x->builder.append(String.format("%s -> %.2f"+System.lineSeparator(),x.getKey(),x.getValue())));
        return builder.toString().trim();
    }
}
