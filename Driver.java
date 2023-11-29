public class Driver {
/*public static void main(String[] args) {
    Application application = new Application();
    application.run();
}
*/ 

public static void main(String[] args) {
    CreatureList creatureList = new CreatureList();
    Inventory inventory = new Inventory(creatureList);

    startGUI start = new startGUI(inventory, creatureList);
    start.run(); 
}
}