package com.zperkowski;

import java.util.ArrayList;

/**
 * Created by zperkowski on 23/05/2017.
 */
public class TSPMatrix extends ArrayList<ArrayList<Double>> {
    private TSPList list;

    /**
     * Creates new matrix and generates distances from given TSPList.
     * @param tspList A List of coordinates
     */
    public TSPMatrix(TSPList tspList) {
        this.setTSPList(tspList);
    }

    /**
     * Constructor creates empty matrix. To add values:
     * TSPMatrix tspMatrix = new TSPMatrix(2);
     * tspMatrix.get(0).set(1, 10.0);
     * tspMatrix.get(1).set(0, 10.0);
     * @param size Size of matrix
     */
    public TSPMatrix(int size){
        list = new TSPList(size, 1);
        this.generateSize();
    }

    public void setTSPList(TSPList tspList) {
        list = tspList;
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


    private void generateSize() {
        this.clear();
        if (list.size() > 0) {
            int quantity = list.get(0).get(1);
            for (int i = 0; i < quantity; i++) {
                    this.add(new ArrayList(quantity));
                    for (int j = 0; j < quantity; j++)
                        this.get(i).add(0.0);
            }
        }
    }

    public void generateDistances() {
        this.generateSize();

        if (this.size() == list.size() - 1)
            for (int i = 0; i < this.size(); i++) {
                double x1 = list.get(i+1).get(1);
                double y1 = list.get(i+1).get(2);
                for (int j = 0; j < i; j++) {
                    double x2 = list.get(j+1).get(1);
                    double y2 = list.get(j+1).get(2);
                    double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
                    this.get(i).set(j, distance);
                    this.get(j).set(i, distance);
                }
            }
    }
}
