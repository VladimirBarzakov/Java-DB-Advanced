package judgeSystem.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

@Component
public class JsonParser {

    private Gson gson;

    public JsonParser() {
        this.gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }

    public <T> T read(Class<T> objectClass, String fileContent) {
        return gson.fromJson(fileContent,objectClass);
    }

    public <T> String write(T object) {
        return gson.toJson(object);
    }

}
