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
        return RawFacePictureGetter.getRawFace(faceID);
    }

    @Override
    public int[] getFaceArea(char[][] face) {
        return FaceAreaGetter.getFaceArea(face);
    }

    @Override
    public Picture extractField(int id, char[][] face, int[] area) {
        return FaceFieldExtractor.extractField(id,face,area);
    }

    @Override
    public void off() {
        this.isOn=false;
    }

    public IRLed[] getIrLedArray(){
        return irLedArray;
    }
    public Chip[] getChips(){
        return chips;
    }

}
