package org.softuni.mostwanted.parser;

import org.softuni.mostwanted.parser.interfaces.Parser;
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
public class XMLParserImpl implements Parser {


    private JAXBContext jaxbContext;
    @Override
    public <T> T read(Class<T> objectClass, String fileContent) throws IOException, JAXBException {
        this.jaxbContext=JAXBContext.newInstance(objectClass);
        Unmarshaller unmarshaller=jaxbContext.createUnmarshaller();
        Reader reader = new StringReader(fileContent);
        T object = (T) unmarshaller.unmarshal(reader);
        reader.close();
        return object;
    }

    @Override
    public <T> String write(T object) throws IOException, JAXBException {
        this.jaxbContext=JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(object,stringWriter);
        return stringWriter.toString();
    }
}
