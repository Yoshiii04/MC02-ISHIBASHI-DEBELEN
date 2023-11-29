import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class mainmenuGUI extends JFrame{

    private Inventory inventory;
    private Evolution evolution;
    private Area area; 

    public mainmenuGUI (Inventory inventory) {
        this.inventory = inventory;
        this.evolution = new Evolution(inventory); 
        this.area = new Area();

        // Other initialization code
    }

    public void run () {

        JButton viewInventoryButton = new JButton("View Inventory");
        JButton exploreAreaButton = new JButton("Explore an Area");
        JButton evolveCreatureButton = new JButton("Evolve Creature");
        JButton exitButton = new JButton("Exit");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300); 
        setLayout(new GridLayout(4, 1)); 

        // add action listeners to buttons
        viewInventoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // add action
                openInventory();
            }
        });

        exploreAreaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // add action

                // temp tester for battle
                openBattle();
            }
        });

        evolveCreatureButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // add action
                openEvolve();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exiting the game. Goodbye!");
                System.exit(0);
            }
        });

        add(viewInventoryButton);
        add(exploreAreaButton);
        add(evolveCreatureButton);
        add(exitButton);

        // remove so gridLayout would work
        // frame.setResizable (false);
        // frame.setLayout(null);

        setVisible(true);
    }

    private void openInventory() {
        setVisible(false); // hide the startGUI window
        dispose(); // dispose startGUI window

        // open the MainMenu
        inventoryGUI inventoryy = new inventoryGUI(inventory);
        inventoryy.run();
    }

    private void openEvolve() {
        setVisible(false); // hide the startGUI window
        dispose(); // dispose startGUI window

        // open the MainMenu
        evoGUI evoGui = new evoGUI(inventory); 
        evoGui.run(evolution);
    }

    private void openBattle () {
        setVisible(false); // hide the startGUI window
        dispose(); // dispose startGUI window

        // open the MainMenu
        areaGUI areagui = new areaGUI(inventory, area); 
        areagui.run();
       // battleGUI battleGui = new battleGUI(inventory, this); test muna 
        //battleGui.run();
    }

}
