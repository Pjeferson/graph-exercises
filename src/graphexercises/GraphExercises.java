/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphexercises;

import graphexercises.graph.Edge;
import graphexercises.graph.Graph;
import graphexercises.graph.Vertex;
import java.util.Vector;

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
        Vertex v4 = new Vertex(4, 40);
        
        s.insertVertex(v1);
        s.insertVertex(v2);
        s.insertVertex(v3);
        s.insertVertex(v4);
        
        s.insertEdge(v1, v2);
        s.insertEdge(v1, v3);
        s.printMatrix();
        /*System.out.println(s.degree(v1));
        System.out.println(s.degree(v2));
        System.out.println(s.degree(v3));
        System.out.println(s.degree(v4));*/

    }
    
}