import java.util.Arrays;

public class FaceAreaGetter {

    public static int[] getFaceArea(char[][] face){
        System.out.println();
        System.out.println("Analysing face data to generate coordinates of the face area.....");

        int[] coordinates=new int[4];
        int plusCounter=0;
        boolean stop=false;

        for(int i=0;i<21;i++){
            for (int j=0;j<14;j++){
                if(face[i][j]=='+'){
                    coordinates[0]=i;
                    coordinates[1]=j+1;
                    stop=true;
                    break;
                }
                if (stop){
                    break;
                }
            }
        }
        stop=false;

        for(int i=20;i>0;i--){
            for(int j=13;j>0;j--){
                if(face[i][j]=='+'){
                    coordinates[2]=i;
                    coordinates[3]=j-1;
                    stop=true;

                    break;
                }
                if (stop){
                    break;
                }

            }
        }
        System.out.println("Coordinates successfully generated!");
        System.out.println("Coordinates are: "+ Arrays.toString(coordinates));

        return coordinates;
    }
}
