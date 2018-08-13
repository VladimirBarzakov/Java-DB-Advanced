package app.exam.io.interfaces;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileIOImpl implements FileIO{

    @Override
    public String read(String file) throws IOException {
        InputStream inputStream = this.getClass().getResourceAsStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line=reader.readLine())!=null){
            sb.append(line);
        }
        return sb.toString().trim();
    }

    @Override
    public void write(String fileContent, String file) throws IOException {
        try (OutputStream outputStream = new FileOutputStream(file);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))){
            writer.write(fileContent);
        }
    }
}
