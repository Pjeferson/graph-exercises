class Edge {
    private Vertex startVertex;
    private Vertex endVertex;
    private double value;
    private boolean isDirected;
   
    public Edge(Vertex iniVertex, Vertex endVertex) {
        this.startVertex=iniVertex;
        this.endVertex=endVertex;
        this.isDirected=false;
    }
    public Edge(Vertex iniVertex, Vertex endVertex, double value) {
        this.startVertex=iniVertex;
        this.endVertex=endVertex;
        this.value = value;
        this.isDirected=false;
    }
 
    public Edge(Vertex iniVertex, Vertex endVertex, double value, boolean isDirected) {
        this.startVertex=iniVertex;
        this.endVertex=endVertex;
        this.value = value;
        this.isDirected=isDirected;
    }
  
    public Vertex getStartVertex() {
        return startVertex;
    }

    public void setStartVertex(Vertex iniVertex) {
        this.startVertex = iniVertex;
    }

    public Vertex getEndVertex() {
        return endVertex;
    }

    public void setEndVertex(Vertex endVertex) {
        this.endVertex = endVertex;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isDirected() {
        return isDirected;
    }

    public void setIsDirected(boolean isDirected) {
        this.isDirected = isDirected;
    }
    
}
