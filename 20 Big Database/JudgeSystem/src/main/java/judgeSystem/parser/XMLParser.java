package judgeSystem.parser;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

@Component
public class XMLParser {

    private JAXBContext jaxbContext;

    public <T> T read(Class<T> objectClass, String fileContent) {
        try {
            this.jaxbContext=JAXBContext.newInstance(objectClass);
            Unmarshaller unmarshaller=jaxbContext.createUnmarshaller();
            Reader reader = new StringReader(fileContent);
            T object = (T) unmarshaller.unmarshal(reader);
            reader.close();
            return object;

        } catch (JAXBException|IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> String write(T object){
        try {
            this.jaxbContext=JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(object,stringWriter);
            return stringWriter.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }

    }
}
