package dom;

import domain.Currency;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrencyDOMParser {
    private Map<String, List<Currency>> map;
    private List<Currency> list;
    private Currency currency;

    private String date;
    private String name;
    private String description;
    private String type;

    public void parse(Document document) {
        Element root = document.getDocumentElement();

        date = root.getAttribute("Date");
        name = root.getAttribute("Name");
        description = root.getAttribute("Description");
        map = new HashMap<>();
        NodeList valtypeList = root.getElementsByTagName("ValType");
        for (int j = 0; j < valtypeList.getLength(); j++) {
            Node valtypeNode = valtypeList.item(j);
            if (valtypeNode.getNodeType() == Node.ELEMENT_NODE) {
                Element valtypeElement = (Element) valtypeNode;
                type = valtypeElement.getAttribute("Type");
                list = new ArrayList<>();
                NodeList valuteList = valtypeElement.getElementsByTagName("Valute");
                for (int k = 0; k < valuteList.getLength(); k++) {
                    Node valuteNode = valuteList.item(k);
                    if (valuteNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element valuteElement = (Element) valuteNode;
                        currency = new Currency();
                        currency.setCode(valuteElement.getAttribute("Code"));
                        currency.setNominal(valuteElement.getElementsByTagName("Nominal").item(0).getTextContent());
                        currency.setName(valuteElement.getElementsByTagName("Name").item(0).getTextContent());
                        currency.setValue(new BigDecimal(valuteElement.getElementsByTagName("Value").item(0).getTextContent()));
                        list.add(currency);
                        currency = null;
                    }
                }
            }
            map.put(type, list);
            list = null;
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