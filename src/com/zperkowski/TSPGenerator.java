package com.zperkowski;

import java.util.Random;

/**
 * Created by zperkowski on 17/05/2017.
 */
class TSPGenerator {

    /**
     * Generates an array with random coordinates.
     * @param quantity Quantity of lines / cities.
     * @param maxValue Maximal value of the coordinates.
     * @return Returns an array[quantity][3] - 1st element has just a line number, next two contain the coordinates.
     */
    static int[][] generate(int quantity, int maxValue) {
        Random random = new Random();
        int[][] problem = new int[quantity+1][3];

        problem[0][1] = quantity;

        System.out.println(problem[0][0]);

        for (int i = 1; i < quantity+1; i++) {
            problem[i][0] = i;
            problem[i][1] = random.nextInt(maxValue);
            problem[i][2] = random.nextInt(maxValue);
        }
        
        return problem;
    }
}
