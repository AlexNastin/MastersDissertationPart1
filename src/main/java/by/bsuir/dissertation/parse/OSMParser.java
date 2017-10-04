package by.bsuir.dissertation.parse;

import by.bsuir.dissertation.entity.Edge;
import by.bsuir.dissertation.entity.Node;
import by.bsuir.dissertation.util.DissertationConstants;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class OSMParser extends DefaultHandler {

    private List<Node> nodesTemp = new ArrayList<>();
    private List<Node> nodes = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();
    private List<OSMWay> osmWays = new ArrayList<>();
    private OSMWay osmWay = null;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {

        switch (qName) {
            case DissertationConstants.XML_PARSE.TAG_NODE:
                Node node = new Node();
                node.setNumber(attributes.getValue(DissertationConstants.XML_PARSE.ATTRIBUTE_ID));
                node.setLatitude(attributes.getValue(DissertationConstants.XML_PARSE.ATTRIBUTE_LAT));
                node.setLongitude(attributes.getValue(DissertationConstants.XML_PARSE.ATTRIBUTE_LON));
                nodesTemp.add(node);
                break;
            case DissertationConstants.XML_PARSE.TAG_WAY:
                osmWay = new OSMWay();
                break;
            case DissertationConstants.XML_PARSE.TAG_ND:
                osmWay.addNode(nodesTemp.stream().filter(l -> l.getNumber().equals(attributes.getValue(DissertationConstants.XML_PARSE.ATTRIBUTE_REF))).findFirst().get());
                break;
            case DissertationConstants.XML_PARSE.TAG_TAG:
                if (attributes.getValue(DissertationConstants.XML_PARSE.ATTRIBUTE_K).equals(DissertationConstants.XML_PARSE.VALUE_HIGHWAY)) {
                    for ( OSMHighwayTypes type: OSMHighwayTypes.values()) {
                        if (attributes.getValue(DissertationConstants.XML_PARSE.ATTRIBUTE_V).equalsIgnoreCase(type.toString())) {
                            osmWays.add(osmWay);
                            break;
                        }
                    }
                }
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();

        osmWays.forEach(way -> {
            List<Node> nodes = way.getNodes();
            if (nodes != null && nodes.size() >= 2) {
                for (int i = 0; i < nodes.size() - 1; i++) {
                    Node node1 = nodes.get(i);
                    Node node2 = nodes.get(i + 1);
                    Edge edge = new Edge(123, node1, node2);
                    node1.worksWith(edge);
                    node2.worksWith(edge);
                    edges.add(edge);
                }
            }
        });

        nodesTemp.forEach(node -> {
            if (node.getEdges() != null && node.getEdges().size() > 0) {
                nodes.add(node);
            }
        });
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public List<OSMWay> getOsmWays() {
        return osmWays;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
