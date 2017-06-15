package com.zperkowski.tspAlgorithms;

import com.zperkowski.TSPMatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by zperkowski on 31/05/2017.
 */
public class ApproxTspTourAlgorithm extends TspAlgorithm {
    Tree minimalSpanningTree;

    @Override
    public void calculateTour() {
        distance = 0;
        TSPMatrix matrix = new TSPMatrix(tspList);
        matrix.generateDistances();
        minimalSpanningTree = calculateMinimalSpanningTree(matrix);
        tourList = minimalSpanningTree.getPreOrder();
        tourList.add(0);
        for (int i = 0; i < tourList.size() - 1; i++) {
            distance += matrix.get(tourList.get(i)).get(tourList.get(i+1));
        }
    }

    public Tree calculateMinimalSpanningTree(TSPMatrix matrix) {
        Tree msTree = new Tree(0); // Starting from 0
        double weight = 0;
        double minCurrentWeight = 0;
        int closestVertexFrom = 0;
        int closestVertexTo = 1;

        ArrayList availableVertexes = new ArrayList(0);
        for (int i = 0; i < matrix.size(); i++)
            availableVertexes.add(i);
        availableVertexes.remove(0); // Starting from 0

        while (availableVertexes.size() > 0) {
            for (int vertex :
                    (LinkedList<Integer>) msTree.getPreOrder()) {
                for (int neighbour :
                        (ArrayList<Integer>) availableVertexes)
                    if (vertex != neighbour && matrix.get(vertex).get(neighbour) != 0)
                        if (minCurrentWeight > weight + matrix.get(vertex).get(neighbour)
                                || minCurrentWeight == weight) {
                            minCurrentWeight = weight + matrix.get(vertex).get(neighbour);
                            closestVertexFrom = vertex;
                            closestVertexTo = neighbour;
                        }
            }

            if (minCurrentWeight == weight)
                throw new ArithmeticException("Isolated vertex");

            weight = minCurrentWeight;
            Tree from = msTree.findInChildren(closestVertexFrom);
            from.addChild(closestVertexTo);
            availableVertexes.removeAll(Arrays.asList(closestVertexTo));
        }
        return msTree;
    }
}
