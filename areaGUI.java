import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class areaGUI extends JFrame {

    private JButton area1Button;
    private JButton area2Button;
    private JButton area3Button;
    private JButton exitButton;
    private JPanel cardPanel;
    private int intArea; 
    private Inventory inventory;
    private battleGUI battle; 
    private mainmenuGUI main; 

    public areaGUI(Inventory inventory, Area area) {
        this.inventory = inventory; 
        this.main = new mainmenuGUI(inventory);
        this.battle = new battleGUI(inventory, main);
    }

    public void run() {
        // Initialize GUI components
        area1Button = new JButton("AREA 1");
        area2Button = new JButton("AREA 2");
        area3Button = new JButton("AREA 3");
        exitButton = new JButton("EXIT");

        

        // Set up the layout
        setLayout(new BorderLayout());

        // Add buttons to the top of the frame
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(area1Button);
        buttonPanel.add(area2Button);
        buttonPanel.add(area3Button);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.PAGE_START);

        // Create a card panel for grid layouts
        CardLayout cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Create panels for each area
        JPanel area1Panel = createAreaPanel(1, 5, 1);
        JPanel area2Panel = createAreaPanel(2, 3, 3);
        JPanel area3Panel = createAreaPanel(3, 4, 4);

        // Add panels to the card panel
        cardPanel.add(area1Panel, "AREA1");
        cardPanel.add(area2Panel, "AREA2");
        cardPanel.add(area3Panel, "AREA3");

        // Add the card panel to the center of the frame
        add(cardPanel, BorderLayout.CENTER);
        // Set up button actions
        area1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                intArea = 1;
                CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
                cardLayout.show(cardPanel, "AREA1");
            }
        });

        area2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                intArea = 2;
                CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
                cardLayout.show(cardPanel, "AREA2");
            }
        });

        area3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                intArea = 3;
                CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
                cardLayout.show(cardPanel, "AREA3");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform actions when Exit button is clicked (e.g., exit the program)
                mainmenuGUI main = new mainmenuGUI(inventory);
                main.run();
                dispose();  


            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Area Selection");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createAreaPanel(int areaNumber, int columns, int rows) {
        JPanel panel = new JPanel();
        // Set up the board panel here (add components, set layout, etc.)
        panel.setBackground(Color.GREEN);
        panel.setLayout(new BorderLayout());
    
        // Create a sub-panel for the cells
        JPanel cellsPanel = new JPanel(new GridLayout(rows, columns));
    
        // Add components or customize based on the areaNumber
        for (int i = 0; i < columns * rows; i++) {
            JLabel label = new JLabel("Cell " + (i + 1));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            cellsPanel.add(label);
        }
    
        // Create a sub-panel for the buttons with GridBagLayout
        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Set insets for spacing
    
        // Add buttons for left, right, up, and down
        JButton upButton = new JButton("Up");
        JButton leftButton = new JButton("Left");
        JButton downButton = new JButton("Down");
        JButton rightButton = new JButton("Right");
    
        upButton.addActionListener(e -> {
            // Add your logic for the "Up" button
            System.out.println("Up button clicked");
        });
    
        leftButton.addActionListener(e -> {
            // Add your logic for the "Left" button
            System.out.println("Left button clicked");
        });
    
        downButton.addActionListener(e -> {
            // Add your logic for the "Down" button
            System.out.println("Down button clicked");
        });
    
        rightButton.addActionListener(e -> {
            // Add your logic for the "Right" button
            System.out.println("Right button clicked");
        });
    
        buttonsPanel.add(upButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonsPanel.add(leftButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        buttonsPanel.add(downButton, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        buttonsPanel.add(rightButton, gbc);
    
        // Add sub-panels to the main panel
        panel.add(cellsPanel, BorderLayout.NORTH);
        panel.add(buttonsPanel, BorderLayout.WEST);
    
        // Set preferred size
        panel.setPreferredSize(new Dimension(columns * 70, (rows + 1) * 70)); // Adjust the size as needed
        return panel;
    }
// encounter 40% 
//appear in the board 
//make the left n right move 
}


