import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Encrypt {
    static SecretKey secretKey;
    IvParameterSpec ivParameterSpec;
    String algorithm;

    public Encrypt() throws NoSuchAlgorithmException {
        secretKey=generateKey(128);
        ivParameterSpec=IvParameterSpecGenerateIv();
         algorithm= "AES/CBC/PKCS5Padding";
    }


    public SealedObject encryptPicture(Picture picture) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, IOException, InvalidKeyException {

        return encryptObject(algorithm,picture,secretKey,ivParameterSpec);
    }

    public Picture decryptSealedPicture(SealedObject sealedObject) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, IOException, InvalidKeyException, ClassNotFoundException {
        return decryptObject(algorithm,sealedObject,secretKey,ivParameterSpec);
    }

    public SecretKey generateKey(int n) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
          //returns secret key
        return keyGenerator.generateKey();
    }

    public IvParameterSpec IvParameterSpecGenerateIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }
    public SealedObject encryptObject(String algorithm, Picture picture, SecretKey key, IvParameterSpec iv) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            InvalidKeyException, IOException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        SealedObject sealedObject = new SealedObject((Serializable) picture, cipher);
        return sealedObject;
    }

    public Picture decryptObject(String algorithm, SealedObject sealedObject,
                                             SecretKey key, IvParameterSpec iv) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
            ClassNotFoundException, BadPaddingException, IllegalBlockSizeException,
            IOException {

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        Picture unsealedPicture= (Picture) sealedObject.getObject(cipher);
        return unsealedPicture;
    }
    public SecretKey getSecretKey(){
        return secretKey;
    }
}
