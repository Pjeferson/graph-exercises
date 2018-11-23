/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graphexercises.graph;

import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author pjeferson
 */
public class Graph {
    private int vertexCount;
    private Vector vertices;
    private Edge adjMatrix[][];
    
    public Graph(){
        this.vertexCount=0;
        this.vertices= new Vector();
        this.adjMatrix = new Edge[0][0];
    }
    
    public void insertVertex(Vertex vertex){
        vertexCount++;
        vertices.add(vertex);
        Edge tempAdjMatrix[][]=new Edge[vertexCount][vertexCount];
        for(int f=0;f<vertexCount-1;f++){ //{copying}
            for(int g=0;g<vertexCount-1;g++){
                  tempAdjMatrix[f][g]= adjMatrix[f][g];              
            }
        }
        for(int g = 0;g <vertexCount-1;g++){// {append a vertex}
            tempAdjMatrix[vertexCount-1][g]= tempAdjMatrix[g][vertexCount-1] = null;          
        }
        adjMatrix=tempAdjMatrix;
    }
        
    public void removeVertex(Vertex vertex){        
        vertexCount--;
        int index=findIndex(vertex.getKey());
        vertices.remove(index);  // removing vertex
        // removing lines/coluns representing the vertex
        Edge tempAdjMatrix[][]=new Edge[vertexCount][vertexCount];
        int ff=0,gg;
        for(int f=0;f<vertexCount+1;f++){ //{copying without the vertex}
            gg=0;
            for(int g=0;g<vertexCount+1;g++){
                if(f!=index && g!=index){
                  tempAdjMatrix[ff][gg]= adjMatrix[f][g];                  
                  if(g!=index)
                      gg++;                  
                }                
            }
            if(f!=index)
                ff++;
        }
        adjMatrix=tempAdjMatrix;
    }
    
    public Edge insertEdge(Vertex startVertex, Vertex endVertex, double value){
        Edge A=new Edge(startVertex, endVertex, value);      
        int index1=findIndex(startVertex.getKey());
        int index2=findIndex(endVertex.getKey());
        
        adjMatrix[index1][index2] = adjMatrix[index2][index1] = A; // no-oriented
        return A;
    }

    public Edge insertEdge(Vertex startVertex, Vertex endVertex){		
        Edge A=new Edge(startVertex, endVertex);      
        int index1=findIndex(startVertex.getKey());
        int index2=findIndex(endVertex.getKey());
        
        adjMatrix[index1][index2] = adjMatrix[index2][index1] = A; // no-oriented
        return A;
    }   
    
    public void removeEdge(Edge edge){        
        int index1=findIndex(edge.getStartVertex().getKey());
        int index2=findIndex(edge.getEndVertex().getKey());
        adjMatrix[index1][index2]=adjMatrix[index2][index1]=null; // no-oriented
    }
    
    public Edge insertArc(Vertex startVertex, Vertex endVertex,double value){
        Edge A=new Edge(startVertex, endVertex, value);      
        int index1=findIndex(startVertex.getKey());
        int index2=findIndex(endVertex.getKey());
        adjMatrix[index1][index2] = A; // oriented
        return A;
    }

    public Edge insertArc(Vertex startVertex, Vertex endVertex){
        Edge A=new Edge(startVertex, endVertex);      
        int index1=findIndex(startVertex.getKey());
        int index2=findIndex(endVertex.getKey());
        adjMatrix[index1][index2] = A; // oriented
        return A;
    }
    
    public void removeArc(Edge edge){    
        int index1=findIndex(edge.getStartVertex().getKey());
        int index2=findIndex(edge.getEndVertex().getKey());
        adjMatrix[index1][index2] = null; // oriented 
    }
    
    public void printVertices(){
        for(int f=0;f<vertices.size();f++)
            System.out.print(vertices.elementAt(f)+",");        
    }
    
    public void printMatrix(){
        for(int f=0;f<vertexCount;f++){
            for(int g=0;g<vertexCount;g++){
                Edge e = adjMatrix[f][g];
                if(e!= null)
                    System.out.print(adjMatrix[f][g].getStartVertex().getKey()+"-"+adjMatrix[f][g].getEndVertex().getKey()+" ");
                else
                    System.out.print("null ");
            }
            System.out.println();
        }        
    }
    
    public int degree(Vertex vertex){
        int degree = 0;
        int index = findIndex(vertex.getKey());
        for (int i = 0; i < vertexCount; i++) {
            if(adjMatrix[i][index] != null && adjMatrix[i][index].isDirected()){
                degree++;
            }
            if(adjMatrix[index][i] != null && adjMatrix[index][i].isDirected()){
                degree++;
            } else if(adjMatrix[index][i] != null){
                degree++;
            }
        }
        return degree;
    }
    
    public int order(){
        return vertexCount;
    }
    
    private int findIndex(int key){
        Iterator i=vertices.iterator();
        int index=0;        
        while(i.hasNext()){     
            Vertex v=(Vertex)(i.next());            
            if(v.getKey()==key)// finded
                return index;
            index++;
        }
        return -1; // not finded
    }
    
    public Vector<Vertex> vertices(){
        return vertices;
    }

    public Vector<Edge> Edges(){
        Vector<Edge> v=new Vector();
        for(int l=0;l<vertexCount;l++)
            for(int c=0;c<vertexCount;c++)                
                v.add(adjMatrix[l][c]);
        return v;
    }
    
    public Vector<Edge> incidentsEdges(Vertex vertex){
        Vector<Edge> edges = new Vector();
        int index = findIndex(vertex.getKey());
        for (int i = 0; i < vertexCount; i++) {
            if(adjMatrix[i][index] != null && adjMatrix[i][index].isDirected()){
                edges.add(adjMatrix[i][index]);
            }
            if(adjMatrix[index][i] != null && adjMatrix[index][i].isDirected()){
                edges.add(adjMatrix[index][i]);
            } else if(adjMatrix[index][i] != null){
                edges.add(adjMatrix[index][i]);
            }
        }
        return edges;
    }
    
    public Vector<Vertex> finalVertices(Edge edge){
        Vector<Vertex> v=new Vector();
        v.add(edge.getStartVertex());
        v.add(edge.getEndVertex());
        return v;
    }
    
    public Vector<Vertex> neighbors(Vertex vertex){
        Vector<Vertex> v=new Vector();
        int index = findIndex(vertex.getKey());
        for (int i = 0; i < vertexCount; i++) {
            if(adjMatrix[index][i] != null){
                v.add((Vertex)vertices.get(i));
            }
        }
        return v;
    }
    
    /*
    public Vertex oposto(Vertex v,Edge e) throws OpostoError {
     // método exercício, fique a vontade para implementa-lo coleguinha   
    }
    */
    
    public boolean isAdjacent(Vertex vertex1, Vertex vertex2){
        int index1=findIndex(vertex1.getKey());
        int index2=findIndex(vertex2.getKey());
        
        return (adjMatrix[index1][index2]) != null;
    }
    
    public Edge getEdge(Vertex vertex1, Vertex vertex2){
        int index1=findIndex(vertex1.getKey());
        int index2=findIndex(vertex2.getKey());
        
        return (adjMatrix[index1][index2]);        
    }

}