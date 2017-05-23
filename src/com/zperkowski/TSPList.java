package com.zperkowski;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by zperkowski on 17/05/2017.
 */
class TSPList extends ArrayList<ArrayList<Integer>> {

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

        if (quantity > 0) {
            this.add(new ArrayList<>(Arrays.asList(0, quantity)));

            for (int i = 1; i < quantity + 1; i++) {
                this.add(new ArrayList<>(Arrays.asList(i, random.nextInt(maxValue), random.nextInt(maxValue))));
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
