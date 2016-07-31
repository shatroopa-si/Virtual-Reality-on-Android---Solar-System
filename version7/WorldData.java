package com.example.android.version7;

/**
 * Created by HP on 7/13/2016.
 */
public final class WorldData {
    //dimensions of each spatial body are stored here

    private static final String[] names = {
            "Sun",
            "Mercury",
            "Venus",
            "Earth",
            "Mars",
            "Jupiter",
            "Saturn",
            "Uranus",
            "Neptune"
    };

    public static String getName(int i){return names[i];}

    private static final float[] radius = {     // unit: kms
           2.50f,                 //696000,                    // body: sun
           0.25f,                 //2440,                       // body: mercury
           0.75f,                 //6052,                       // body: venus
           0.85f,                 //6371,                       // body: earth
           0.50f,                 //3390,                       // body: mars
           1.20f,                 //69911,                      // body: jupiter
           0.80f,                 //58232,                      // body: saturn
           0.70f,                 //25362,                      // body: uranus
           0.60f                 //24622                       // body: neptune

    };
    public static float getRadius(int i){return radius[i];}

    public static final float[] actualRadius = {     // unit: kms
           696000,                    // body: sun
           2440,                       // body: mercury
           6052,                       // body: venus
           6371,                       // body: earth
           3390,                       // body: mars
           69911,                      // body: jupiter
           58232,                      // body: saturn
           25362,                      // body: uranus
           24622                       // body: neptune
    };

    private static final float[] distance = {   // unit: million kms
            0.0f,            // body: sun
            3.0f,            //57.91f,                     // body: mercury
            4.4f,            //108.2f,                     // body: venus
            6.4f,            //149.6f,                     // body: earth
            8.4f,            //227.9f,                     // body: mars
            10.4f,            //778.5f,                     // body: jupiter
            13.0f,            //1429,                       // body: saturn
            15.0f,            //2877,                       // body: uranus
            17.0f            //4498                        // body: neptune
    };

    public static float getDistance(int i){return distance[i];}

    public static final float[] actualDistance = {   // unit: million kms
            0.0f,                       // body: sun
            57.91f,                     // body: mercury
            108.2f,                     // body: venus
            149.6f,                     // body: earth
            227.9f,                     // body: mars
            778.5f,                     // body: jupiter
            1429,                       // body: saturn
            2877,                       // body: uranus
            4498                        // body: neptune
    };


    private static final float[] orbitalPeriod = {  // unit: km/s
            0.00f,            //0,                          // body: sun
            4.70f,            //47.87f,                     // body: mercury
            3.50f,            //35.02f,                     // body: venus
            2.90f,            //29.78f,                     // body: earth
            2.40f,            //24.077f,                    // body: mars
            1.30f,            //13.07f,                     // body: jupiter
            0.96f,            //9.69f,                      // body: saturn
            0.68f,            //6.81f,                      // body: uranus
            0.54f             //5.43f                       // body: neptune

    };

    public static float getSpeed(int i){return orbitalPeriod[i];}

    public static final float[] actualOrbitalPeriod = {  // unit: km/s
            0,                          // body: sun
            47.87f,                     // body: mercury
            35.02f,                     // body: venus
            29.78f,                     // body: earth
            24.077f,                    // body: mars
            13.07f,                     // body: jupiter
            9.69f,                      // body: saturn
            6.81f,                      // body: uranus
            5.43f                       // body: neptune
    };

    private static final boolean[] rings = {
            false,                         // sun
            false,                      // body: mercury
            false,                      // body: venus
            false,                      // body: earth
            false,                      // body: mars
            false,                      // body: jupiter
            true,                       // body: saturn
            false,                      // body: uranus
            false                       // body: neptune
    };

    public static boolean getRings(int i){return rings[i];}

    private static final int[] nMoons = {
            0,                             // sun
            0,                          // body: mercury
            0,                          // body: venus
            1,                          // body: earth
            2,                          // body: mars
            62,                         // body: jupiter
            33,                         // body: saturn
            27,                          // body: uranus
            13                          // body: neptune
    };

    public static int getMoons(int i){return nMoons[i];}


}
