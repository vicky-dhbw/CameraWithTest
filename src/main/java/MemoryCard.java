import java.util.Collection;
import java.util.Stack;

public class MemoryCard extends Storage{

    Encrypt encrypt;
    public MemoryCard(){
        this.store=new Stack<>();
    }
    public Stack<Picture> getStore(){
        return (Stack<Picture>) store;
    }
}
