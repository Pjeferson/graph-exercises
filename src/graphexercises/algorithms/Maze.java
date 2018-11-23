/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graphexercises.algorithms;

import graphexercises.graph.Edge;
import graphexercises.graph.Graph;
import graphexercises.graph.Vertex;
import java.util.Vector;

/**
 *
 * @author Usuario
 */
public class Maze {
     public static void main(String[] args) {
         String[] maze = {
            "1111111111" ,
            "1000000001" ,
            "1200010101" ,
            "1111010101" ,
            "1000000101" ,
            "1011111103" ,
            "1000000001" ,
            "1111111101" ,
            "3100100001" ,
            "1101001111" ,
            "1000000301" ,
            "1111111111"
         };
         Graph s = new Graph();
         Vertex vertices[][] = new Vertex[maze.length][maze[0].length()];
         int n = 1;
         Vertex start = null;
         for (int i = 0; i < maze.length; i++) {
             for (int j = 0; j < maze[i].length(); j++) {
                //System.out.print(maze[i].charAt(j));
                
                Vertex v = new Vertex(n++,maze[i].charAt(j));
                s.insertVertex(v);
                vertices[i][j] = v;
                start = maze[i].charAt(j)=='2'?v:start;
             }
             //System.out.print('\n');
         }
         for (int i = 0; i < maze.length; i++) {
             for (int j = 0; j < maze[i].length(); j++) {
                if(i-1 > -1){
                    s.insertEdge(vertices[i][j], vertices[i-1][j]);
                }
                if(i+1 < maze.length){
                    s.insertEdge(vertices[i][j], vertices[i+1][j]);
                }
                if(j-1 > -1){
                    s.insertEdge(vertices[i][j], vertices[i][j-1]);
                }
                if(j+1 < maze[i].length()){
                    s.insertEdge(vertices[i][j], vertices[i][j+1]);
                }
             }
         }
         int[] path= new int[n];
         Vector<Vertex> open = new Vector<>();
         Vector<Vertex> closed = new Vector<>();
         open.add(start);
         Vector<Edge> inci = s.incidentsEdges(start);
         Vector<Vertex> neighbors = s.neighbors(start);
         
         for (Vertex vertex : neighbors) {
             System.out.println(vertex.getKey());
         }

         //

         //s.printMatrix();
         System.out.println(start);
     }
}
