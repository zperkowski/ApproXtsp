package com.zperkowski.tspAlgorithms;

import com.zperkowski.TSPMatrix;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by zperkowski on 31/05/2017.
 */
public class ApproxTspTourAlgorithm extends TspAlgorithm {
    Tree MinimalSpanningTree;

    @Override
    public void calculateTour() {
        TSPMatrix matrix = new TSPMatrix(tspList);
        matrix.generateDistances();
        MinimalSpanningTree = calculateMinimalSpanningTree(matrix);
    }

    private Tree calculateMinimalSpanningTree(TSPMatrix matrix) {
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
            minCurrentWeight = weight +
                    matrix.get(0).get((Integer) availableVertexes.get(0));
            closestVertexFrom = 0;
            closestVertexTo = (Integer) availableVertexes.get(0);

            for (int vertex :
                    (ArrayList<Integer>) msTree.getPreOrder()) {
                for (int neighbour :
                        (ArrayList<Integer>) availableVertexes)
                    if (vertex != neighbour)
                        if (minCurrentWeight > weight + matrix.get(vertex).get(neighbour)) {
                            minCurrentWeight = weight + matrix.get(vertex).get(neighbour);
                            closestVertexFrom = vertex;
                            closestVertexTo = neighbour;
                        }
            }
            weight = minCurrentWeight;
            System.out.println(closestVertexFrom + " " + closestVertexTo);
            Tree from = msTree.findInChildren(closestVertexFrom);
            from.addChild(closestVertexTo);
            availableVertexes.removeAll(Arrays.asList(closestVertexTo));
        }
        // TODO: Sometimes Tree from is null.
        return msTree;
    }
}
