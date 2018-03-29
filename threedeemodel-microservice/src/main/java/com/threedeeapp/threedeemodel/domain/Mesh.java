package com.threedeeapp.threedeemodel.domain;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import Jama.Matrix;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jogamp.opengl.GL2;
import com.threedeeapp.threedeemodel.utilities.MyMath;
import org.springframework.data.annotation.Id;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Mesh {

    @Id
    private String fileName;

    private int totalFaces; //total no. of faces

    private int totalVertices;

    private Face faces[]; //Array to store all faces

    private Vertex vertices[]; //Array to store all vertices

    private double[] verticesMean; // mean of all mesh vertices

    private double surfaceArea; // Total Surface area of mesh (area of all triangles}

    private double[] massCenter; // The center of mass of the mesh

    public Mesh(){
    }

    public Mesh(int totalFaces, int totalVertices){
        this.totalFaces = totalFaces;
        faces = new Face[totalFaces];
        vertices = new Vertex[totalVertices];
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setTotalFaces(int numFaces){
        totalFaces = numFaces;
    }

    public int getTotalVertices() {
        return totalVertices;
    }

    public void setTotalVertices(int totalVertices) {
        this.totalVertices = totalVertices;
    }

    public int getTotalFaces(){
        return totalFaces;
    }

    public void setFace(int index, Face face){
        faces[index]= face;
    }

    public Face getFace(int index){
        return faces[index];
    }

    public void setVertices(Vertex [] vertices){
        this.vertices = vertices;
    }

    public Vertex [] getVertices(){
        return vertices;
    }

    public void setFaces( Face [] faces){
        this.faces=faces;
    }

    public Face[] getFaces(){
        return faces;
    }

    public void setSurfaceArea(double sArea){
        surfaceArea=sArea;
    }

    public double getSurfaceArea(){
        return surfaceArea;
    }

    public void setMassCenter(double[] mCenter){
        massCenter= new double[]{mCenter[0],mCenter[1],mCenter[2]};
    }

    public double []getMassCenter(){
        return massCenter;
    }

    public double [] getVerticesMean(){
        return verticesMean;
    }


    //Calculates the mean x y and z values of all vertices in vertices[]
    public void setVerticesMean(){
        verticesMean = new double[]{0,0,0};
        for(Vertex vertex: vertices){
            double coords [] = vertex.getCoords();
            verticesMean[0] += coords[0];
            verticesMean[1] += coords[1];
            verticesMean[2] += coords[2];
        }

        verticesMean[0] = verticesMean[0] / vertices.length;
        verticesMean[1] = verticesMean[1] / vertices.length;
        verticesMean[2] = verticesMean[2] / vertices.length;
    }

    //sets the mean x y and z values to predefined values
    public void setVerticesMean(double[] mean){
        verticesMean = new double[]{mean[0], mean[1], mean[2]};
    }

    //Calculates and Sets the area, centroid and normal of each face
    public void setFacesDetails(){
        surfaceArea=0;
        massCenter = new double[]{0,0,0};
        for(Face face:faces){ //for each face in faces
            int [] vertIndices = face.getVertIndices();
            Vertex [] vertices = new Vertex[vertIndices.length];
            for(int i =0; i<vertIndices.length; i++ ){ //for each vertex in this face
                vertices[i] = this.vertices[vertIndices[i]]; //get coords
            }
            double [] vertA = vertices[0].getCoords();
            double [] vertB = vertices[1].getCoords();
            double [] vertC = vertices[2].getCoords();

            //set face normal
            face.setNormal(vertA,vertB ,vertC);

            //set face area
            face.setArea(vertA,vertB ,vertC);
            double faceArea =face.getArea();
            surfaceArea+=faceArea;//add face area to mesh surface area

            //set Face centroid
            face.setCentroid(vertA,vertB ,vertC);
            double[]faceCentroid=face.getCentroid();
            for(int i =0; i<faceCentroid.length; i++ )//set mesh Mass center
                massCenter[i]+= (faceArea*faceCentroid[i]);
        }
        for(int i =0; i< massCenter.length; i++ )//normalize mesh Mass center
            massCenter[i]=massCenter[i]/surfaceArea;
    }

    //Modifies the mesh pose by multiplying each vertex with the 'newPoseMatrix'
    public void modifyPose(Matrix newPoseMatrix){
        //for each vertex in mesh
        for(Vertex vertex: vertices){
            //represent vertex as a 1 x 'matrixDim' matrix
            double coords [] = vertex.getCoords();
            int matrixDim = newPoseMatrix.getColumnDimension();
            double coordinates[]= new double [matrixDim];
            for(int i = 0; i < matrixDim;  i++){
                if (i<coords.length)
                    coordinates[i]= coords[i];
                else
                    coordinates[i]=1;
            }
            Matrix vert = new Matrix(coordinates,matrixDim);

            //multiply Vertex by the 'newPoseMatrix'
            double newVertex [] = (newPoseMatrix).times(vert).getRowPackedCopy();
            //point to new vertex.
            vertex.setCoords(newVertex);
        }
    }

    // A method that creates a copy of this mesh, randomizes its pose and returns it.
    public Mesh randomisePose(){
        //creates a copy of this Mesh
        Mesh meshOut= this.createMeshCopy();

        /*Generates random scaling,translating and rotation matrices. Then multiplies
         * all the generated matrices to build a new Matrix randPose*/
        double scalFact= MyMath.rangeRandVal(0.0f, 3.0f);
        double xRot = MyMath.rangeRandVal((float)Math.PI * -1, (float)Math.PI);
        double yRot = MyMath.rangeRandVal((float)Math.PI * -1, (float)Math.PI);
        double zRot = MyMath.rangeRandVal((float)Math.PI * -1, (float)Math.PI);
        double[][] scalMat = {{scalFact,0,0,0},{0,scalFact,0,0},{0,0,scalFact,0},{0,0,0,1}};
        Matrix scale = new Matrix (scalMat);
        double[][] transMat = {{1,0,0,MyMath.rangeRandVal(-2, 2)},{0,1,0,MyMath.rangeRandVal(-2, 2)},
                {0,0,1,MyMath.rangeRandVal(-2, 2)},{0,0,0,1}};
        Matrix translate = new Matrix (transMat);
        double[][] xRotMat = {{1,0,0,0},{0,Math.cos(xRot),Math.sin(xRot)*-1,0},
                {0,Math.sin(xRot),Math.cos(xRot),0},{0,0,0,1}};
        Matrix xRotate = new Matrix(xRotMat);
        double[][] yRotMat = {{Math.cos(yRot),0,Math.sin(yRot),0},{0,1,0,0},
                {Math.sin(yRot)*-1,0,Math.cos(yRot),0},{0,0,0,1}};
        Matrix yRotate = new Matrix(yRotMat);
        double[][] zRotMat = {{Math.cos(zRot),Math.sin(zRot)*-1,0,0},{Math.sin(zRot),Math.cos(zRot),0,0},
                {0,0,1,0},{0,0,0,1}};
        Matrix zRotate = new Matrix(zRotMat);
        Matrix randPose = scale.times(translate).times(xRotate).times(yRotate).times(zRotate);

        //Modify the pose of the mesh copy by the randPose Matrix
        meshOut.modifyPose(randPose);

        //output the pose randomised mesh copy
        return meshOut;

    }

    /*Making another copy of the input mesh on which modifications
     * can be done. The clone() method was avoided. */
    public Mesh createMeshCopy(){
        Mesh meshOut = new Mesh(getFaces().length, getVertices().length);
        meshOut.setTotalFaces(getTotalFaces());
        meshOut.setVerticesMean(getVerticesMean());
        meshOut.setSurfaceArea(getSurfaceArea());
        meshOut.setMassCenter(getMassCenter());
        meshOut.setFileName("temp_" + getFileName() );
       // meshOut.setMeshFile(new File(getMeshFile().getPath()));

        Vertex[]vertsOut = meshOut.getVertices();
        Face [] facesOut=meshOut.getFaces();

        for(int i = 0; i< vertices.length; i++){
            double vertCoords [] = vertices[i].getCoords();
            vertsOut[i]= new Vertex(vertCoords[0],vertCoords[1],vertCoords[2]);
        }
        for(int i=0; i<faces.length;i++){
            int faceIndIn[] = faces[i].getVertIndices();
            facesOut[i] = new Face(faceIndIn[0],faceIndIn[1],faceIndIn[2]);
            facesOut[i].setNormal(faces[i].getNormal());
            facesOut[i].setArea(faces[i].getArea());
            facesOut[i].setCentroid(faces[i].getCentroid());
        }
        return meshOut;
    }

    //Prints the mesh(vertices and faces) as an OFF file to file:'outFile'
    public void printMeshToFile(File file){
        try {

            StringBuffer destFile=new StringBuffer(file.toString());
            if(!file.toString().toLowerCase().endsWith("off"))
                destFile.append("/" + getFileName());
            BufferedWriter out = new BufferedWriter(new FileWriter(new File(destFile.toString())));
            out.write("OFF \n");//print 'OFF'
            //print Total vertices, total faces and total edges (0 since not used)
            out.write(vertices.length + " " + faces.length + " " + 0 + "\n");
            //print each mesh vertex
            for (Vertex vertex : vertices){
                double [] coords = vertex.getCoords();
                out.write(coords[0] + " " + coords[1] + " " + coords[2] + "\n");
            }
            //Print each mesh face
            for (Face face : faces){
                int [] vertIndices = face.getVertIndices();
                out.write(Integer.toString(face.getNverts()));
                for (int vertIndex : vertIndices){
                    out.write(" " + vertIndex);
                }
                out.write("\n");
            }
            out.flush();
            out.close();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    //Defines the mesh faces to be drawn to canvas
    public void drawMesh(GL2 gl){
        for(Face f : faces){ //for each face in this mesh
            gl.glBegin(GL2.GL_POLYGON); //signals start of polygon definition
            gl.glNormal3d(f.getNormal()[0],f.getNormal()[1],
                    f.getNormal()[2]); //get face normal
            int [] vertIndices = f.getVertIndices();
            for(int index : vertIndices ){ //for each vertex in this face
                double[] v = vertices[index].getCoords(); //get coords
                gl.glVertex3f((float)v[0], (float) v[1], (float)v[2]); //define coords
            }
            gl.glEnd();//signals end of polygon definition
        }
    }
}
