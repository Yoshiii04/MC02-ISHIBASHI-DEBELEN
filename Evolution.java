import java.util.ArrayList;
import java.util.Scanner;

public class Evolution {
    private Inventory inventory; 
    private ArrayList<Creatures> creatureList;
    private Creatures evolvedCreature; 

    public Evolution(Inventory inventory) {
        this.inventory = inventory; 
        this.creatureList = new ArrayList<>(); 
        this.evolvedCreature = null; 
    }

    public void getCreatureInventory(){
        Scanner scanner = new Scanner(System.in); 
        System.out.println("Enter the first creature's index:");
        int index1 = scanner.nextInt() - 1; // array

        System.out.println("Enter the second creature's index:");
        int index2 = scanner.nextInt() - 1; // array 

        nextEvolution(index1, index2);

        scanner.close();
    }
    public boolean nextEvolution(int creatureIndex1, int creatureIndex2) {
        Creatures selectedCreature1 = inventory.getOwnedCreature(creatureIndex1);
        Creatures selectedCreature2 = inventory.getOwnedCreature(creatureIndex2);
        
        if((selectedCreature1.getEvoLevel() == selectedCreature2.getEvoLevel()) && (selectedCreature1.getFamily() == selectedCreature2.getFamily())){
            if(selectedCreature1.getEvoLevel() == 1){ // evolution to 2 
                Creatures evolvedCreatureEvo2 = findCreature(selectedCreature1.getFamily(),2);

                inventory.removeCreature(selectedCreature1);
                inventory.removeCreature(selectedCreature2);
                inventory.addCreature(evolvedCreatureEvo2);

                this.evolvedCreature = evolvedCreatureEvo2;

                System.out.println("You have successfully evolved "+selectedCreature1.getName() +"to" +evolvedCreatureEvo2.getName());

            } else if (selectedCreature1.getEvoLevel() == 2){ // evolution to 3 
                Creatures evolvedCreatureEvo3 = findCreature(selectedCreature1.getFamily(),3);

                inventory.removeCreature(selectedCreature1);
                inventory.removeCreature(selectedCreature2);
                inventory.addCreature(evolvedCreatureEvo3);
                this.evolvedCreature = evolvedCreatureEvo3;

                System.out.println("You have successfully evolved "+selectedCreature1.getName() +"to" +evolvedCreatureEvo3.getName());
            } else {
                System.out.println("You cannot select an EL 3 creature!");
            }

            return true; 
        } else {
            System.out.println("Failed to Evolve");
            System.out.println("=====================================================");
            System.out.println("Tip: Creatures must be the same EL and Family!");
            return false; 
        }
    }

    public Creatures findCreature(String strFamily, int intEvoLevel){
        Creatures selectedCandidate; 

        for(int i = 0; i < creatureList.size(); i++){
            selectedCandidate = creatureList.get(i); 
            if(selectedCandidate.getFamily().equals(strFamily) && selectedCandidate.getEvoLevel() == intEvoLevel){
                return selectedCandidate; 
            }   
        }
        
        return null; 
    }

    public Creatures getEvolvedCreature() {
        return evolvedCreature;
    } 

    
}
