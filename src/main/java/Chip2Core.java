public class Chip2Core extends Chip{

    public Chip2Core(){
        this.cores=new Core[Configuration.INSTANCE.numberOfCoresIn2Core];
        for(int i=0;i<Configuration.INSTANCE.numberOfCoresIn2Core;i++){
            cores[i]=new Core();
        }
    }

}
