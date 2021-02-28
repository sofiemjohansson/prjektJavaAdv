public class Player {

    private int position;
    private Inventory inventory;

    public Player(int position) {
        this.position = position;
        this.inventory = new Inventory(5);
    }

    public Inventory getInventory() {
        return this.inventory;
    }
}