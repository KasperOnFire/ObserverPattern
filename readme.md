# Observer Pattern

## Problem

When to look for updates about a change in state in a particular object.

### Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically

```java
import java.util.Observable;

public class Server extends Observable {
    private boolean isDown = false;

    public void changeIsDown(boolean status){
        isDown = status;
        setChanged();
        notifyObservers(isDown);
    }
}

```

The Server class acts as what's being 'observed', and the field being observed is a boolean of whether or not the server is down. **setChanged()** marks the Server as having been changed, which will make the **hasChanged()** method return true, allowing you to do conditional checks for whether or not a change has occurred. 

**notifyObservers(isDown)** lets all observers know of the change, triggering their **update()** method and supplying them with the new value.

```java
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

```

The ITStaff class is our 'observer', observing if the server is down. Whenever it changes, the ITStaff will be notified of this and its **update()** method gets called, changing the local variable to the newly received value, and printing out the new status.

```java
public class Main {

    public static void main(String[] args) {
        Server srv01 = new Server();
        ITStaff krb = new ITStaff("Kasper");
        ITStaff tog = new ITStaff("Tobias");
        srv01.addObserver(krb);
        srv01.addObserver(tog);

        srv01.changeIsDown(true);
    }
}

```

```bash
Tobias, Is the server down? answer: true
Kasper, Is the server down? answer: true
```

Testing this out in the main, we see that once **changeIsDOwn()** is called, every observer is notified of this and receives the new value.
___