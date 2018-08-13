package app.exam.parser;

import org.springframework.stereotype.Component;
import app.exam.parser.interfaces.Parser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

@Component(value = "XMLParser")
public class XMLParser implements Parser {

    private JAXBContext jaxbContext;

    @Override
    public <T> T read(Class<T> objectClass, String fileContent) {
        T object = null;
        try {
            this.jaxbContext = JAXBContext.newInstance(objectClass);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Reader reader = new StringReader(fileContent);
            object = (T) unmarshaller.unmarshal(reader);
            reader.close();

        }catch (Exception e){

        }
        return object;
    }

    @Override
    public <T> String write(T object){
        String result = null;
        try {
            this.jaxbContext=JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(object,stringWriter);
            result = stringWriter.toString();
        } catch (Exception e){

        }
        return  result;
    }
}
