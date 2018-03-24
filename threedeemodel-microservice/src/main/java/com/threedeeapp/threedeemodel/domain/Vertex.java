package com.threedeeapp.threedeemodel.domain;

public class Vertex {
    // double array to store coords x,y,z
    private double coords [] = new double [3];

    public Vertex(double xCoor, double yCoor, double zCoor){
        coords[0] = xCoor;
        coords[1] = yCoor;
        coords[2] = zCoor;
    }

    public double [] getCoords (){
        return coords;
    }

    //set coords by array
    public void setCoords(double[]coords){
        this.coords = coords;
    }
    //set coords by x,y,z values
    public void setCoords(double xCoor, double yCoor, double zCoor){
        coords[0] = xCoor;
        coords[1] = yCoor;
        coords[2] = zCoor;
    }

    //print vertex coords.
    public void printVertexCoor(){
        System.out.print(coords[0] + ": " + coords[1] + ": " + coords[2] + "\t\t");
    }
}
