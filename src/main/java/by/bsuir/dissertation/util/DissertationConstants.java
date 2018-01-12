package by.bsuir.dissertation.util;

public final class DissertationConstants {

    public static final class XML_PARSE {
        public static final String TAG_NODE = "node";
        public static final String TAG_WAY = "way";
        public static final String TAG_ND = "nd";
        public static final String TAG_TAG = "tag";
        public static final String ATTRIBUTE_ID = "id";
        public static final String ATTRIBUTE_LAT = "lat";
        public static final String ATTRIBUTE_LON = "lon";
        public static final String ATTRIBUTE_REF = "ref";
        public static final String ATTRIBUTE_K = "k";
        public static final String ATTRIBUTE_V = "v";
        public static final String VALUE_HIGHWAY = "highway";
        public static final String VALUE_LANDUSE = "landuse";
    }

    public static final class CAR_MOVEMENT {
        public static final int CAR_MAX_COUNT = 50;

        /**
         * In seconds
         */
        public static final int SAMPLING_FREQUENCY = 1;

        /**
         * In meter per second
         */
        public static final double INITIAL_SPEED = 0;
        /**
         * In meter per second
         */
        public static final double MAX_SPEED = 2.7;
        /**
         * In meter
         */
        public static final double INITIAL_DISTANCE = 0;
        /**
         * In meter per second squared
         */
        public static final double ACCELERATION = 1.6;
    }
}
