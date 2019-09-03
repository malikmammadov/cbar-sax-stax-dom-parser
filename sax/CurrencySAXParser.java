package sax;

import domain.Currency;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrencySAXParser extends DefaultHandler {

    private Map<String, List<Currency>> map;
    private List<Currency> list;
    private Currency currency;

    private String date;
    private String name;
    private String description;
    private String type;

    private boolean isNominal;
    private boolean isName;
    private boolean isValue;

    @Override
    public void startDocument() throws SAXException {
        map = new HashMap<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("ValCurs")) {
            date = attributes.getValue("Date");
            name = attributes.getValue("Name");
            description = attributes.getValue("Description");
        } else if (qName.equals("ValType")) {
            list = new ArrayList<>();
            type = attributes.getValue("Type");
        } else if (qName.equals("Valute")) {
            currency = new Currency();
            currency.setCode(attributes.getValue("Code"));
        } else if (qName.equals("Nominal")) {
            isNominal = true;
        } else if (qName.equals("Name")) {
            isName = true;
        } else if (qName.equals("Value")) {
            isValue = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String data = new String(ch, start, length);
        if (isNominal) {
            currency.setNominal(data);
            isNominal = false;
        } else if (isName) {
            currency.setName(data);
            isName = false;
        } else if (isValue) {
            currency.setValue(new BigDecimal(data));
            isValue = false;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("Valute")) {
            list.add(currency);
            currency = null;
        } else if (qName.equals("ValType")) {
            map.put(type, list);
            list = null;
        }
    }

    @Override
    public void endDocument() throws SAXException {
    }

    public Map<String, List<Currency>> getMap() {
        return map;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
