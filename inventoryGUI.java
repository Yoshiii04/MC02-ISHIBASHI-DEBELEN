import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class inventoryGUI extends JFrame {
    private JTable table;
    private Inventory inventory;

    public inventoryGUI(Inventory inventory) {
        this.inventory = inventory;
    }
    public void run() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JLabel headerLabel = new JLabel("Inventory");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 40));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(headerLabel, BorderLayout.NORTH);
        
        // Tester
        System.out.println ("owned CREATURE IS : " + inventory.getOwnedCreature(0).getName());
        
        // Create table model with column names
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("No.");
        tableModel.addColumn("Creature Name");
        tableModel.addColumn("Type");
        tableModel.addColumn("Family");
        tableModel.addColumn("Level");
        
        // Add data to the table model
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

        // Create JTable with the table model
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(table);

        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Panel for the buttons and text
        JPanel buttonPanel = new JPanel(new BorderLayout());

        // Label for the text
        JLabel changeCreatureLabel = new JLabel("|| Active Creature : " + inventory.getActiveCreature().getName() + " || Do you want to change the active creature?");
        changeCreatureLabel.setHorizontalAlignment(SwingConstants.CENTER);
        buttonPanel.add(changeCreatureLabel, BorderLayout.NORTH);
    
        // Panel for the buttons
        JPanel buttonSubPanel = new JPanel(new FlowLayout());

        // Create buttons for changing active creature
        JButton yes = new JButton("Yes, change active creature");
        JButton no = new JButton("No, exit inventory");

        buttonSubPanel.add(yes);
        buttonSubPanel.add(no);

        buttonPanel.add(buttonSubPanel, BorderLayout.CENTER);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);

        // Add actions for buttons 
        yes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ask the user for the ID of the creature to set as active
                String idInput = JOptionPane.showInputDialog("Enter the ID of the creature to set as active:");
        
                try {
                    int id = Integer.parseInt(idInput);
                    // Check if the entered ID is within the valid range
                    if (id >= 1 && id <= inventory.getArrayListSize()) {
                        // Set the active creature using the entered ID
                        inventory.setActiveCreature(id - 1);
                        System.out.println("Active creature changed to: " + inventory.getActiveCreature().getName());
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid ID. Please enter a valid ID.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid ID number.");
                }
            }
        });

        no.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add your logic for not changing the active creature here
                openMainmenu();
            }
        });
    }

    private void openMainmenu() {
        setVisible(false); // hide the startGUI window
        dispose(); // dispose startGUI window

        // open the MainMenu
        mainmenuGUI mainMenu = new mainmenuGUI(inventory);
        mainMenu.run();
    }

}
