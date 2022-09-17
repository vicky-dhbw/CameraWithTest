public class PictureSaver {

    public static void savePicture(Storage storage,Picture picture){
        storage.store.add(picture);
    }
}
