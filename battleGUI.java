import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class battleGUI extends JFrame{
    private Battlephase battlePhase;
    private Inventory inventory;
    private mainmenuGUI mainmenu;

    // Enemy infos
    private Creatures enemy;
    private String enemyName;
    private String enemyType;
    private int enemyHP;
    private int actions;
    private String enemyImagePath;
    

    public battleGUI (Inventory inventory, mainmenuGUI mainmenu) {
        this.inventory = inventory;
        this.mainmenu = mainmenu;
        this.battlePhase = new Battlephase();

        this.enemy = battlePhase.getEnemy(); 
        this.enemyName = enemy.getName();
        this.enemyType = enemy.getType();
        this.enemyHP = battlePhase.getEnemyHP();
        this.enemyImagePath = battlePhase.getEnemyImagePath();
        this.actions = battlePhase.getActions();
    }

    public void run () {
        setTitle("Battle Phase");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(null);
        
        // Active Creature infos
        String tempName = inventory.getActiveCreature().getName();
        String tempType = inventory.getActiveCreature().getType();
        int tempLevel = inventory.getActiveCreature().getEvoLevel();
        String aImagePath = inventory.getActiveCreature().getImagePath();
        

        JLabel playerCreatureLabel = new JLabel(new ImageIcon(aImagePath));
        JLabel enemyCreatureLabel = new JLabel(new ImageIcon(enemyImagePath));

        JLabel playerCreatureNameLabel = new JLabel("Active Creature : " + tempName);
        JLabel enemyCreatureNameLabel = new JLabel("Enemy Creature : " + enemyName);

        JLabel playerCreatureTypeLabel = new JLabel("Creature Type : " + tempType);
        JLabel enemyCreatureTypeLabel = new JLabel("Enemy Type : " + enemyType);

        JLabel enemyCreatureHPLabel = new JLabel("HP: " + enemyHP);

        JButton attackButton = new JButton("Attack");
        JButton swapButton = new JButton("Swap");
        JButton catchButton = new JButton("Catch");
        JButton runButton = new JButton("Run Away");

        // Enemy Creature image, name, type, and HP
        enemyCreatureLabel.setBounds(178, 0, 450, 450);
        add(enemyCreatureLabel);

        enemyCreatureNameLabel.setBounds(550, 100, 200, 30);
        add(enemyCreatureNameLabel);

        enemyCreatureTypeLabel.setBounds(550, 130, 200, 30);
        add(enemyCreatureTypeLabel);

        enemyCreatureHPLabel.setBounds(100, 100, 200, 100);
        add(enemyCreatureHPLabel);

        // Active Creature image, name, and type
        playerCreatureLabel.setBounds(45, 420, 100, 100);
        add(playerCreatureLabel);

        playerCreatureNameLabel.setBounds(150, 430, 200, 30);
        add(playerCreatureNameLabel);

        playerCreatureTypeLabel.setBounds(150, 450, 200, 30);
        add(playerCreatureTypeLabel);

        // Buttons
        attackButton.setBounds(350, 450, 100, 30);
        add(attackButton);

        swapButton.setBounds(450, 450, 100, 30);
        add(swapButton);

        catchButton.setBounds(550, 450, 100, 30);
        add(catchButton);

        runButton.setBounds(650, 450, 100, 30);
        add(runButton);

        attackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (actions > 0) {
                Random random = new Random();
                int damage;
                damage = random.nextInt(10) + 1 * tempLevel;
                
                String type1 = tempType;
                String type2 = enemyType;

                if (type1.equals("fire") && type2.equals("grass")) {
                    damage = (int)(damage * 1.5); 
                } 
                else if (type1.equals("grass") && type2.equals("water")) {
                    damage = (int)(damage * 1.5);
                } 
                else if (type1.equals("water") && type2.equals("fire")) {
                    damage = (int)(damage * 1.5); 
                }
                
                enemyHP -= damage;
                int updatedEnemyHP = enemyHP;
                enemyCreatureHPLabel.setText("HP: " + updatedEnemyHP);
                actions--;
                System.out.println ("ACTIONS REMAINING : " + actions);
                }
                else {
                    JOptionPane.showMessageDialog(null, "No more actions left..");
                    endBattle();
                }
                battlePhase.updateEnemy();
            }
        });

        swapButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (actions != 0) {
                    int choice = JOptionPane.showConfirmDialog(null,
                        "Do you want to change the active creature?",
                        "Swap Active Creature",
                        JOptionPane.YES_NO_OPTION);

                    if (choice == JOptionPane.YES_OPTION) {
                        swapActiveCreature();
                    }
                }   else {
                    endBattle();
                }
                battlePhase.updateEnemy();
            }
        });

        catchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (actions > 0) {
                    int catchChance = 40 + 50 - enemyHP;
                    Random random = new Random();
                    int randomNumber = random.nextInt(100) + 1;
        
                    if (randomNumber <= catchChance) {
                        // display caught message
                        JOptionPane.showMessageDialog(null, "You caught the enemy creature!");
                        inventory.addCreature(enemy);
                    } else {
                        // display failed message
                        JOptionPane.showMessageDialog(null, "You failed to catch the enemy creature..");
                        endBattle();
                    }
                    actions--;
        
                    if (actions == 0) {
                        
                        endBattle();
                    } else {
                        battlePhase.updateEnemy();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No more actions left..");
                    endBattle();
                }
            }
        });

        runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                endBattle();
                battlePhase.updateEnemy();
            }
        });
        setVisible(true);
    }
    
    private void endBattle () {
        setVisible(false); // hide the startGUI window
        dispose(); // dispose startGUI window

        // open the MainMenu
        mainmenuGUI mainMenu = new mainmenuGUI(inventory);
        mainMenu.run();
    }

     private void swapActiveCreature() {
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

    int choice = JOptionPane.showConfirmDialog(null, panel,
            "Choose the Active Creature", JOptionPane.OK_CANCEL_OPTION);

    if (choice == JOptionPane.OK_OPTION) {
        String idInput = JOptionPane.showInputDialog("Enter the ID of the creature to set as active:");
        
        try {
            int id = Integer.parseInt(idInput);
            if (id >= 1 && id <= inventory.getArrayListSize()) {
                inventory.setActiveCreature(id - 1);
                System.out.println("Active creature changed to: " + inventory.getActiveCreature().getName());
                actions--; 
            } else {
                JOptionPane.showMessageDialog(null, "Invalid ID. Please enter a valid ID.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid ID number.");
            }
        }
    }

}