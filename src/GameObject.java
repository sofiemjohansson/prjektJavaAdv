public class GameObject {
    private String name;
    boolean moveable;

    public GameObject(String name ,boolean moveable){
        this.name = name;
        this.moveable = moveable;
    }
    public synchronized boolean isMoveable(){
        return this.moveable;
    }
    public synchronized String getName(){
        return this.name;
    }
    public synchronized String toString(){

        return this.name;
    }
}
