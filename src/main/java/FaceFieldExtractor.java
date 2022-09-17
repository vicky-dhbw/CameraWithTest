public class FaceFieldExtractor {

    public static Picture extractField(int id, char[][] face, int[] area){
        System.out.println();
        System.out.println("Extracting field from face data using coordinates..");

        char[][] pictureContent=new char[10][10];
        for (int i=0; i<21;i++){
            for (int j=0; j<14;j++){
                if((i>=area[0] && i<=area[2])&&(j>=area[1]&&j<=area[3])){
                    pictureContent[i-area[0]][j-area[1]]=face[i][j];
                }
            }
        }

        Picture picture=new Picture();
        picture.setFaceID(id);
        picture.setContent(pictureContent);

        System.out.println("Saving picture to memory card.....");
        System.out.println("Picture with id: " + id+ " saved in memory card!");
        return picture;
    }
}
