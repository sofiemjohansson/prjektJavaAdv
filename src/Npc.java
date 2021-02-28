public abstract class Npc {
    String name;
    Inventory inventory;
    Npc npc;

    public Npc(String name) {
        this.name = name;
        this.inventory = new Inventory(1);
    }

    public synchronized String getName() {
        return name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public synchronized String toString() {
        return this.name + " is carrying " + this.inventory;

    }
    public synchronized String SuperName() {
        return npc.SuperName() + " is carrying " + this.inventory;

}}
