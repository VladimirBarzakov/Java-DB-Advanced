import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Number number = new Number(Long.parseLong(reader.readLine()));

        String num = number.LastDigitName();
        System.out.println(num);


    }
}
