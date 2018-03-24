package com.threedeeapp.threedeemodel.domain;

import java.util.Random;

// A Math class that can be used by other classes for mathematical computations
public class MyMath {

    private MyMath(){	//Private constructor so that it cannot be instantiated
    }

    //returns a random integer between 'min' and 'max'
    public static int rangeRandVal(int min, int max){
        int initialValue = min + (int)(Math.random() * (max-min));
        return initialValue;
    }

    // returns random float between 'min' and 'max'
    public static float rangeRandVal(float min, float max){	//method overloading
        float initialValue = min + (float)(Math.random() * (max-min));
        return initialValue;
    }

    //returns a random integer from 0 to 'limit'
    public static int randomNum(int limit){
        Random ran = new Random();
        int num = ran.nextInt();
        return  num % limit;
    }

    //returns a random float from 0 to 'limit'
    public static float randomNum(float limit){
        Random ran = new Random();
        float num = ran.nextFloat();
        return  num % limit;
    }

    //returns the cross product of vectors vec1 and vec2
    public static double[] vectorCrossProd(double[]vecA, double []vecB){
        double [] crossProd = new double[3];
        crossProd[0] = (vecA[1]*vecB[2])-(vecB[1]*vecA[2]);
        crossProd[1] = ((vecA[2]*vecB[0])-(vecB[2]*vecA[0]));
        crossProd[2] = (vecA[0]*vecB[1])-(vecB[0]*vecA[1]);
        return crossProd;
    }

    //returns vecToNorm as a normalised vector
    //(Code Taken from Shilane et al (2004) Princeton Shape Benchmark util program: OFFVIEWER)
    public static double[]normaliseVector(double[] vecToNorm){
        double vectLengthSqrd = 0;
        vectLengthSqrd += vecToNorm[0]*vecToNorm[0];
        vectLengthSqrd += vecToNorm[1]*vecToNorm[1];
        vectLengthSqrd += vecToNorm[2]*vecToNorm[2];
        float vectLength = (float) Math.sqrt(vectLengthSqrd);
        if (vectLength > 1.0E-6) {
            vecToNorm[0] /= vectLength;
            vecToNorm[1] /= vectLength;
            vecToNorm[2] /= vectLength;
        }
        return vecToNorm;
    }
}

