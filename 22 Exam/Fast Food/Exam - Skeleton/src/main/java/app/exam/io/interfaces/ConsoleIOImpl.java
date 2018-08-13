package app.exam.io.interfaces;

import org.springframework.stereotype.Component;

@Component
public class ConsoleIOImpl implements ConsoleIO{

    @Override
    public void write(String line) {
        System.out.println(line);
    }
}
