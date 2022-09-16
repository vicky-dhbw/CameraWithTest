import java.io.*;
import java.util.Arrays;
import java.util.stream.Stream;

public class Camera implements ICamera{
    private boolean isOn;

    private final IRLed[] irLedArray=Stream.generate(IRLed::new).limit(Configuration.INSTANCE.numberOfIRLEDs).toArray(IRLed[]::new);
    //generate takes a supplier as parameter, the supplier take no parameter and returns something that is wished
    private final Chip[] chips=Stream.generate(Chip2Core::new).limit(Configuration.INSTANCE.numberOfChips).toArray(Chip[]::new);
    private final IController voiceController =new VoiceController();
    private final MemoryCard memoryCard=new MemoryCard();
    private final Cloud cloudStorage=new Cloud();


    public Camera() {
        isOn = false;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public MemoryCard getMemoryCard(){
        return memoryCard;
    }

    @Override
    public void on() {
        this.isOn=true;

    }

    @Override
    public char[][] getRawFacePicture(int faceID) {

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

    @Override
    public int[] getFaceArea(char[][] face) {

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

    @Override
    public  Picture extractField(int id, char[][] face, int[] area) {

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

        memoryCard.getStore().push(picture);
        System.out.println("Picture with id: " + id+ " saved in memory card!");
        return picture;
    }

    @Override
    public void off() {
        this.isOn=false;
    }

    public IRLed[] getIrLedArray(){
        return irLedArray;
    }

}
