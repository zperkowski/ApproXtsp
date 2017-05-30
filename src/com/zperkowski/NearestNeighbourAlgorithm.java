package com.zperkowski;

import java.util.LinkedList;

/**
 * Created by zperkowski on 24/05/2017.
 */
public class NearestNeighbourAlgorithm {
    private double distance;
    private LinkedList<Integer> tourList = new LinkedList<Integer>();
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

    public void calculateTour() {
        if (tspList != null) {
            TSPMatrix matrix = new TSPMatrix(tspList);
            matrix.generateDistances();
            double minCurrentDistance = matrix.get(0).get(1);
            int currentVertex;
            int closestCurrentNeighbour = 1;
            tourList.add(0);
            while (tourList.size() < matrix.size()) {
                currentVertex = tourList.getLast();

                // Looks for new value of minCurrentDistance
                for (int i = matrix.size()-1; i > 0; i--)
                    if (!tourList.contains(i) && currentVertex != i) {
                        minCurrentDistance = matrix.get(currentVertex).get(i);
                        closestCurrentNeighbour = i;
                        break;
                    }
                // Looks for the true minCurrentDistance
                for (int neighbour = 0; neighbour < matrix.size(); neighbour++) {
                    if (!tourList.contains(neighbour) && currentVertex != neighbour)
                        if (matrix.get(currentVertex).get(neighbour) < minCurrentDistance) {
                            minCurrentDistance = matrix.get(currentVertex).get(neighbour);
                            closestCurrentNeighbour = neighbour;
                        }
                }
                tourList.add(closestCurrentNeighbour);
                distance += minCurrentDistance;
            }
        }
    }

    @Override
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
