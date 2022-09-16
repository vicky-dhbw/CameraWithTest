import java.util.Arrays;
public class Picture {

    private int faceID;

    private char[][] content= new char[10][10];

    private long nanoTimeStamp;

    public void setFaceID(int faceID) {
        this.faceID = faceID;
    }

    public void setContent(char[][] content) {
        this.content = content;
    }

    public int getFaceID(){
        return faceID;
    }

    public char[][] getContent(){
        return content;
    }
}
