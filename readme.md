___
## Observer
### Problem
When to look for updates about a change in state in a particular object.
#### Define a one-to-many dependency between objects so that when one object changes state, all its dependents 
are notified and updated automatically.

```java
public class Canteen extends Observable {

    private final boolean hasPizzaInStock = true;

    public void changePizzaStock(boolean stock) {
        setChanged();
        notifyObservers(stock);
    }

}
```

The Canteen class acts as what's being 'observed', and the field being observed is a boolean of whether or not 
they have pizza in stock. **setChanged()** marks the Canteen as having been changed, which will make the 
**hasChanged()** method return true, allowing you to do conditional checks for whether or not a change has 
occurred. 

**notifyObservers(stock)** lets all observers know of the change, triggering their **update()** method and 
supplying them with the new value.

```java
public class Student implements Observer {

    private boolean canteenPizzaStock;
    private String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        canteenPizzaStock = (Boolean) arg;
        System.out.println(name + " sees the canteen's pizza stock as: " + canteenPizzaStock);
    }

}
```

The Student class is our 'observer', observing the canteen's stock of pizza. Whenever it changes, the Student 
will be notified of this and its **update()** method gets called, changing the local variable to the newly 
received value, and printing out the new stock. 

```java
public class Main {
    
    public static void main(String[] args) {
        Canteen canteen = new Canteen();
        Student lovro = new Student("Lovro");
        Student thomas = new Student("Thomas");
        Student mathias = new Student("Mathias");
        canteen.addObserver(lovro);
        canteen.addObserver(thomas);
        canteen.addObserver(mathias);
        canteen.changePizzaStock(false);
    }
    
}
```
```
Mathias sees the canteen's pizza stock as: false
Thomas sees the canteen's pizza stock as: false
Lovro sees the canteen's pizza stock as: false
```

Testing this out in the main, we see that once **changePizzaStock()** is called, every observer is notified of 
this and receives the new value.

___