package judgeSystem.io.impl;

import judgeSystem.io.interfaces.FileIO;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileIOImpl implements FileIO {

    @Override
    public String read(String file) {
        InputStream stream = this.getClass().getResourceAsStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line=reader.readLine())!=null){
                sb.append(line);
            }
        } catch (IOException e){
            e.getStackTrace();
            return null;
        }

        return sb.toString().trim();
    }

    @Override
    public void write(String fileContent, String file) {

        String filePath=System.getProperty("user.dir")+"/src/main/resources/output/"+file;
        OutputStream stream;
        try {
            stream = new FileOutputStream(filePath);
            BufferedWriter writer=new BufferedWriter((new OutputStreamWriter(stream)));
            writer.write(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
