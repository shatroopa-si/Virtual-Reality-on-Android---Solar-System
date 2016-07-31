package com.example.android.version7;

/**
 * Created by HP on 7/23/2016.
 */
public class SpaceController {

    private static final int NUMBER_OF_COMPONENTS = 9;

    public Node initCreateRelate()
    {
        //build solar sytem
        Node solarSystem = new Node(null, "solar system");

        //build sun and the planets
        for(int i = 0; i < NUMBER_OF_COMPONENTS; i++)
        {
            Node element = new Node(solarSystem, "" + i, i);
            solarSystem.addChild(element);
        }



        return solarSystem;
    }


}
