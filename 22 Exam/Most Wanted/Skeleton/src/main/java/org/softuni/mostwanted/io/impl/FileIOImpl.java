package org.softuni.mostwanted.io.impl;

import org.softuni.mostwanted.io.interfaces.FileIO;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class FileIOImpl implements FileIO {

    public FileIOImpl() {
    }

    @Override
    public String read(String file) throws IOException {
        //TODO: Implement me ...
        //throw new UnsupportedOperationException("I am not implemented yet!");
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
        //TODO: Implement me ...
        //throw new UnsupportedOperationException("I am not implemented yet!");
        String filePath= System.getProperty("user.dir")+"/src/main/resources/outputJSON/"+file;
        try (OutputStream outputStream = new FileOutputStream(filePath);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))){
            writer.write(fileContent);
        }
    }
}
