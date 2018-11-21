/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphexercises;

import graphexercises.graph.Graph;
import graphexercises.graph.Vertex;

/**
 *
 * @author pjeferson
 */
public class GraphExercises {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Graph s = new Graph();
        Vertex v1 = new Vertex(1, 10);
        Vertex v2 = new Vertex(2, 20);
        Vertex v3 = new Vertex(3, 30);
        s.insertVertex(v1);
        s.insertVertex(v2);
        s.insertVertex(v3);
        s.insertEdge(v1, v2);
        s.insertEdge(v1, v3);
        s.printMatrix();
    }
    
}
