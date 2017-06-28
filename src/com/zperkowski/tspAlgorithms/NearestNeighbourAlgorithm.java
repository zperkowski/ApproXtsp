package com.zperkowski.tspAlgorithms;

import com.zperkowski.TSPMatrix;

/**
 * Created by zperkowski on 24/05/2017.
 */
public class NearestNeighbourAlgorithm extends TspAlgorithm {

    public void calculateTour() {
        if (tspList != null) {
            TSPMatrix matrix = new TSPMatrix(tspList);
            matrix.generateDistances();
            distance = 0;
            double minCurrentDistance = 0;
            int currentVertex = 0;
            int closestCurrentNeighbour = 1;
            tourList.add(0);
            while (tourList.size() < matrix.size()) {
                currentVertex = tourList.getLast();
                // Looks for the true minCurrentDistance
                minCurrentDistance = 0;
                for (int neighbour = 0; neighbour < matrix.size(); neighbour++) {
                    if (!tourList.contains(neighbour) && currentVertex != neighbour)
                        if (matrix.get(currentVertex).get(neighbour) < minCurrentDistance
                                || minCurrentDistance == 0) {
                            minCurrentDistance = matrix.get(currentVertex).get(neighbour);
                            closestCurrentNeighbour = neighbour;
                        }
                }
                tourList.add(closestCurrentNeighbour);
                distance += minCurrentDistance;
            }
            tourList.add(0);
            distance += matrix.get(currentVertex).get(0);
        }
    }
}
