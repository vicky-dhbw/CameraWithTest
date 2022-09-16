import java.util.ArrayList;
import java.util.stream.Stream;

public class Chip4Core extends Chip{

    public Chip4Core(){
        this.cores= Stream.generate(Core::new).limit(Configuration.INSTANCE.numberOfCoresIn4Core).toArray(Core[]::new);
    }
}
