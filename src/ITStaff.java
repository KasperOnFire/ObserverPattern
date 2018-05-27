import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Observable;
import java.util.Observer;

public class ITStaff implements Observer {

    private boolean serverIsDown;
    private String name;

    public ITStaff(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        serverIsDown = (Boolean) arg;
        System.out.println(name + ", Is the server down? answer: " + serverIsDown);
    }
}
