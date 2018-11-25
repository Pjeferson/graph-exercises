import java.util.Vector;

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
 Graph s;
 int n,h,w, size;
 Vertex vertices[][];
 Vector<Vertex> outs;
 Vertex start;
 int[] path;
 Vector<Vertex> openSet;
 Vector<Vertex> closedSet;
 
 void setup(){
   size(640,480);
   n=1;
   size=20;
   
   h=maze.length;
   w=maze[0].length();
   
   /*
   h=height/size;
   w=width/size;
   */
   s = new Graph();
   vertices = new Vertex[h][w];
   outs = new  Vector();
   for (int i = 0; i < h; i++) {
       for (int j = 0; j < w; j++) {
          Vertex v = new Vertex(n++,maze[i].charAt(j), i,j,0,0,0);
          
          /*Vertex v = new Vertex(n++,random(10)>7?'1':'0', i,j,0,0,0);
          if(v.getKey() ==1) v.setC('2');
          if(outs.size() < 1 && v.getKey() > h*w-1) v.setC('3');
          */
          s.insertVertex(v);
          vertices[i][j] = v;
          start = v.getC() == '2'?v:start;
          if(v.getC()=='3') outs.add(v);
       }
   }
   for (int i = 0; i < h; i++) {
       for (int j = 0; j < w; j++) {
          if(i-1 > -1){
              s.insertEdge(vertices[i][j], vertices[i-1][j]);
          }
          if(i+1 < h){
              s.insertEdge(vertices[i][j], vertices[i+1][j]);
          }
          if(j-1 > -1){
              s.insertEdge(vertices[i][j], vertices[i][j-1]);
          }
          if(j+1 < w){
              s.insertEdge(vertices[i][j], vertices[i][j+1]);
          }
          
          //Horizontal
          /*if(i-1 > -1 && j-1 > -1){
              s.insertEdge(vertices[i][j], vertices[i-1][j-1]);
          }
          if(i-1 > -1 && j+1 <w){
              s.insertEdge(vertices[i][j], vertices[i-1][j+1]);
          }
          if(i+1 < h && j+1 < w){
              s.insertEdge(vertices[i][j], vertices[i+1][j+1]);
          }
          if(i+1 < h && j-1 < -1){
              s.insertEdge(vertices[i][j], vertices[i+1][j-1]);
          }*/
       }
   }
   path = new int[n];
   openSet = new Vector<Vertex>();
   closedSet = new Vector<Vertex>();
   openSet.add(start);
 }
 
void draw(){
 Vector<Vertex> verticesDraw = s.vertices();
 for (Vertex vertex : verticesDraw) {
     if(vertex.getC()=='1') fill(0,0,0);
     else if(vertex.getC()=='3') fill(0,0,255);
     else if(vertex.getC()=='2') fill(255,255,0);
     else fill(255,255,255);
     rect(vertex.getY()*size,vertex.getX()*size,size,size); 
 }
 for (Vertex vertex : closedSet) {
     fill(255,0,0);
     rect(vertex.getY()*size,vertex.getX()*size,size,size);
 } 
 for (Vertex vertex : openSet) {
     fill(0,255,0);
     rect(vertex.getY()*size,vertex.getX()*size,size,size);
 } 
 if(openSet.size()> 0) {
     Vertex current = openSet.get(0);
     for (Vertex vertex : openSet) {
         if(vertex.getF() < current.getF())
             current = vertex;
     }
     
    if(current.getC() == '3'){
        System.out.println("Finish");
        int now = current.getKey();
        Vector<Vertex> vv = s.vertices();
        while(now != start.getKey()){
            System.out.print(now+" ");
            now = path[now];
            vv.get(now-1).setC('*');
            fill(0,255,255);
            rect(vv.get(now-1).getY()*size,vv.get(now-1).getX()*size,size,size);
        }
        vv.get(now-1).setC('2');
        System.out.print("\n");
         for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
        
               Vertex v = vertices[i][j];
               System.out.print(v.getC());
            }
            System.out.print('\n');
         }
        noLoop();
    }
    
    openSet.remove(current);
    closedSet.add(current);
    
    Vector<Vertex> neighbors = s.neighbors(current);
    for (Vertex neighbor : neighbors) {
        if(!closedSet.contains(neighbor) && neighbor.getC() != '1'){
            int tempG = current.getG()+1;
            
            boolean newPath = false;
            if(openSet.contains(neighbor)){
                if (tempG < neighbor.getG()) {
                    neighbor.setG(tempG);
                    newPath = true;
                }
            } else {
                neighbor.setG(tempG);
                openSet.add(neighbor);
                newPath = true;
            }
            if(newPath){
              neighbor.setH(heuristc(neighbor, outs));
              neighbor.setF(neighbor.getG()+neighbor.getH());
              path[neighbor.getKey()] = current.getKey();
            }
        }
    }
 } else {
 // no solution
 }  
}
int heuristc (Vertex neighbor,Vector<Vertex> outs) {
   int minDist = Integer.MAX_VALUE;
   for (Vertex vertex : outs) {
     // Manhattan
     int dist = Math.abs(neighbor.getX() - vertex.getX()) + Math.abs(neighbor.getY() - vertex.getY());
     // Euclidian
     /*float xdif = Math.abs(neighbor.getX() - vertex.getX());
     float ydif = Math.abs(neighbor.getY() - vertex.getY());
     int dist = (int) Math.sqrt((xdif)*(xdif) +(ydif)*(ydif));*/
      if(dist < minDist){
          minDist = dist;
      }
   }
   return minDist;
 }
 
