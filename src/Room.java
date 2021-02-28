public class Room {
    private String name;
    private String description;
    private Inventory inventory;
    private Person[] person;
    private int numberOfPersons;
    private boolean playerInRoom;


    public Room(String roomName, String roomDescription) {
        this.person = new Person[5];
        this.name = roomName;
        this.description = roomDescription;
        this.inventory = new Inventory(10);
        this.numberOfPersons = 0;
        this.playerInRoom= false;
    }
    public synchronized void setPlayerInRoom(){
        this.playerInRoom=!this.playerInRoom;
    }
public synchronized boolean isPlayerInRoom() {
   return this.playerInRoom;
    }
    public synchronized void addNpc(Person person) {

        this.person[this.numberOfPersons] = person;
    }

    public synchronized void removeNpc(Person person) {
        int index = -1;
        for (int i = 0; i < this.numberOfPersons; i++) {
            if (this.person[i].name.equals(person.getName())) {
                index = i;
            }
        }
        if (index != -1) {
            Person[] copyArray = new Person[this.person.length - 1];
            System.arraycopy(person, 0, copyArray, 0, index);
            System.arraycopy(person, index + 1, copyArray, index, this.person.length - index - 1);
            this.numberOfPersons--;
        }
    }

    public Person getPersons() {
        return this.person[0];
    }

    public void addObject(GameObject go) {
        this.inventory.addObject(go);
    }

    public String toString() {
        return name + " : " + description + "\n" + inventory;
    }

    public synchronized Inventory getInventory() {
        return inventory;
    }
}
