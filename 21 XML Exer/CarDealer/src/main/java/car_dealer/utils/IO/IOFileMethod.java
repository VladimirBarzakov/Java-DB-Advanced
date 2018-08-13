package car_dealer.utils.IO;

import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

@Component
public class IOFileMethod {

    public String readAllData(String filePath){
        try {
            InputStream stream = this.getClass().getResourceAsStream(filePath);
            return StreamUtils.copyToString(stream, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeToFile(String fileName, String content) {

        String filePath= System.getProperty("user.dir")+"/src/main/resources/outputXML/"+fileName;
        try {
            FileWriter writer = new FileWriter(new File(filePath));
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
