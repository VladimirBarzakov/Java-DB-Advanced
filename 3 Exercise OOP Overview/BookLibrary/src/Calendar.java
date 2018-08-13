import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;

public class Calendar{

    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader( System.in));
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
        LocalDate startDate = LocalDate.parse(sc.readLine(),format);
        LocalDate endDate = LocalDate.parse(sc.readLine(),format);
        HashSet<String> holidays = new HashSet<>(Arrays.asList("01-01","03-03","01-05","06-05",
                "24-05","06-09","22-09","01-11","24-12","25-12","26-12"));

        long workDays=0;
        while (startDate.isBefore(endDate.plusDays(1))){
            if(startDate.getDayOfWeek().getValue()==6
                    || startDate.getDayOfWeek().getValue() == 7 || containsDate(holidays,startDate)){
            } else {
                workDays++;
            }

            startDate=startDate.plusDays(1);
        }
        System.out.println(workDays);
    }

    private static boolean containsDate(HashSet<String> hollidays, LocalDate startDate) {
        String key = String.format("%02d-%02d",startDate.getDayOfMonth(),startDate.getMonthValue());
        if (hollidays.contains(key)){
            return true;
        }
        return false;
    }
}
