package com.zperkowski;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Created by zperkowski on 17/05/2017.
 */
public class TSPList extends ArrayList<ArrayList<Integer>> {

    public TSPList(int quantity, int maxValue) {
        this.generate(quantity, maxValue);
    }

    /**
     * Generates an array with random coordinates.
     * The method first clears the list.
     * @param quantity Quantity of lines / cities.
     * @param maxValue Maximal value of the coordinates.
     */
    void generate(int quantity, int maxValue) {
        this.clear();
        Random random = new Random();
        ArrayList usedCoordinates = new ArrayList<Integer>();
        int x, y;

        if (quantity > 0) {
            this.add(new ArrayList<>(Arrays.asList(0, quantity)));

            for (int i = 1; i < quantity + 1; i++) {
                do {
                    x = random.nextInt(maxValue);
                    y = random.nextInt(maxValue);
                    // Repeat when the sublist is on a even position.
                    // First the x is added to the list and then y. Both have to belong to the same point.
                } while (Collections.indexOfSubList(usedCoordinates, Arrays.asList(x, y)) % 2 == 0
                        && Collections.indexOfSubList(usedCoordinates, Arrays.asList(x, y)) > 0);
                usedCoordinates.add(x);
                usedCoordinates.add(y);
                this.add(new ArrayList<>(Arrays.asList(i, x, y)));
            }
        }
    }

    @Override
    public String toString() {
        if (this.isEmpty())
            return "";

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.get(i).size(); j++) {
                sb.append(this.get(i).get(j).toString() + " ");
            }
            // To remove redundant space on the end of every line
            sb.deleteCharAt(sb.length()-1);
            sb.append("\n");
        }

        return sb.toString();
    }
}
