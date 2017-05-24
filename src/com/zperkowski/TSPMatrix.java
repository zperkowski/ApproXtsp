package com.zperkowski;

import java.util.ArrayList;

/**
 * Created by zperkowski on 23/05/2017.
 */
public class TSPMatrix extends ArrayList<ArrayList<Double>> {

    /**
     * Creates new matrix and generates distances from given TSPList.
     * @param tspList A List of coordinates
     */
    public TSPMatrix(TSPList tspList) {
        this.addAll(tspList);
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


    public boolean addAll(TSPList tspList) {
        this.clear();
        if (tspList.size() > 0) {
            int quantity = tspList.get(0).get(1);
            for (int i = 0; i < quantity; i++) {
                    this.add(new ArrayList(quantity));
                    for (int j = 0; j < quantity; j++)
                        this.get(i).add(0.0);
            }
        }
        if (this.size() == tspList.size() - 1) {
            this.generateDistances(tspList);
            return true;
        }
        return false;
    }

    public void generateDistances(TSPList tspList) {
        if (this.size() != tspList.size() - 1) {
            this.clear();
            this.addAll(tspList);
        }

        for (int i = 0; i < this.size(); i++) {
            double x1 = tspList.get(i+1).get(1);
            double y1 = tspList.get(i+1).get(2);
            for (int j = 0; j < i; j++) {
                double x2 = tspList.get(j+1).get(1);
                double y2 = tspList.get(j+1).get(2);
                double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
                this.get(i).set(j, distance);
                this.get(j).set(i, distance);
            }
        }
    }
}
