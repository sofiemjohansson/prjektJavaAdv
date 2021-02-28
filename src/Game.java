import java.util.Scanner;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Game {
    private Gui gui;
    private Room room1, room2, room3, room4;
    private Room[] map;
    private Player player;
    private ScheduledThreadPoolExecutor pool;


    public Game() {
        createRooms();
        player = new Player(0);
        this.map[0].setPlayerInRoom();

        gameItems();

        createNpc();


        //Starta guit!
        int position = 0;
        createGui(position);

//[r1, r2, r3]
        while (true) {
            this.map[position].setPlayerInRoom();
            String command = gui.getCommand();
            if (!command.equals("-1")) {

                if (command.equals("1")) {
                    position = 0;

                }
                if (command.equals("2")) {
                    position = 1;
                }


                if (command.equals("3")) {
                    position = 2;
                }

                if (command.equals("4")) {
                    position = 3;
                }
                if (command.equals("take")) {
                    GameObject temp = this.map[position].getInventory().getFirstItem();
                    if (temp != null) {
                        this.player.getInventory().moveObject(map[position].getInventory(), temp);
                    }

                }
                if (command.equals("drop")) {
                    GameObject temp = this.player.getInventory().getFirstItem();
                    if (temp != null) {
                        this.map[position].getInventory().moveObject(this.player.getInventory(), temp);
                    }
                }
                this.map[position].setPlayerInRoom();
            }

            gui.setShowRoom(map[position].toString());
            gui.setShowInventory(this.player.getInventory());
            if (map[position].getPersons() != null) {
                gui.setPerson(map[position].getPersons());
            }

        }


    }

    private void gameItems() {
        GameObject lampa = new GameObject("Taklampa", false);
        GameObject kanin = new GameObject("Liten vit kanin", true);
        GameObject bok = new GameObject("Bok", false);
        GameObject penna = new GameObject("Penna", false);
        Container box = new Container("En skokartong", false, true);
        room1.addObject(kanin);
        room1.addObject(box);
        room2.addObject(lampa);
        room2.addObject(box);
        room3.addObject(kanin);
        room3.addObject(box);
        room3.addObject(bok);
        room4.addObject(bok);
        room4.addObject(penna);
        room4.addObject(box);
    }

    private void createGui(int position) {
        this.gui = new Gui();
        gui.setShowRoom(map[position].toString());
        this.gui.setShowInventory(this.player.getInventory());
    }

    private void createNpc() {
        Person Lemmy = new Person(2, "Lemmy", this.map, gui);
        room1.addNpc(Lemmy);
       Person bruce = new Person(0, "Bruce Dickinson", this.map, gui);
        room2.addNpc(bruce);
        Person axl = new Person(1, "Axl Rose", this.map, gui);
        room3.addNpc(axl);

        pool = new ScheduledThreadPoolExecutor(10);
        pool.scheduleAtFixedRate(Lemmy, 5, 5, TimeUnit.SECONDS);
           pool.scheduleAtFixedRate(bruce, 8, 5, TimeUnit.SECONDS);
        pool.scheduleAtFixedRate(axl, 2, 10, TimeUnit.SECONDS);


    }

    private void createRooms() {
        //Skapa rum!
        room1 = new Room("Vardagsrum", "Stort och fult med en soffa");
        room2 = new Room("Hall", "liten, med ful tapet");
        room3 = new Room("Kök", "Med puttrande kaffe");
        room4 = new Room("Tvättstuga", "Höga berg av ren tvätt");
        map = new Room[4];
        map[0] = room1;
        map[1] = room2;
        map[2] = room3;
        map[3] = room4;
    }
}




