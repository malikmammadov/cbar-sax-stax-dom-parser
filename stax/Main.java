package stax;

import domain.Cbar;
import domain.Currency;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        CurrencyStAXParser currencyStAXParser = new CurrencyStAXParser();
        try {
            URL url = new URL(Cbar.URL);
            XMLInputFactory factory = XMLInputFactory.newFactory();
            XMLStreamReader reader = factory.createXMLStreamReader(url.openStream());
            currencyStAXParser.parse(reader);
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }

        System.out.println(currencyStAXParser.getDate() + " " + currencyStAXParser.getName() + "\n"
                + currencyStAXParser.getDescription());
        Set<Map.Entry<String, List<Currency>>> entries = currencyStAXParser.getMap().entrySet();
        for (Map.Entry<String, List<Currency>> entry : entries) {
            System.out.println("Type" + String.format("%41s %20s %20s %21s", "Currency", "Nominal", "Code", "Value\n"));
            System.out.println(entry.getKey());
            for (Currency currency : entry.getValue()) {
                System.out.println(currency);
            }
        }
    }
}
