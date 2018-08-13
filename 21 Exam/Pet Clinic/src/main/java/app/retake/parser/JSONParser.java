package app.retake.parser;

import app.retake.parser.interfaces.Parser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

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
    public <T> T read(Class<T> objectClass, String fileContent) throws IOException {
        T result = gson.fromJson(fileContent,objectClass);
        return result;
    }

    @Override
    public <T> String write(T object) throws IOException {
        String content = gson.toJson(object);
        return content;
    }
}
