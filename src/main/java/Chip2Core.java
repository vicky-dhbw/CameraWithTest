import java.util.stream.Stream;

public class Chip2Core extends Chip{

    public Chip2Core(){
        this.cores= Stream.generate(Core::new).limit(Configuration.INSTANCE.numberOfCoresIn2Core).toArray(Core[]::new);
    }

}
