import java.util.Scanner;

/**
 * The main class that serves as the starting point of the game.
 */
public class Application {
    public void run() {
        Scanner scanner = new Scanner(System.in);
        CreatureList creatureList = new CreatureList();
        Inventory inventory = new Inventory(creatureList);
        Evolution evolution = new Evolution(inventory); 
        System.out.println ("\nChoose your Starting Creature!");
        System.out.println ("[1] " + creatureList.getCreatureName(0));  
        System.out.println ("[2] " + creatureList.getCreatureName(9));
        System.out.println ("[3] " + creatureList.getCreatureName(18));
        System.out.print ("INPUT: ");
        

        boolean valid = false;
        int input = 0;
        while (!valid) {
            input = scanner.nextInt();
            if (input == 1 || input == 2 || input == 3) {
                valid = true;
            }
            else {
                System.out.println ("Invalid Input, try again.");
                System.out.print ("INPUT: ");
            }
        }

        //  switch statement to add chosen Creature
        switch (input) {
            case 1:
                inventory.addCreature(creatureList.getCreaturefromList(0));
                break;
            case 2:
                inventory.addCreature(creatureList.getCreaturefromList(9));
                break;
            case 3:
                inventory.addCreature(creatureList.getCreaturefromList(18));
                break;
        }
        
        //  set chosen Creature as 'Active Creature'
        inventory.setActiveCreature(0); 
        //  print active creature (tester lamang) 
        System.out.println ("ACTIVE CREATURE IS " + inventory.getActiveCreature().getName());   
        
        int choice;
         /**
         * Starts the game and provides the user with a menu to interact with.
         */
        //  next step: display menu screen 
        do {
            System.out.println ("MENU SCREEN");
            System.out.println ("[1] View Inventory");
            System.out.println ("[2] Explore an Area");
            System.out.println ("[3] Evolve Creature");
            System.out.println ("[4] Exit");
            choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                   // viewInventory();
                   System.out.println ("\t\t\t\t\t Inventory ");
                    inventory.printInventory();         //  display Creatures in inventory method
                    inventory.ActiveCreatureChange();   //  active creature change method 
                    break;
                case 2:
                    Area area = new Area(); 
                    char activeCreatureSymbol = 'A';
                    char enemyCreatureSymbol = 'E';
                    area.printArea(creatureList, activeCreatureSymbol, inventory, enemyCreatureSymbol);
                    break;
                case 3:
                    evolution.getCreatureInventory(); 
                    break;
                case 4:
                    System.out.println("Exiting the game. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
                        
           
        } while (true);

        
    }
    
 }

