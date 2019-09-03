package dom;

import domain.Cbar;
import domain.Currency;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        CurrencyDOMParser currencyDOMParser = new CurrencyDOMParser();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(Cbar.URL);
            document.normalizeDocument();
            currencyDOMParser.parse(document);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        System.out.println(currencyDOMParser.getDate() + " " + currencyDOMParser.getName() + "\n"
                + currencyDOMParser.getDescription());
        Set<Map.Entry<String, List<Currency>>> entries = currencyDOMParser.getMap().entrySet();
        for (Map.Entry<String, List<Currency>> entry : entries) {
            System.out.println("Type" + String.format("%41s %20s %20s %21s", "Currency", "Nominal", "Code", "Value\n"));
            System.out.println(entry.getKey());
            for (Currency currency : entry.getValue()) {
                System.out.println(currency);
            }
        }
    }
}
