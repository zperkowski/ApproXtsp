package com.zperkowski.tspAlgorithms;

import com.zperkowski.TSPMatrix;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zperkowski on 31/05/2017.
 * Source: https://stackoverflow.com/questions/19330731/tree-implementation-in-java-root-parents-and-children
 */
public class Tree<T> {
    private List<Tree<T>> children = new ArrayList<Tree<T>>();
    private Tree<T> parent = null;
    private T data = null;

    public Tree(T data) {
        this.data = data;
    }

    public Tree(T data, Tree<T> parent) {
        this.data = data;
        this.parent = parent;
    }

    /**
     * Creates a tree based on a adjacency matrix.
     * Starts from vertex 0.
     * @param matrix The adjacency matrix.
     */
    public Tree(TSPMatrix matrix) {
        // TODO
    }

    public List<Tree<T>> getChildren() {
        return children;
    }

    public void setParent(Tree<T> parent) {
        this.parent = parent;
    }

    public void addChild(T data) {
        Tree<T> child = new Tree<T>(data);
        child.setParent(this);
        this.children.add(child);
    }

    public void addChild(Tree<T> child) {
        child.setParent(this);
        this.children.add(child);
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isRoot() {
        return (this.parent == null);
    }

    public boolean isLeaf() {
        if(this.children.size() == 0)
            return true;
        else
            return false;
    }

    /**
     * Looks for a given data in every child.
     * @param data Requested data.
     * @return Returns the found child. If doesn't exist a vertex with given data the returns null.
     */
    public Tree findInChildren(T data) {
        if (this.getData().equals(data))
            return this;
        Tree foundChild = null;
        for (Tree child:
             this.children) {
            if (child.data.equals(data)) {
                foundChild = child;
                break;
            }
        }
        if (foundChild == null)
            for (Tree child:
                    this.children) {
                foundChild = child.findInChildren(data);
                if (foundChild != null)
                    break;
            }
        return foundChild;
    }

    /**
     * Finds the root of the current vertex.
     * @return Returns the root.
     */
    public Tree getRoot() {
        Tree root = this;
        while(!root.isRoot()) {
            root = root.parent;
        }
        return root;
    }

    public void removeParent() {
        this.parent = null;
    }

    /**
     * The method walks through the tree in pre order.
     * @return Returns a list with vertexes in pre order.
     */
    public LinkedList getPreOrder() {
        LinkedList preOrder = new LinkedList();
        preOrder.add(this.data);
        for (Tree child:
             this.children) {
            preOrder.addAll(child.getPreOrder());

        }
        return preOrder;
    }
}
