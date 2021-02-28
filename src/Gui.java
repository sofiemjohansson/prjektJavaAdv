import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;



public class Gui extends JFrame {

    private JPanel panel;
    private JTextArea showRoom;
    private JTextArea showPersons;
    private JTextField input;
    private JTextArea inventory;
    private String command;
    private boolean gotCommand;
    private JButton button;
    public Gui(){
        this.gotCommand = false;
        this.command = "";
        this.setTitle("Game");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUpElements();
        setUpPanel();
        this.add(panel);
        this.setVisible(true);
        this.setResizable(false);
    }

    //Returnera det senaste commitade kommandot
    public String getCommand(){
        if (this.gotCommand){
            this.gotCommand= false;
            System.out.println(this.command);
            return this.command;
        }
        return "-1";

    }
    //HÃ¤r kan man updatera respektive fÃ¤lt:
    public synchronized void setShowRoom(String roomDescription){
        this.showRoom.setText(roomDescription);
    }
    public synchronized void setShowPersons(Person person ){
        this.showPersons.setText(person.toString());
    }
    public synchronized void setShowInventory(Inventory i){
        this.inventory.setText(i.toString());
    }

    //Add person to room
    public synchronized void setPerson(Person p){
        this.showPersons.setText(p.toString());
    }


    public void gotCommand(){
        this.gotCommand = false;
    }

    private synchronized void setUpPanel(){
        this.panel.add(showPersons);
        this.panel.add(showRoom);
        this.panel.add(input);
        this.panel.add(inventory);
        this.panel.add(button);

    }
    private synchronized void setUpElements(){
        this.panel = new JPanel(new GridLayout(4,3));
        this.showRoom = new JTextArea("Room: ");
        this.showPersons = new JTextArea("Persons");
        this.inventory = new JTextArea("Inventory");
        this.input = new JTextField("Give command");
        this.showPersons.setEditable(false);
        this.showRoom.setEditable(false);
        this.inventory.setEditable(false);

        ActionListener inputListener = e -> {
            this.command = input.getText();
            System.out.println("click");
            this.gotCommand = true;
        };

        input.addActionListener(inputListener);

        this.button = new JButton("commit");
        this.button.addActionListener(inputListener);

    }

}









