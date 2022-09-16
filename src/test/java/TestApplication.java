import org.junit.jupiter.api.*;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Stack;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestApplication {
    private Camera camera;

    @BeforeEach
    public void setup() {
        camera = new Camera();
    }

    @Test
    @Order(1)
    public void testAssertNotNull() {
        assertNotNull(camera);
    }


    @Test
    @Order(2)
    public void isOn() {
        camera.setOn(true);
        assertTrue(camera.isOn());

        camera.setOn(false);
        assertFalse(camera.isOn());
    }

    @Test
    @Order(3)
    public void testCoordinatesFor1(){
        char [][] faceData;
        int[] coordinates;

        int[]expectedCoordinates = new int[]{ 2,2,11,11 };

        faceData= camera.getRawFacePicture(1);
        coordinates=camera.getFaceArea(faceData);
        assertEquals(expectedCoordinates[0],coordinates[0]);
        assertEquals(expectedCoordinates[1],coordinates[1]);
        assertEquals(expectedCoordinates[2],coordinates[2]);
        assertEquals(expectedCoordinates[3],coordinates[3]);

    }
    @Test
    @Order(4)
    public void testIfMemoryCardWorks(){
        char [][] faceData;
        int[] coordinates;

        int[]expectedCoordinates = new int[]{ 2,2,11,11 };

        faceData= camera.getRawFacePicture(25);
        coordinates=camera.getFaceArea(faceData);
        assertEquals(expectedCoordinates[0],coordinates[0]);
        assertEquals(expectedCoordinates[1],coordinates[1]);
        assertEquals(expectedCoordinates[2],coordinates[2]);
        assertEquals(expectedCoordinates[3],coordinates[3]);

        Picture picture=camera.extractField(25,faceData,coordinates);
        MemoryCard memoryCard = camera.getMemoryCard();
        assertFalse(memoryCard.getStore().isEmpty());

        int coordinateCounter=0;


        picture=memoryCard.getStore().peek();

        char[][] pictureContent=picture.getContent();

       for(int i=0; i<10;i++){
            for(int j=0; j<10;j++){
                for(int k=97; k<=122;k++){
                    char ch=(char)k;
                    if(ch==pictureContent[i][j]){
                        coordinateCounter+=1;
                    }

                }

            }
        }

        assertEquals(coordinateCounter,100);

    }

    @Test
    @Order(5)
    public void checkArrayIsNotEmpty(){
        for(IRLed irLed:camera.getIrLedArray()){
            assertNotNull(irLed);
        }
    }

}