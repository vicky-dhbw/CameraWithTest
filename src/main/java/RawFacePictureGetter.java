import java.io.BufferedReader;
import java.io.FileReader;

public class RawFacePictureGetter {

    public static char[][] getRawFace(int faceID){
        System.out.println();
        System.out.println("reading raw face data..");
        char[][] faceData=new char[21][14];
        String filename;

        if(faceID<10){
            filename=Configuration.INSTANCE.fileNamePatternPrefix+0+faceID+Configuration.INSTANCE.fileNamePatternSuffix;
        }
        else {
            filename=Configuration.INSTANCE.fileNamePatternPrefix+faceID+Configuration.INSTANCE.fileNamePatternSuffix;
        }

        try{
            BufferedReader buffer=new BufferedReader(new FileReader(filename));

            String line;
            int counter=0;
            char ch;
            while((line=buffer.readLine())!=null){
                for(int i=0;i<line.length();i++){
                    ch=line.charAt(i);
                    faceData[counter][i]=ch;
                };
                counter++;

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return faceData;
    }

}
