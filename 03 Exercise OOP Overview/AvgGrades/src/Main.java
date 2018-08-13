import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Student> students = new ArrayList<>();

        int n = Integer.parseInt(reader.readLine());

        for (int i = 0; i < n; i++) {
            String[] input = reader.readLine().split(" ");
            String name = input[0];
            double avg = 0;
            if (input.length>1){
                avg = Arrays.stream(Arrays.copyOfRange(input,1, input.length)).mapToDouble(Double::parseDouble).average().getAsDouble();
            }
            Student student = new Student(name, avg);
            students.add(student);
        }
        StringBuilder builder = new StringBuilder();
        students.stream().filter(x->x.averageGrade>=5.00).
                sorted((a,b)->{
                    int result = a.name.compareTo(b.name);
                    if (result == 0) {
                        result=Double.compare(b.averageGrade,a.averageGrade);
                    }
                    return result;
                }).forEach(
                x->builder.append(String.format("%s -> %.2f"+System.lineSeparator(), x.name,x.averageGrade)));

        System.out.println(builder.toString().trim());

    }
}
