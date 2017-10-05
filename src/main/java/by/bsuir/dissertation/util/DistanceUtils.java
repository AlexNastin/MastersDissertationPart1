package by.bsuir.dissertation.util;

public class DistanceUtils {

    public static double distanceCalculate(String latitude1, String longitude1, String latitude2, String longitude2) {
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
        return ad * rad;
    }

    private static double toRadian(double value) {
        return value * Math.PI / 180;
    }
}
