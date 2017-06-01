package com.zperkowski.tspAlgorithms;

import com.zperkowski.TSPMatrix;

import java.util.ArrayList;

/**
 * Created by zperkowski on 31/05/2017.
 */
public class ApproxTspTourAlgorithm extends TspAlgorithm {
    Tree MinimalSpanningTree;

    @Override
    public void calculateTour() {

    }

    private Tree calculateMinimalSpanningTree (TSPMatrix matrix) {
        Tree msTree = new Tree(0);
        ArrayList availableVertexes = new ArrayList(0);
        for (int i = 0; i < matrix.size(); i++)
            availableVertexes.add(i);
        double weight = matrix.get(0).get(1);
        while (availableVertexes.size() > 0) {
            ArrayList moves = possibleMoves(msTree, matrix);
            for (int i = 0; i < moves.size(); i++) {
                // Find the lowest distance
            }
        }

        return msTree;
    }

    /**
     * Creates a list with every possible moves from leafs.
     * @param tree Gets current situation.
     * @param matrix Gets distances.
     * @return Returns a list with possible moves - from, to and distance.
     */
    private ArrayList<ArrayList> possibleMoves (Tree tree, TSPMatrix matrix) {
        ArrayList<ArrayList> moves = new ArrayList<>(0);

        // Recursive looking for leafs in the tree

        return moves;
    }

    /**
     * The method walks through the tree in pre order.
     * @param tree Given tree to walk.
     * @return Returns a list with vertexes in pre order.
     */
    private ArrayList preOrder (Tree tree) {
        ArrayList preOrder = new ArrayList();

        return preOrder;
    }
}
