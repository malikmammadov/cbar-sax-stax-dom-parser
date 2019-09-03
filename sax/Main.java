package sax;

import domain.Cbar;
import domain.Currency;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        CurrencySAXParser currencySAXParser = new CurrencySAXParser();
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(Cbar.URL, currencySAXParser);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        System.out.println(currencySAXParser.getDate() + " " + currencySAXParser.getName() + "\n"
                + currencySAXParser.getDescription());
        Set<Map.Entry<String, List<Currency>>> entries = currencySAXParser.getMap().entrySet();
        for (Map.Entry<String, List<Currency>> entry : entries) {
            System.out.println("Type" + String.format("%41s %20s %20s %21s", "Currency", "Nominal", "Code", "Value\n"));
            System.out.println(entry.getKey());
            for (Currency currency : entry.getValue()) {
                System.out.println(currency);
            }
        }
    }
}
