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
         Vertex end = null;
         for (int i = 0; i < maze.length; i++) {
             for (int j = 0; j < maze[i].length(); j++) {
                //System.out.print(maze[i].charAt(j));
                
                Vertex v = new Vertex(n++,maze[i].charAt(j));
                s.insertVertex(v);
                vertices[i][j] = v;
                start = maze[i].charAt(j)=='2'?v:start;
                end = maze[i].charAt(j)=='3'?v:end;
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
         int[] f= new int[n];
         int[] g= new int[n];
         int[] h= new int[n];

         for (int i = 0; i < n; i++) {
             h[i]=f[i]=g[i]=0;
         }
         Vector<Vertex> openSet = new Vector<>();
         Vector<Vertex> closedSet = new Vector<>();
         openSet.add(start);
         while(openSet.size()> 0) {
             Vertex current = openSet.get(0);
             for (Vertex vertex : openSet) {
                 if(f[vertex.getKey()] < f[current.getKey()])
                     current = vertex;
             }
             
            if(current == end){
                System.out.println("Finish");
                int now = end.getKey();
                Vector<Vertex> vv = s.vertices();
                while(now != start.getKey()){
                    System.out.print(now+" ");
                    now = path[now];
                    vv.get(now-1).setValue('8');
                }
                vv.get(now-1).setValue('2');
                System.out.print("\n");
                break;
            }
            
            openSet.remove(current);
            closedSet.add(current);
            
            Vector<Vertex> neighbors = s.neighbors(current);
            for (Vertex neighbor : neighbors) {
                if(!closedSet.contains(neighbor) && neighbor.getValue() != '1'){
                    int tempG = g[current.getKey()]+1;
                    
                    if(openSet.contains(neighbor)){
                        if (tempG < g[neighbor.getKey()]) {
                            g[neighbor.getKey()] = tempG;
                        }
                    } else {
                        g[neighbor.getKey()] = tempG;
                        openSet.add(neighbor);
                    }
                    
                    h[neighbor.getKey()] = heuristc(neighbor, end);
                    f[neighbor.getKey()] = g[neighbor.getKey()]+h[neighbor.getKey()];
                    path[neighbor.getKey()] = current.getKey();
                }
            }
         }
         
        for (int i = 0; i < maze.length; i++) {
             for (int j = 0; j < maze[i].length(); j++) {
                
                Vertex v = vertices[i][j];
                System.out.print((int)v.getValue()-'0');
             }
             System.out.print('\n');
         }
         //

         //s.printMatrix();
     }
     
     private static int heuristc (Vertex neighbor,Vertex end) {
         return end.getKey()-neighbor.getKey();
     }
}
