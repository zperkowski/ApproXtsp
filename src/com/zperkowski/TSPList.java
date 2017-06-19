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
        ArrayList usedCoordinates = new ArrayList<ArrayList<Integer>>();
        int x, y;

        if (quantity > 0) {
            this.add(new ArrayList<>(Arrays.asList(0, quantity)));

            for (int i = 1; i < quantity + 1; i++) {
                do {
                    x = random.nextInt(maxValue);
                    y = random.nextInt(maxValue);
                    // TODO: Remove endless loop. It appears when all possibilities are used.
                    // Eg. Quantity 5+ and max value 2.
                } while (Collections.indexOfSubList(usedCoordinates, Arrays.asList(Arrays.asList(x, y))) >= 0);
                usedCoordinates.add(Arrays.asList(x, y));
                this.add(new ArrayList<>(Arrays.asList(i, x, y)));
            }
        }
    }

    public void setFromFile(String input) {
        int i;
        String[] elements;
        ArrayList arrayList;
        for (String line : input.split("\\n")) {
            elements = line.split("\\s+");
            arrayList = new ArrayList(elements.length);
            for (String element:
                 elements) {
                if ((i = element.indexOf('.')) > 0)
                    element = element.substring(0, i);
                arrayList.add(Integer.parseInt(element));
            }
            this.add(arrayList);
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
