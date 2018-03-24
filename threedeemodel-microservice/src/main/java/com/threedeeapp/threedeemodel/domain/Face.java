package com.threedeeapp.threedeemodel.domain;

public class Face {

    private final int nverts =3;
    private int vertsIndices[] = new int [3];
    private double normal [] = new double [3];
    private double area = 0;
    private double centroid [] = new double[3];

    //constructors
    public Face( ){
        normal[0]= normal[1] = normal[2] = 0;
    }
    public Face(int a, int b, int c){
        vertsIndices[0] = a;
        vertsIndices[1] = b;
        vertsIndices[2] = c;
        normal[0] = normal[1] = normal[2]= 0;
    }

    //get total vertices for this face
    public int getNverts(){
        return nverts;
    }

    //defines the array index of vertex 'vertNo' in the Mesh's 'verts[]' array
    public void setVertIndices(int vertNo, int vertsInd){
        vertsIndices[vertNo] = vertsInd;
    }

    //returns the Mesh's 'verts[]' array indices of all vertices in this face
    public int[] getVertIndices(){
        return vertsIndices;
    }

    //Calculates and sets face area using cross product between two edges
    public void setArea(double[] vertA, double[] vertB ,double[] vertC){
        double edgeBtoA []= new double[]{vertB[0]-vertA[0],
                vertB[1]-vertA[1],vertB[2]-vertA[2]};
        double edgeCtoA []= new double[]{vertC[0]-vertA[0],
                vertC[1]-vertA[1],vertC[2]-vertA[2]};
        double[]crossProd = MyMath.vectorCrossProd(edgeBtoA,edgeCtoA);
        area=0.5*(Math.sqrt((crossProd[0]*crossProd[0])+
                (crossProd[1]*crossProd[1]) + (crossProd[2]*crossProd[2])));
    }

    //sets area to a predefined value
    public void setArea(double area){
        this.area = area;
    }

    //returns the face area
    public double getArea(){
        return area;
    }

    //calculates and sets face centroid
    public void setCentroid(double[] vertA, double[] vertB ,double[] vertC){
        centroid[0]=(vertA[0]+vertB[0]+vertC[0])/3;
        centroid[1]=(vertA[1]+vertB[1]+vertC[1])/3;
        centroid[2]=(vertA[2]+vertB[2]+vertC[2])/3;
    }

    public void setCentroid(double[] cent){
        centroid = new double[]{cent[0],cent[1],cent[2]};
    }

    //returns face centroid array
    public double [] getCentroid(){
        return centroid;
    }

    //Calculates and sets Face normal using cross product of two edges.
    public void setNormal(double[] vertA, double[] vertB ,double[] vertC){
        //find edge vectors
        double edgeBtoA []= new double[]{vertB[0]-vertA[0],
                vertB[1]-vertA[1],vertB[2]-vertA[2]};
        double edgeCtoA []= new double[]{vertC[0]-vertA[0],
                vertC[1]-vertA[1],vertC[2]-vertA[2]};
        //cross product of two edge vectors give face normal
        normal = MyMath.vectorCrossProd(edgeBtoA,edgeCtoA );
        normal = MyMath.normaliseVector(normal);
    }

    //set normal to  predefined x,y,z values
    public void setNormal(double []norm){
        normal = new double[]{norm[0], norm[1],norm[2]};
    }

    //returns the normals array
    public double [] getNormal(){
        return normal;
    }

}
