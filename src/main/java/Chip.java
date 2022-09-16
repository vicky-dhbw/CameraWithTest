import java.util.ArrayList;
import java.util.UUID;
public class Chip {

    final UUID chipId=UUID.randomUUID();
    Core[] cores;

    public UUID getChipId(){
        return chipId;
    }
}
