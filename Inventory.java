import java.util.ArrayList;
import java.util.Scanner;

/**
 * The 'Inventory' class represents the player's inventory of creatures and the active creature.
 */

public class Inventory {
    private ArrayList<Creatures> ownedCreatures = new ArrayList<>(); 
    private Creatures activeCreature;       
    private CreatureList creatureList;
    private char activeCreatureSymbol;
    public Object getActiveCreature;

    /**
     * Gets the list of owned creatures in the inventory.
     *
     * @return The list of owned creatures.
     */
    public ArrayList<Creatures> getOwnedCreature() {    //  setter for Arraylist 'ownedCreatures'
        return ownedCreatures;                          
    }
    /**
     * Gets the number of creatures in the inventory.
     *
     * @return The number of creatures in the inventory.
     */
    public int getArrayListSize() {
        return ownedCreatures.size();
    }
     /**
     * Adds a creature to the inventory.
     *
     * @param creature The creature to add to the inventory.
     */
    public void addCreature (Creatures creature) {      //  method for adding a creature in the arraylist 'ownedCreatures'
        ownedCreatures.add(creature);                   //  arraylist add command 
    }

    /**
     * Removes a creature from the inventory.
     *
     * @param creature The creature to remove from the inventory.
     */
    public void removeCreature (Creatures creature) {   //  method for removing a creature in the arraylist 'ownedCreatures'
        ownedCreatures.remove(creature);                //  arraylist remove command
    }
     /**
     * Sets the active creature in the inventory.
     *
     * @param index The index of the creature to set as the active creature.
     */
    public void setActiveCreature (int index) {      //  setter for activeCreature
        this.activeCreature = ownedCreatures.get(index);
    }
     /**
     * Gets the active creature in the inventory.
     *
     * @return The active creature.
     */

    public Creatures getActiveCreature() {      //  getter for activeCreature
        return activeCreature;
    }

    /**
     * Constructs an `Inventory` object with the specified `CreatureList`.
     *
     * @param creatureList The `CreatureList` containing available creatures.
     */

    public Inventory(CreatureList creatureList) {       //  setter for inventory 
            this.creatureList = creatureList;
        }
    
    /**
     * Gets the owned creature at the specified index.
     *
     * @param index The index of the owned creature to retrieve.
     * @return The owned creature at the specified index.
     */
    public Creatures getOwnedCreature (int index) {
        return ownedCreatures.get(index);
    }
    
     /**
     * Prints the name of the owned creature at the specified position in the inventory.
     *
     * @param pos The position of the creature in the inventory.
     */

    void printCreatureName (int pos) {
        if (pos >= 0 && pos < ownedCreatures.size()) {
            Creatures creature = ownedCreatures.get(pos);
            System.out.println ("Owned Creature Name at Index " + pos + ": " + creature.getName());
        }
        else {
            System.out.println ("Invalid index or no creature at the specific index.");
        }
    }

    /**
     * Prints the details of creatures in the inventory.
     */
    public void printInventory () {
        int i;
        for (i = 0; i < ownedCreatures.size(); i++) { 
            Creatures owned = ownedCreatures.get(i);
            int temp = i;
            System.out.println ("       +-------------+");
            System.out.println ("~~~~~~~| Creature  " + (temp + 1) + " |~~~~~~~");
            System.out.println ("       +-------------+");
            System.out.println ("Name: " + owned.getName());
            System.out.println ("Type: " + owned.getType());
            System.out.println ("Family: " + owned.getFamily());
            System.out.println ("Level: " + owned.getEvoLevel());
            System.out.println ("+-------------+-------------+");
        }
        System.out.println();
    }

    /**
     * Allows the player to change the active creature in the inventory.
     */
    void ActiveCreatureChange () {
        int size = ownedCreatures.size();
        boolean valid = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println(activeCreature.getName() + " is currently your active Creature.");
        System.out.println("Do you wish to change your Active Creature? [y/n]");
        String input = scanner.next();

        if (input.equalsIgnoreCase("Y")) {
            System.out.println("What is the position of the pokemon to set as your active Creautre? ");
            System.out.println("Press [0] to cancel.");
                while (!valid) {
                    if (scanner.hasNextInt()) {
                        int temp = scanner.nextInt();
                        if (temp == 0) {
                            System.out.println ("Active Creature change cancelled.");
                            break;
                        }
                        temp--;
                        if (temp >= 0 && temp < size) {
                            if (temp != ownedCreatures.indexOf(activeCreature)) {
                                setActiveCreature(temp);
                                System.out.println("Active Creature successfully changed to " + ownedCreatures.get(temp).getName());
                                valid = true;
                            } else {
                                System.out.println("The chosen creature is currently your Active Creature. Choose another one.");
                            }
                        } else {
                            System.out.println("Invalid Input. Please enter a valid position.");
                        }
                    } else {
                        System.out.println("Invalid Input. Please enter a valid position.");
                        scanner.next(); // Consume the invalid input
                    }
                }
        }
        else if (input.equalsIgnoreCase("N")) {
            System.out.println("Active Creature is still " + activeCreature.getName() + ".");
            valid = true;
        }
        else {
            System.out.println("Invalid input. Please enter a valid position.");
        }

        //scanner.close();
    }

    /**
     * Sets the symbol for the active creature.
     *
     * @param symbol The symbol to represent the active creature on the game board.
     */
    public void setActiveCreatureSymbol(char symbol) {
        this.activeCreatureSymbol = symbol;
    }

    /**
     * Gets the symbol for the active creature.
     *
     * @return The symbol representing the active creature.
     */
    public char getActiveCreatureSymbol() {
        return activeCreatureSymbol;
    }
        
}

//  setters and getters (public) are used so that declared variables can be private 