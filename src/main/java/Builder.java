import java.util.Arrays;

public class Builder {
    static Camera camera=new Camera();
    static char[][] faceData;
    static int[] coordinates;

    static char[][] pictureField;

    static int numOfPictures=25;

    static Picture picture;

    public static void main(String[] args) {

        camera.on();

        for (int i = 0; i <numOfPictures ; i++) {
            faceData= camera.getRawFacePicture(i+1);
            coordinates=camera.getFaceArea(faceData);

            picture=camera.extractField(i+1,faceData,coordinates);
            pictureField=picture.getContent();

            System.out.println("____________");
        }

        camera.off();


    }


}
