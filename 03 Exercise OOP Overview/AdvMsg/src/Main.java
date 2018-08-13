import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {
        String[] phrases = new String[]{"Excellent product.", "Such a great product.", "I always use that product.",
        "Best product of its category.", "Exceptional product.", "I can’t live without this product."};
        String[] events = new String[]{"Now I feel good.", "I have succeeded with this product.", "Makes miracles. I am happy of the results!",
        "I cannot believe but now I feel awesome.", "Try it yourself, I am very satisfied.", "I feel great!"};
        String[] author = new String[] {"Diana", "Petya", "Stella", "Elena", "Katya", "Iva", "Annie", "Eva"};
        String[] cities = new String[] {"Burgas", "Sofia", "Plovdiv", "Varna", "Ruse"};

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        long inputN = Long.parseLong(reader.readLine());
        Random rn = new Random();

        for (int i = 0; i < inputN; i++) {
            StringBuilder builder = new StringBuilder();
            int n = rn.nextInt(phrases.length-1);
            builder.append(phrases[n]);
            builder.append(" ");
            n = rn.nextInt(events.length-1);
            builder.append(events[n]);
            builder.append(" ");
            n = rn.nextInt(author.length-1);
            builder.append(author[n]);
            builder.append(" - ");
            n = rn.nextInt(cities.length-1);
            builder.append(cities[n]);

            System.out.println(builder.toString());
        }
    }
}
