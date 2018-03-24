package com.threedeeapp.threedeemodel.parser;

import com.threedeeapp.threedeemodel.domain.Face;
import com.threedeeapp.threedeemodel.domain.Mesh;
import com.threedeeapp.threedeemodel.domain.Vertex;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class OffFileParser {

    //private constructor so it cannot be instantiated
    private OffFileParser(){
    }

    //BUILDS AND RETURNS A MESH FROM THE .OFF FILE
    public static Mesh buildMesh(MultipartFile f){

        Mesh mesh = new Mesh();
        int totVerts = 0;
        int totFaces = 0;
        Vertex verts[] = null;
        Face faces[] = null;
        mesh.setFileName(f.getName());

        int lineCount = 0;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(f.getInputStream()));
            String line = in.readLine();

            while (line != null) {
                line.trim(); //REMOVES WHITE SPACES FROM BOTH ENDS
                line.toLowerCase();

                //SKIP COMMENTS AND BLANK LINES
                if (!line.startsWith("#") && line.length()!=0){

                    //READ THE TOTAL No.OF VERTICES AND FACES
                    if (totVerts == 0 && !line.startsWith("OFF")){
                        String [] lineSplit = line.split(" ");
                        totVerts=Integer.parseInt(lineSplit[0]);
                        totFaces=Integer.parseInt(lineSplit[1]);
                        verts = new Vertex[totVerts];
                        faces = new Face[totFaces];
                    }

    				/*READ EACH VERTEX LINE BY LINE AND STORE IT IN A VERTEX OBJECT
    				IN THE LOCAL VERTS[] ARRAY*/
                    else if (lineCount < totVerts){
                        String [] lineSplit = line.split(" ");
                        double xCoor = Double.valueOf(lineSplit[0]);
                        double yCoor = Double.valueOf(lineSplit[1]);
                        double zCoor = Double.valueOf(lineSplit[2]);
                        verts[lineCount]= new Vertex(xCoor,yCoor,zCoor);
                        lineCount++;
                    }

    				/* LINE BY LINE FOR EACH FACE READ EACH VERTEX INDEX POSITION in 'verts[]'. STORE THE
    			 	INDEX Position in the VertIndices[] array of the corresponding face*/
                    else if (lineCount-totVerts<totFaces){
                        String [] lineSplit = line.split(" ");
                        //TOTAL No. OF VERTICES (always 3 since meshes are triangulated)
                        int totFaceVert = Integer.parseInt(lineSplit[0]);
                        Face face = new Face();
                        for(int i=0; i < totFaceVert; i++){
                            face.setVertIndices(i, Integer.parseInt(lineSplit[i+1]));
                        }
                        faces[lineCount-totVerts]=face;
                        lineCount++;
                    }
                }
                line = in.readLine();
            }

            mesh.setVertices(verts);//assigns the vertices array to the mesh
            mesh.setFaces(faces);//assigns the faces array to the mesh
            mesh.setVerticesMean();
            mesh.setFacesDetails();//Calculates area and centroid for each face
            mesh.setTotalFaces(totFaces);
            mesh.setTotalVertices(totVerts);
            in.close();
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        return mesh; //RETURN THE BUILT MESH
    }

}
