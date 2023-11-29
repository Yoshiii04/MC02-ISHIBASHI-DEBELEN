import javax.swing.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class startGUI extends JFrame{
    private Inventory inventory;
    private CreatureList creatureList;

    public startGUI(Inventory inventory, CreatureList creatureList) {
        this.inventory = inventory;
        this.creatureList = creatureList;
    }

    public void run () {

        // button creation
        JButton creature1Button = createButtonWithImage("C:\\Users\\yoshi\\Downloads\\MC01-ISHIBASHI-DEBELEN\\MC01-ISHIBASHI-DEBELEN\\images\\1Strawander.png");
        JButton creature2Button = createButtonWithImage("C:\\Users\\yoshi\\Downloads\\MC01-ISHIBASHI-DEBELEN\\MC01-ISHIBASHI-DEBELEN\\images\\4Chocowool.png");
        JButton creature3Button = createButtonWithImage("C:\\Users\\yoshi\\Downloads\\MC01-ISHIBASHI-DEBELEN\\MC01-ISHIBASHI-DEBELEN\\images\\7Parfwit.png");
        JButton creature4Button = createButtonWithImage("C:\\Users\\yoshi\\Downloads\\MC01-ISHIBASHI-DEBELEN\\MC01-ISHIBASHI-DEBELEN\\images\\10Brownisaur.png");
        JButton creature5Button = createButtonWithImage("C:\\Users\\yoshi\\Downloads\\MC01-ISHIBASHI-DEBELEN\\MC01-ISHIBASHI-DEBELEN\\images\\13Frubat.png");
        JButton creature6Button = createButtonWithImage("C:\\Users\\yoshi\\Downloads\\MC01-ISHIBASHI-DEBELEN\\MC01-ISHIBASHI-DEBELEN\\images\\16Malts.png");
        JButton creature7Button = createButtonWithImage("C:\\Users\\yoshi\\Downloads\\MC01-ISHIBASHI-DEBELEN\\MC01-ISHIBASHI-DEBELEN\\images\\19Squirpie.png");
        JButton creature8Button = createButtonWithImage("C:\\Users\\yoshi\\Downloads\\MC01-ISHIBASHI-DEBELEN\\MC01-ISHIBASHI-DEBELEN\\images\\22Chocolite.png");
        JButton creature9Button = createButtonWithImage("C:\\Users\\yoshi\\Downloads\\MC01-ISHIBASHI-DEBELEN\\MC01-ISHIBASHI-DEBELEN\\images\\25Oshacone.png");

        // interface creation
        int width = 90;
        int height = 90;

        setSize(700, 500);
        creature1Button.setBounds(50, 50, width, height);
        creature2Button.setBounds(249, 50, width, height);
        creature3Button.setBounds(465, 50, width, height);
        creature4Button.setBounds(50, 150, width, height);
        creature5Button.setBounds(249, 150, width, height);
        creature6Button.setBounds(465, 150, width, height);
        creature7Button.setBounds(50, 250, width, height);
        creature8Button.setBounds(249, 250, width, height);
        creature9Button.setBounds(465, 250, width, height);

        // button add
        add(creature1Button);
        add(creature2Button);
        add(creature3Button);
        add(creature4Button);
        add(creature5Button);
        add(creature6Button);
        add(creature7Button);
        add(creature8Button);
        add(creature9Button);

        setResizable (false);
        setLayout(null);
        setVisible(true);

        // Button actions
        creature1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inventory.addCreature(creatureList.getCreaturefromList(0));
                inventory.setActiveCreature(0); 

                //Tester
                System.out.println ("ACTIVE CREATURE IS " + inventory.getActiveCreature().getName());
                System.out.println ("Owned Creature is : " + inventory.getOwnedCreature(0).getName());
                openMainmenu();
            }
        });

        creature2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inventory.addCreature(creatureList.getCreaturefromList(3));
                inventory.setActiveCreature(0); 

                openMainmenu();
            }
        });

        creature3Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inventory.addCreature(creatureList.getCreaturefromList(6));
                inventory.setActiveCreature(0); 

                openMainmenu();
            }
        });

        creature4Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inventory.addCreature(creatureList.getCreaturefromList(9));
                inventory.setActiveCreature(0); 

                openMainmenu();
            }
        });

        creature5Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inventory.addCreature(creatureList.getCreaturefromList(12));
                inventory.setActiveCreature(0); 

                openMainmenu();
            }
        });

        creature6Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inventory.addCreature(creatureList.getCreaturefromList(15));
                inventory.setActiveCreature(0); 

                openMainmenu();
            }
        });

        creature7Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inventory.addCreature(creatureList.getCreaturefromList(18));
                inventory.setActiveCreature(0); 

                openMainmenu();
            }
        });

        creature8Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inventory.addCreature(creatureList.getCreaturefromList(21));
                inventory.setActiveCreature(0); 

                openMainmenu();
            }
        });

        creature9Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inventory.addCreature(creatureList.getCreaturefromList(24));
                inventory.setActiveCreature(0); 

                //tester
                System.out.println ("ACTIVE CREATURE IS " + inventory.getActiveCreature().getName()); 

                openMainmenu();
            }
        });

    }

 
    // create button with image
    private JButton createButtonWithImage(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage();
        
        int buttonWidth = 100;
        int buttonHeight = 100; 
    
        // Resize the image while preserving its aspect ratio
        Image scaledImg = img.getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
    
        JButton button = new JButton(scaledIcon);
        
        return button;
    }

    // opening the mainmenuGUI and closing the startGUI
    private void openMainmenu() {
        setVisible(false); // hide the startGUI window
        dispose(); // dispose startGUI window

        // open the MainMenu
        mainmenuGUI mainMenu = new mainmenuGUI(inventory);
        mainMenu.run();
    }
}
