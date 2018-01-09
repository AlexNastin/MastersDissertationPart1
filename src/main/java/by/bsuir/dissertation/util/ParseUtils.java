package by.bsuir.dissertation.util;

import by.bsuir.dissertation.entity.graph.Node;
import by.bsuir.dissertation.parser.entity.OSMWay;

import java.util.List;

public class ParseUtils {

    public static int distanceCalculate(String latitude1, String longitude1, String latitude2, String longitude2) {
//        Радиус сферы (Земли)
        final int rad = 6372795;

//        Координаты в радианах
        double lat1 = toRadian(Double.valueOf(latitude1));
        double lon1 = toRadian(Double.valueOf(longitude1));
        double lat2 = toRadian(Double.valueOf(latitude2));
        double lon2 = toRadian(Double.valueOf(longitude2));

//        Косинусы и синусы широт и разницы долгот
        double cosLat1 = Math.cos(lat1);
        double cosLat2 = Math.cos(lat2);
        double sinLat1 = Math.sin(lat1);
        double sinLat2 = Math.sin(lat2);
        double delta = lon2 - lon1;
        double cosDelta = Math.cos(delta);
        double sinDelta = Math.sin(delta);

//        Вычисление длины большого круга
        double y = Math.sqrt(Math.pow(cosLat2 * sinDelta, 2) + Math.pow(cosLat1 * sinLat2 - sinLat1 * cosLat2 * cosDelta, 2));
        double x = sinLat1 * sinLat2 + cosLat1 * cosLat2 * cosDelta;
        double ad = Math.atan2(y, x);
        return (int) (ad * rad);
    }

    public static boolean containsPointInPolygon(OSMWay polygon, Node node) {
        List<Node> polygonNodes = polygon.getNodes();
        if (polygonNodes == null || polygonNodes.size() <= 3 || !polygonNodes.get(0).equals(polygonNodes.get(polygonNodes.size() - 1))) {
            return false;
        }
        int hits = 0;

        double lastX = Double.valueOf(polygonNodes.get(polygonNodes.size() - 1).getLatitude());
        double lastY = Double.valueOf(polygonNodes.get(polygonNodes.size() - 1).getLongitude());
        double curX, curY;

        // Walk the edges of the polygon
        for (int i = 0; i < polygonNodes.size(); lastX = curX, lastY = curY, i++) {
            curX = Double.valueOf(polygonNodes.get(i).getLatitude());
            curY = Double.valueOf(polygonNodes.get(i).getLongitude());

            double x = Double.valueOf(node.getLatitude());
            double y = Double.valueOf(node.getLongitude());

            if (curY == lastY) {
                continue;
            }

            double leftX;
            if (curX < lastX) {
                if (x >= lastX) {
                    continue;
                }
                leftX = curX;
            } else {
                if (x >= curX) {
                    continue;
                }
                leftX = lastX;
            }

            double test1, test2;
            if (curY < lastY) {
                if (y < curY || y >= lastY) {
                    continue;
                }
                if (x < leftX) {
                    hits++;
                    continue;
                }
                test1 = x - curX;
                test2 = y - curY;
            } else {
                if (y < lastY || y >= curY) {
                    continue;
                }
                if (x < leftX) {
                    hits++;
                    continue;
                }
                test1 = x - lastX;
                test2 = y - lastY;
            }

            if (test1 < (test2 / (lastY - curY) * (lastX - curX))) {
                hits++;
            }
        }
        return ((hits & 1) != 0);
    }

    private static double toRadian(double value) {
        return value * Math.PI / 180;
    }
}
