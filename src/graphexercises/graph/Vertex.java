/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graphexercises.graph;

/**
 *
 * @author pjeferson
 */
public class Vertex {
    private int key;
    private double value;

    public Vertex(int key, double value) {
        super();
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
   
}
