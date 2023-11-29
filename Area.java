import java.util.Random;
import java.util.Scanner;
/**
 * The 'Area' class represents an area in the game arena.
 * It allows the player to explore different areas such as AREA 1, AREA 2, AREA 3
 */
public class Area {
    private Random random = new Random();
    int intArea; 
    CreatureList creatureList = new CreatureList();
    Battlephase battlephase = new Battlephase(); 
   
    public boolean fortyPEncounter() { // 40% chance
      return random.nextDouble() <= 0.4;
  }
/**
 * Print the details and actions available in a specific game area.
 *
 * @param Creatures          The list of available creatures in the game.
 * @param activeCreatureSymbol The symbol representing the active creature.
 * @param inventory           The player's inventory.
 * @param enemyCreatureSymbol The symbol representing enemy creatures.
 */
    public void printArea(CreatureList Creatures, char activeCreatureSymbol, Inventory inventory, char enemyCreatureSymbol){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the ARENA!");
        System.out.println("[1] AREA 1");
        System.out.println("[2] AREA 2");
        System.out.println("[3] AREA 3");
        System.out.print("Choose an Area: ");

        int intArea = scanner.nextInt(); 
        
        if(intArea <= 3 && intArea >= 1){
            this.intArea = intArea; 
          
          if(intArea == 1){
            //area

            if(inventory.getActiveCreature().getEvoLevel() == 1){ // only accepts evo lvl 1 for arena 1
              char[][] grid = new char[1][5];
              System.out.println ("ACTIVE CREATURE IS " + inventory.getActiveCreature().getName()); 
              inventory.setActiveCreatureSymbol(activeCreatureSymbol);

              // Place the active creature at (0, 0) in the grid
              grid[0][0] = activeCreatureSymbol;
              int curPos = 0;

                while (true) {
                  // Display the board
                  System.out.print("|");
                  for (int i = 0; i < 5; i++) {
                      System.out.print(grid[0][i] + "|");
                  }
                  System.out.println("");
  
                  // Ask the player for movement input
                  System.out.print("Enter 'L' to move left, 'R' to move right, or 'Q' to quit: ");
                  String move = scanner.next().toUpperCase();
                  
                  if (move.equals("L")) {
                      // Move the active creature left
                      if (curPos > 0) {
                          grid[0][curPos] = ' '; // Clear the current position
                          curPos--;
                          grid[0][curPos] = activeCreatureSymbol; // Set the new position
                          if(fortyPEncounter()){
                            System.out.println("its true 40 left check");
                            Battlephase battlephase = new Battlephase();
                            battlephase.printBattlePhase(inventory);

                          }
                          
                      } else {
                          System.out.println("Cannot move left. You are at the leftmost position.");
                      }
                  } else if (move.equals("R")) {
                      // Move the active creature right
                      if (curPos < 5 - 1) {
                          grid[0][curPos] = ' '; // Clear the current position
                          curPos++;
                          grid[0][curPos] = activeCreatureSymbol; // Set the new position
                          if(fortyPEncounter()){
                            System.out.println("its true 40 right check");
                            Battlephase battlephase = new Battlephase();
                            battlephase.printBattlePhase(inventory);

                          }
                      } else {
                          System.out.println("Cannot move right. You are at the rightmost position.");
                      }
                  } else if (move.equals("Q")) {
                      // Quit the area 
                      break;
                  } else {
                      System.out.println("Invalid input. Please enter 'L', 'R', or 'Q'.");
                  }
              }
            } else {
              System.out.println("Active Creature must only be Evolution level 1!");
            }
            

            
            
         }
          else if(intArea == 2){
            // area 2
          } 
          else {
            // area 3
          }
        } 

        else {
        System.out.println("Error please input a valid number!");
        }

        
    }

}
