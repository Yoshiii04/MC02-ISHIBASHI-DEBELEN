import java.util.ArrayList;
import java.util.Random;;
/**
 * The 'CreatureList' class represents a list of available creatures in the game.
 */
public class CreatureList {
    private ArrayList<Creatures> creatureList = new ArrayList<>();
    private char enemyCreatureSymbol;
    
     /**
     * Constructs a 'CreatureList' object and initializes it with a predefined list of creatures.
     */

    public CreatureList () {
        creatureList.add (new Creatures("Strawander", "Fire", "A", 1, "images/1Strawander.png"));
        creatureList.add (new Creatures("Strawleon", "Fire", "A", 2, "images/2Strawleon.png"));
        creatureList.add (new Creatures("Strawizard", "Fire", "A", 3, "images/3Strawizard.png"));
        
        creatureList.add (new Creatures("Chocowool", "Fire", "B", 1, "images/4Chocowool.png"));
        creatureList.add (new Creatures("Chocofluff", "Fire", "B", 2, "images/5Chocofluff.png"));
        creatureList.add (new Creatures("Candaros", "Fire", "B", 3, "images/6Candaros.png"));
    
        creatureList.add (new Creatures("Parfwit", "Fire", "C", 1, "images/7Parfwit.png"));
        creatureList.add (new Creatures("Parfure", "Fire", "C", 2, "images/8Parfure.png"));
        creatureList.add (new Creatures("Parfelure", "Fire", "C", 3, "images/9Parfelure.png"));
    
        creatureList.add (new Creatures("Brownisaur", "Grass", "D", 1, "images/10Brownisaur.png"));
        creatureList.add (new Creatures("Chocosaur", "Grass", "D", 2, "images/11Chocosaur.png"));
        creatureList.add (new Creatures("Fudgasaur", "Grass", "D", 3, "images/12Fudgasaur.png"));
    
        creatureList.add (new Creatures("Frubat", "Grass", "E", 1, "images/13Frubat.png"));
        creatureList.add (new Creatures("Golberry", "Grass", "E", 2, "images/14Golberry.png"));
        creatureList.add (new Creatures("Croberry", "Grass", "E", 3, "images/15Croberry.png"));
    
        creatureList.add (new Creatures("Malts", "Grass", "F", 1, "images/16Malts.png"));
        creatureList.add (new Creatures("Kirlicake", "Grass", "F", 2, "images/17Kirlicake.png"));
        creatureList.add (new Creatures("Velvevoir", "Grass", "F", 3, "images/18Velvevoir.png"));
    
        creatureList.add (new Creatures("Squirpie", "Water", "G", 1, "images/19Squirpie.png"));
        creatureList.add (new Creatures("Tartortle", "Water", "G", 2, "images/20Tartortle.png"));
        creatureList.add (new Creatures("Piestoise", "Water", "G", 3, "images/21Piestoise.png"));
    
        creatureList.add (new Creatures("Chocolite", "Water", "H", 1, "images/22Chocolite.png"));
        creatureList.add (new Creatures("Chocolish", "Water", "H", 2, "images/23Chocolish.png"));
        creatureList.add (new Creatures("Icesundae", "Water", "H", 3, "images/24Icesundae.png"));
    
        creatureList.add (new Creatures("Oshacone", "Water", "I", 1, "images/25Oshacone.png"));
        creatureList.add (new Creatures("Dewice", "Water", "I", 2, "images/26Dewice.png"));
        creatureList.add (new Creatures("Samurcone", "Water", "I", 3, "images/27Samurcone.png"));
    }

    /**
     * Gets a creature from the list based on its index.
     *
     * @param index The index of the creature to get. 
     * @return The specific creature at the specified index.
     */

    public Creatures getCreaturefromList(int index) {       //  getter for creatures based on their index
        return creatureList.get(index);
    }

     /**
     * Gets the name of a creature in the list based on its index.
     *
     * @param index The index of the creature to get.
     * @return The name of the creature.
     */

    public String getCreatureName (int index) {
        Creatures temp = getCreaturefromList(index);
        return temp.getName();  //  get name properly to fix in main printing starters 
    }

    /**
     * Gets a random enemy creature from the list that has an evolution level of 1.
     *
     * @return A random enemy creature with evolution level 1.
     */

    public Creatures getRandomEnemyCreatures() {
        
        ArrayList<Creatures> evoLevel1Creatures = new ArrayList<>(); //creates a list of evo 1 

        // Filter the creatures to find those with evolution level 1
        for (Creatures creature : creatureList) {
            if (creature.getEvoLevel() == 1) {
                evoLevel1Creatures.add(creature);
            }
        }

        Random random = new Random();
        int randomIndex = random.nextInt(evoLevel1Creatures.size());
        return evoLevel1Creatures.get(randomIndex);
    }

    /**
     * Sets the symbol for the enemy creature.
     *
     * @param symbol The symbol to represent the enemy creature on the game board.
     */
    public void setEnemyCreatureSymbol(char symbol) { // idk if needed toh lolll 
        this.enemyCreatureSymbol = symbol;
    }

     /**
     * Gets the symbol for the enemy creature.
     *
     * @return The symbol representing the enemy creature.
     */
    
    public char getEnemyCreatureSymbol() {
        return enemyCreatureSymbol;
    }
    

}