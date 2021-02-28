
public class Person extends Npc implements Runnable {
    int position;
    String name;
    Inventory inventory;
    Room[] map;
    Gui gui;


    public Person(int position, String name, Room[] map, Gui gui) {
        super(name);
        this.position = position;
        this.map = map;

    }

    public void show() {
        System.out.println(this.name + " befinner sig i position " + this.position);
    }


    public synchronized void move(int x) {

        this.position = x;

    }

    @Override
    public synchronized void run() {
        int rand = (int) (Math.random() * 100) + 1;
        if (rand < 40) {
            moveNpc();
        } else if (rand > 40 && rand < 75) {

            GameObject temp = super.getInventory().getFirstItem();
            if (temp == null) {
                temp = map[position].getInventory().getFirstItem();
                super.getInventory().moveObject(map[position].getInventory(), temp);
                System.out.println(super.name + " taked " + temp);
            }

        } else {
            GameObject temp = super.getInventory().getFirstItem();
            if (temp != null) {
                map[position].getInventory().moveObject(super.getInventory(), temp);
                System.out.println(super.name + " dropped " + temp);
            }


        }

        if (this.map[position].isPlayerInRoom()) {
            gui.setShowRoom(map[position].toString());
            gui.setShowPersons(this);

        }


    }


    private synchronized void moveNpc() {
        this.map[this.position].removeNpc(this);
        int rand = (int) (Math.random() * 100);
        if (rand < 50 && this.position != 0) {
            this.position -= 1;
            System.out.println(super.name + " går åt vänster");
        } else {
            this.position += 1;
            System.out.println(super.name + " går åt höger");
        }
        this.map[this.position].addNpc(this);
    }

    private synchronized void Npc() {
        this.map[this.position].removeNpc(this);
        int rand = (int) (Math.random() * 50);
        if (rand < 20 && this.position != 0) {
            this.position = +1;
            System.out.println(super.name + " går åt vänster");
        } else {
            this.position = -1;
            System.out.println(super.name + " går åt höger");
        }
        this.map[this.position].addNpc(this);
    }
}




