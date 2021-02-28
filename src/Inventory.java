import java.util.Arrays;
import java.util.Objects;

public class Inventory {
    private GameObject[] list;
    private int size;

    public Inventory(int size){
        this.size = size;
        list = new GameObject[size];

    }


    public synchronized void addObject(GameObject go){
       int index = getFirstEmptyIndex();

        if (index ==-1){
            System.out.println("Inventory är fullt");
            return;
        }
        this.list[index] = go;
    }
    public synchronized void moveObject(Inventory i2, GameObject go) {
        int index = getFirstEmptyIndex();
        if(index !=-1){
            this.list[index]= go;

            i2.removeObject(go);
        }

    }
    public synchronized void removeObject(GameObject go){

          for(int i= 0; i< this.size; i++){
              GameObject temp= this.list[i];
              if(temp!= null){
              if(temp.getName().equals(go.getName())) {
                  this.list[i] = null;
              }
              }
          }
    }
    public synchronized String toString(){
        GameObject[] temp = Arrays.stream(this.list).filter(Objects::nonNull).toArray(GameObject []::new);
        return Arrays.toString(temp);
    }

    private int getFirstEmptyIndex(){

        for (int i = 0; i<this.list.length; i++){
            if (this.list[i] == null){
                return i;
            }
        }
        return -1;


    }

    public synchronized GameObject getFirstItem(){

        for (int i = 0; i<this.list.length; i++){
            if (this.list[i] !=null){
                return this.list[i];
            }
        }
        return null;


    }
}
