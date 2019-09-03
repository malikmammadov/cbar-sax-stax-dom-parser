package stax;

import domain.Currency;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrencyStAXParser {
    private Map<String, List<Currency>> map = new HashMap<>();
    private List<Currency> list;
    private Currency currency;

    private String date;
    private String name;
    private String description;
    private String type;

    private boolean isNominal;
    private boolean isName;
    private boolean isValue;

    public void parse(XMLStreamReader reader) {
        try {
            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLEvent.START_ELEMENT) {
                    String element = reader.getLocalName();

                    if (element.equals("ValCurs")) {
                        date = reader.getAttributeValue("", "Date");
                        name = reader.getAttributeValue("", "Name");
                        description = reader.getAttributeValue("", "Description");
                    } else if (element.equals("ValType")) {
                        list = new ArrayList<>();
                        type = reader.getAttributeValue("", "Type");
                    } else if (element.equals("Valute")) {
                        currency = new Currency();
                        currency.setCode(reader.getAttributeValue("", "Code"));
                    } else if (element.equals("Nominal")) {
                        isNominal = true;
                    } else if (element.equals("Name")) {
                        isName = true;
                    } else if (element.equals("Value")) {
                        isValue = true;
                    }
                } else if (event == XMLEvent.CHARACTERS) {
                    String data = reader.getText();

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
                } else if (event == XMLEvent.END_ELEMENT) {
                    String element = reader.getLocalName();

                    if (element.equals("Valute")) {
                        list.add(currency);
                        currency = null;
                    } else if (element.equals("ValType")) {
                        map.put(type, list);
                        list = null;
                    }
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
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
