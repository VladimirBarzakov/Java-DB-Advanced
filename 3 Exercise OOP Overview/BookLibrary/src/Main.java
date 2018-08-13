import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
        Library library = new Library();

        int n = Integer.parseInt(reader.readLine());

        for (int i = 0; i < n; i++) {
            String[] input = reader.readLine().split(" ");
            Book book = new Book(input[0],input[1],input[2],input[3],input[4],Double.parseDouble(input[5]));
            library.addBook(book);
        }

        System.out.println(library.toString());

    }
}
