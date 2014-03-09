import Menu.Menu;
import Synchronisation.Synchronizer;


public class Main {

	public static void main(String[] args) {
		Synchronizer sync = new Synchronizer();
		final Menu menu = new Menu(sync);
		sync.initialiseBook(System.getProperty("user.dir") + "//Texte1.txt", 30);
	}
}
