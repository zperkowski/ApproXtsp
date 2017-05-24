package com.zperkowski;

import java.util.LinkedList;

/**
 * Created by zperkowski on 24/05/2017.
 */
public class NearestNeighbourAlgorithm {
    private int currentVertex;
    private double currentDistance;
    private LinkedList<Integer> tourList;
    private TSPList tspList;

    public void addCities(TSPList cities) {
        if (tspList == null || tspList.isEmpty())
            tspList = cities;
        else
            throw new SecurityException("The algorithm has a list loaded. Clear it first!");
    }

    public void removeCities() {
        tspList = null;
    }

}
