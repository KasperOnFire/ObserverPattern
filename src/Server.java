import java.util.Observable;

public class Server extends Observable {
    private boolean isDown = false;

    public void changeIsDown(boolean status){
        isDown = status;
        setChanged();
        notifyObservers(isDown);
    }
}
