import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;


public class evoGUI extends JFrame{

   private Inventory inventory; 
   private Creatures selectedCreature1;
   private Creatures selectedCreature2;
   private JLabel evolvedCreatureLabel;
   private JButton evolveButton;
   private JButton exitButton;

   public evoGUI(Inventory inventory) {

       this.inventory = inventory;
       this.evolveButton = new JButton();
       this.exitButton = new JButton();

    }

    public void run(Evolution evolution) {
        JFrame frame = new JFrame();
        JButton inventoryButton = new JButton();

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(200, 200, 400, 600));
        panel.setLayout(new GridLayout(1,1));

        // Creation of buttons for owned Creatures
        for (Creatures creature : inventory.getOwnedCreature()) {
            JButton creatureButton = createButtonForCreature(creature);
            panel.add(creatureButton);
        }

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        evolveButton.setText("Evolve!");
        exitButton.setText("Exit!");
        inventoryButton.setText("Inventory");

        // "Evolve" button
        evolveButton.setPreferredSize(new Dimension(100, 50)); // Adjust width and height as needed
        bottomPanel.add(evolveButton);

        // Add space between buttons
        bottomPanel.add(Box.createHorizontalGlue());

        // Set the size and text for the "Exit" button
        exitButton.setPreferredSize(new Dimension(100, 50)); // Adjust width and height as needed
        bottomPanel.add(exitButton);

        inventoryButton.setPreferredSize(new Dimension(100, 50)); // Adjust width and height as needed
        bottomPanel.add(inventoryButton);

