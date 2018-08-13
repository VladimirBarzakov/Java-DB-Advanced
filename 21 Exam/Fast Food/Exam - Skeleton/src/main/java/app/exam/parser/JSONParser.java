package app.exam.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;
import app.exam.parser.interfaces.Parser;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Component(value = "JSONParser")
public class JSONParser implements Parser {

    private Gson gson;

    public JSONParser() {
        this.gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting().setDateFormat("dd-MM-yyyy")
                .create();
    }


    @Override
    public <T> T read(Class<T> objectClass, String fileContent) throws IOException, JAXBException {
        T result = gson.fromJson(fileContent,objectClass);
        return result;
    }

    @Override
    public <T> String write(T object) throws IOException, JAXBException {
        String content = gson.toJson(object);
        return content;
    }
}
