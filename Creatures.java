/**
 * The 'Creatures' class represents individual creatures in the game.
 */
public class Creatures {
    private String strName;
    private String strType;
    private String strFamily;
    private int intEvoLevel;
    private String imagePath;
    

     /**
     * Constructs a default 'Creatures' object with an evolution level of 1.
     */
    public Creatures () {
        intEvoLevel = 1;
    }
    
    /**
     * Constructs a 'Creatures' object with specified attributes.
     *
     * @param strName     The name of the creature.
     * @param strType     The type of the creature (e.g., Fire, Grass, Water).
     * @param strFamily   The family of the creature (A,B,C,D,E,F,G,H,I).
     * @param intEvoLevel The evolution level of the creature (1,2,3).
     */
    public Creatures(String strName, String strType, String strFamily, int intEvoLevel, String imagePath){
        this.strName = strName;
        this.strType = strType;
        this.strFamily = strFamily;
        this.intEvoLevel = intEvoLevel;
        this.imagePath = imagePath;
    }

     /**
     * Gets the name of the creature.
     *
     * @return The name of the creature.
     */

    public String getName() {
        return strName;
    }

    /**
     * Gets the type of the creature.
     *
     * @return The type of the creature (e.g., Fire, Grass, Water).
     */

    public String getType() {
        return strType;
    }
    
    /**
     * Gets the family of the creature.
     *
     * @return The family of the creature.
     */
    public String getFamily() {
        return strFamily;
    }

    /**
     * Gets the evolution level of the creature.
     *
     * @return The evolution level of the creature.
     */
    
    public int getEvoLevel() {
        return intEvoLevel;
    }

    public String getImagePath() {
        return imagePath;
    }

}