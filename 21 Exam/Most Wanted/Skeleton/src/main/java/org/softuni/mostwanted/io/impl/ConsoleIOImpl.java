package org.softuni.mostwanted.io.impl;

import org.softuni.mostwanted.io.interfaces.ConsoleIO;
import org.springframework.stereotype.Component;

@Component
public class ConsoleIOImpl implements ConsoleIO {
    @Override
    public void write(String line) {
        //TODO: Implement me ...
        //throw new UnsupportedOperationException("I am not implemented yet!");
        System.out.println(line);
    }
}
