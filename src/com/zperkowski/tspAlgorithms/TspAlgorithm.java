package com.zperkowski.tspAlgorithms;

import com.zperkowski.TSPList;

import java.util.LinkedList;

/**
 * Created by zperkowski on 31/05/2017.
 */
public abstract class TspAlgorithm {
    double distance;
    LinkedList<Integer> tourList = new LinkedList<Integer>();
    TSPList tspList;

    public abstract void calculateTour();

    public void addCities(TSPList cities) {
        if (tspList == null || tspList.isEmpty())
            tspList = cities;
        else
            throw new SecurityException("The algorithm has a list loaded. Clear it first!");
    }

    public void removeCities() {
        tspList = null;
    }

    public String toString() {
        if (tourList.isEmpty())
            return "";

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tourList.size(); i++) {
            sb.append(tourList.get(i).toString() + " ");
        }
        sb.deleteCharAt(sb.length()-1);

        sb.append("\n" + this.distance + "\n");

        return sb.toString();
    }

}