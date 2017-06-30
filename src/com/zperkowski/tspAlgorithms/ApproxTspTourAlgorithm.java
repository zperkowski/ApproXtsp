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

    public void calculateAllTours() {
        ApproxTspTourAlgorithm currentATT = new ApproxTspTourAlgorithm();
        currentATT.addCities(tspList);
        TSPMatrix matrix = new TSPMatrix(tspList);
        matrix.generateDistances();

        for (int i = 0; i < tspList.size() - 1; i++) {
            System.out.print(i + " ");
            currentATT.calculateTour(i, matrix);
            if (currentATT.distance < this.distance
                    || this.distance == 0) {
                this.distance = currentATT.distance;
                this.tourList = currentATT.tourList;
            }
        }
    }

    /**
     * Calculates the shortest tour. Starts from the 1st vertex.
     */
    @Override
    public void calculateTour() {
        TSPMatrix matrix = new TSPMatrix(tspList);
        matrix.generateDistances();
        calculateTour(0, matrix);
    }

    public void calculateTour(TSPMatrix matrix) {
        calculateTour(0, matrix);
    }

    public void calculateTour(int startVertex, TSPMatrix matrix) {
        distance = 0;
        minimalSpanningTree = calculateMinimalSpanningTree(matrix, startVertex);
        tourList = minimalSpanningTree.getPreOrder();
        tourList.add(startVertex);
        for (int i = 0; i < tourList.size() - 1; i++) {
            distance += matrix.get(tourList.get(i)).get(tourList.get(i+1));
        }
    }

    /**
     * Calculates a MST. Starts from the 1st vertex.
     * @param matrix A adjacency matrix.
     * @return Returns the Minimal Spanning Tree.
     */
    public Tree calculateMinimalSpanningTree(TSPMatrix matrix) {
        return calculateMinimalSpanningTree(matrix, 0);
    }

    public Tree calculateMinimalSpanningTree(TSPMatrix matrix, int startVertex) {
        Tree msTree = new Tree(startVertex);
        double weight = 0;
        double minCurrentWeight = 0;
        int closestVertexFrom = startVertex;
        int closestVertexTo;

        if (startVertex < matrix.size())
            closestVertexTo = startVertex + 1;
        else
            closestVertexTo = startVertex - 1;


        ArrayList availableVertexes = new ArrayList(0);
        for (int i = 0; i < matrix.size(); i++)
            availableVertexes.add(i);
        availableVertexes.remove(startVertex);

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
