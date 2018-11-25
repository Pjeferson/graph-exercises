class Vertex {
    private int key;
    private double value;
    //Maze solving
    private char c;
    private int x;
    private int y;
    private int h;
    private int g;
    private int f;
    //

    public Vertex(int key, double value) {
        this.key = key;
        this.value = value;
    }

    public Vertex(int key, char c, int x,int y, int h, int g, int f) {
        this.key = key;
        this.c = c;
        this.x = x;
        this.y = y;
        this.h = h;
        this.g = g;
        this.f = f;
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
   
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }
    
    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }
}
