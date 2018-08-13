package org.softuni.mostwanted.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.IOException;
@Component
public class JSONParserImpl implements Parser {

    private Gson gson;

    public JSONParserImpl() {
        this.gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting().setDateFormat("dd-MM-yyyy")
                .create();
    }


    @Override
    public <T> T read(Class<T> objectClass, String fileContent) {
        T result = gson.fromJson(fileContent,objectClass);
        return result;
    }

    @Override
    public <T> String write(T object) {
        String content = gson.toJson(object);
        return content;
    }
}