        // Add the panels to the frame using BorderLayout
        frame.add(panel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Evolution");
        frame.pack();
        frame.setVisible(true);

        // 
        inventoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // add action
                displayInventory();
            }
        });

        // Add ActionListener for the "Evolve" button
        /* evolveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle Evolve button click
                
                System.out.println("Evolve button clicked");
                evolveMessage(evolution);
            }
        });
        */
        
        evolveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Evolve button clicked");
        
                if (selectedCreature1 != null && selectedCreature2 != null) {
                    if (selectedCreature1.getName().equals(selectedCreature2.getName())) {
                        if (selectedCreature1.getEvoLevel() == 1) {
                            // Assuming the evolution involves combining the two creatures
                            int index1 = inventory.getOwnedCreature().indexOf(selectedCreature1);
                            int index2 = inventory.getOwnedCreature().indexOf(selectedCreature2);
                            boolean evolutionSuccess = evolution.nextEvolution(index1, index2);
        
                            if (evolutionSuccess) {
                                // Display results or update UI
                                JOptionPane.showMessageDialog(evoGUI.this, "Evolution successful!");
                                // Update UI if needed, display evolvedCreature image or details
                                // displayCreatureImage(evolvedCreatureLabel, evolution.getEvolvedCreature());
                            } else {
                                // Handle cases where evolution fails
                                JOptionPane.showMessageDialog(evoGUI.this, "Evolution failed.");
                            }
                        } else {
                            // Handle other evolution cases
                            JOptionPane.showMessageDialog(evoGUI.this, "Evolution not possible for these creatures.");
                        }
                    } else {
                        // Handle mismatched creatures
                        JOptionPane.showMessageDialog(evoGUI.this, "Selected creatures do not match for evolution.");
                    }
                } else {
                    // Handle case where two creatures are not selected
                    JOptionPane.showMessageDialog(evoGUI.this, "Please select two creatures for evolution.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        
        
        // Add ActionListener for the "Exit" button
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle Exit button click
                mainmenuGUI mainMenu = new mainmenuGUI(inventory);
                mainMenu.run();
                System.out.println("Exit button clicked");
                frame.dispose();
            }
        });
    }

    private ImageIcon createImageIcon(String path, int width, int height) {
        // Get the ClassLoader
        ClassLoader classLoader = getClass().getClassLoader();

        // Use getResource to obtain a URL for the resource
        URL imageURL = classLoader.getResource(path);
        
        if (imageURL != null) {
            // Create an ImageIcon from the URL
            ImageIcon originalIcon = new ImageIcon(imageURL);
            Image image = originalIcon.getImage();
            Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } else {
            // Handle the case where the resource is not found
            System.err.println("Resource not found: " + path);
            return null;
        }
    }
    private JButton createButtonForCreature(Creatures creature) {
        JButton creatureButton = new JButton();
        creatureButton.setText(creature.getName()); // Set button text
        creatureButton.setSize(70,70);
        // Set button icon
        ImageIcon icon = createImageIcon(creature.getImagePath(), 50, 50);
        if (icon != null) {
            creatureButton.setIcon(icon);
        } else {
            System.err.println("Image not found for creature: " + creature.getName());
        }

        // Add ActionListener to handle button click
        creatureButton.addActionListener(e -> handleCreatureButtonClick(creature));

        return creatureButton;
    }
    private void evolveMessage(int creature1Index, int creature2Index) {
    // Implement your evolution logic here

        Evolution evolution = new Evolution(inventory);

        if (evolution.nextEvolution(creature1Index, creature2Index)) {

            // Display a message or update GUI based on evolution result
            JOptionPane.showMessageDialog(this, "Evolution successfully!");
            displayCreatureImage(evolvedCreatureLabel, evolution.getEvolvedCreature());
        } else {
            // Handle the case where the user didn't select two creatures
            JOptionPane.showMessageDialog(this, "Please select two creatures for evolution.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void handleCreatureButtonClick(Creatures creature) {
        // Handle the button click for the specific creature
        System.out.println("Button clicked for creature: " + creature.getName());
    
        int index1 = inventory.getOwnedCreature().indexOf(selectedCreature1);
        int index2 = inventory.getOwnedCreature().indexOf(selectedCreature2);
    
        if (selectedCreature1 == null) {
            // If selectedCreature1 is null or the same creature is already selected, assign the clicked creature to it
            selectedCreature1 = creature;
            JOptionPane.showMessageDialog(this, "Selected creature: " + selectedCreature1.getName() +
                    "\nType: " + selectedCreature1.getType() +
                    "\nFamily: " + selectedCreature1.getFamily() +
                    "\nLevel: " + selectedCreature1.getEvoLevel());
    
            System.out.println("Button clicked for creature: " + creature.getName());
        } else if(selectedCreature2 == null && index2 != index1) {
            // If selectedCreature2 is null or the same creature is already selected, assign it
            selectedCreature2 = creature;
    
            JOptionPane.showMessageDialog(this, "Selected creature: " + selectedCreature2.getName() +
                    "\nType: " + selectedCreature2.getType() +
                    "\nFamily: " + selectedCreature2.getFamily() +
                    "\nLevel: " + selectedCreature2.getEvoLevel());
    
            // Now you have two selected creatures, you can enable the evolve button or perform any other action
            // Enable the evolve button or perform any other action
            evolveButton.setEnabled(true);
        } else {
            // Find the indices of selectedCreature1 and selectedCreature2
            index1 = inventory.getOwnedCreature().indexOf(selectedCreature1);
            index2 = inventory.getOwnedCreature().indexOf(selectedCreature2);
    
            // Show a message displaying the indices of selectedCreature1 and selectedCreature2
            JOptionPane.showMessageDialog(this, "Index of selectedCreature1: " + index1 +
                    "\nIndex of selectedCreature2: " + index2);
        }
    }

    
//error with evolving
    public void displayCreatureImage(JLabel label, Creatures creature) {
        // Get the image path or image from the Creatures class
        // Replace "getImagePath()" with the appropriate method in your Creatures class
        String imagePath = creature.getImagePath(); // image

        // Set the image icon to the label
        label.setIcon(new ImageIcon(imagePath));
    }

    private void evolveMessage(Evolution evolution) {
        // Implement your evolution logic here

        if (selectedCreature1 != null && selectedCreature2 != null) {
            // Display a message or update GUI based on evolution result
            JOptionPane.showMessageDialog(this, "Evolution successful!");
            displayCreatureImage(evolvedCreatureLabel, evolution.getEvolvedCreature());
        } else {
            // Handle the case where the user didn't select two creatures
            JOptionPane.showMessageDialog(this, "Please select two creatures for evolution.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }


    // ADDED 
    public void displayInventory() {
        JPanel panel = new JPanel(new BorderLayout());
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("No.");
        tableModel.addColumn("Creature Name");
        tableModel.addColumn("Type");
        tableModel.addColumn("Family");
        tableModel.addColumn("Level");

        for (int i = 0; i < inventory.getArrayListSize(); i++) {
            Creatures creature = inventory.getOwnedCreature(i);
            Object[] rowData = {
                    i + 1,
                    creature.getName(),
                    creature.getType(),
                    creature.getFamily(),
                    creature.getEvoLevel()
            };
            tableModel.addRow(rowData);
        }

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(scrollPane, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(null, panel, "Inventory", JOptionPane.PLAIN_MESSAGE);
    }
    
}
