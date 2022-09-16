import java.util.ArrayList;

public class Chip4Core extends Chip{

    public Chip4Core(){
        this.cores=new Core[Configuration.INSTANCE.numberOfCoresIn4Core];
        for(int i=0;i<Configuration.INSTANCE.numberOfCoresIn4Core;i++){
            cores[i]=new Core();
        }
    }
}
