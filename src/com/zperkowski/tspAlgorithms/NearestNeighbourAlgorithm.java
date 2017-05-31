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
}
