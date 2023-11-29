import java.util.Random;
import java.util.Scanner;
/**
 * The 'Battlephase' class represents the battle phase of the game where the player's active creature battles against an enemy.
 */
public class Battlephase {
    private int enemyHP;
    private int actions;
    Scanner scanner = new Scanner(System.in);
    CreatureList creatureList = new CreatureList();
    //Inventory inventory = new Inventory(creatureList);
    private Creatures enemy; 
    
    /**
     * Constructs a new Battlephase object with default settings: enemy HP, actions, and an enemy creature.
     */

    public Battlephase() {
        enemyHP = 50;
        actions = 3;
        enemy = creatureList.getRandomEnemyCreatures();
    }
     /**
     * Prints the battle phase screen and handles player actions during a battle.
     *
     * @param inventory The player's inventory.
     */
    public void printBattlePhase(Inventory inventory) {

        System.out.println ("\nYou have encountered an enemy!!\n");
        System.out.println ("\tBATTLE PHASE\n");

        while (actions > 0 && enemyHP > 0) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");   
            System.out.println("USER's Active Creature: ");
            displayCreatureInfo(inventory.getActiveCreature());

            System.out.println ("\n\t--- VS ---");
        
            System.out.println("\nEnemy Creature: ");
            displayCreatureInfo(enemy);
            System.out.println("Enemy HP: " + enemyHP);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("What do you want to do?");
            System.out.println("Actions left: " + actions);
            System.out.println("[1] Attack");
            System.out.println("[2] Swap");
            System.out.println("[3] Catch");
            System.out.println("[4] Run away");
            System.out.println("INPUT: ");
            int input = scanner.nextInt();

            if (input == 1) {
                System.out.println (inventory.getActiveCreature().getName() + ", attacks the Enemy!");
                int damage = attack(inventory);
                if (damage > 0) {
                System.out.println ("Damage dealt: " + damage);
                }
                else {
                    System.out.println("Out of actions");
                }
                actions--;
            }
            else if (input == 2) {
                System.out.println ("\t\t\t\t\t Inventory ");
                swap(inventory);
                actions--;
            }
            else if (input == 3) {
                System.out.println("Attempting to catch enemy.");
                boolean success = catchChanceEnemy(inventory);
                if (success) {
                    System.out.println ("Successfully caught enemy!");
                    enemyHP = 0;    //  to break while loop
                }
                else {
                    System.out.println ("Catch failed.");
                    actions--;
                }
            }
            else if (input == 4) {
                System.out.println ("You ran away..");
                enemyHP = 0;
            }
        }       
    }
    /**
     * Displays information about a creature, including its name, type, and evolution level.
     *
     * @param creature The creature to display information for.
     */
    public void displayCreatureInfo(Creatures creature) {
        System.out.println("Name: " + creature.getName());
        System.out.println("Type: " + creature.getType());
        System.out.println("Level: " + creature.getEvoLevel());
    }

	/**
     * Performs an attack action during a battle.
     *
     * @param inventory The player's inventory.
     * @return The damage dealt to the enemy.
     */   

    public int attack(Inventory inventory) {
        if (actions > 0) {
            int damage = damageCalculator(inventory);
            enemyHP = enemyHP - damage;
            return damage;
        }
        return 0;
    }

    //  Swap Method
    /**
     * Allows the player to swap their active creature during a battle.
     *
     * @param inventory The player's inventory.
     */
    public void swap (Inventory inventory) {           //  NOTE: dont forget to incre decre actions
        if (actions > 0) {
            inventory.printInventory(); 
            inventory.ActiveCreatureChange();
        }
    }

    //  Catch Method
    /**
     * Attempts to catch the enemy creature during a battle.
     *
     * @param inventory The player's inventory.
     * @return `true` if the catch attempt is successful, `false` otherwise.
     */
    public boolean catchChanceEnemy(Inventory inventory){
        int catchChance = 40 + 50 - enemyHP;
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;
        if(randomNumber <= catchChance){
            System.out.println("You successfully caught the enemy!");
            inventory.addCreature(enemy);
            return true;
        } else {
            System.out.println("Failed to catch the enemy.");
            return false;    
        }
        
    }

    // Run away Method 
    /**
     * Checks if the player can run away from the battle.
     *
     * @return `true` if the player can run away, `false` otherwise.
     */
    public boolean RunAway () {     //  run away method 
        return enemyHP > 0 && actions == 0;
    }


    //  Damage Calculator 
    /**
     * Calculates the damage dealt by the active creature during an attack.
     *
     * @param inventory The player's inventory.
     * @return The calculated damage value.
     */
    public int damageCalculator (Inventory inventory) {
        Random random = new Random();
        int damage = random.nextInt(10) + 1 * inventory.getActiveCreature().getEvoLevel();
        
        if (stronger(inventory.getActiveCreature(), enemy)) {
            damage = (int)(damage * 1.5);
        }

        return damage;
    }

    /**
     * Determines if one creature is stronger than another based on their types.
     *
     * @param creature1 The first creature.
     * @param creature2 The second creature.
     * @return `true` if the first creature is stronger than the second, `false` otherwise.
     */
    public boolean stronger(Creatures creature1, Creatures creature2) {
        String type1 = creature1.getType();
        String type2 = creature2.getType();

        if (type1.equals("fire") && type2.equals("grass")) {
            return true; 
        } 
        else if (type1.equals("grass") && type2.equals("water")) {
            return true;
        } 
        else if (type1.equals("water") && type2.equals("fire")) {
            return true; 
        }
        else {
            return false;
        }
    }

    public Creatures getEnemy () {
        return enemy;
    }

    public int getEnemyHP () {
        return enemyHP;
    }

    public int getActions () {
        return actions;
    }

    public String getEnemyImagePath () {
        return enemy.getImagePath();
    }

    public void updateEnemy() {
        enemy = creatureList.getRandomEnemyCreatures(); // Update enemy details accordingly
        enemyHP = 50; // Reset enemy HP or update as needed
    }

    
}
