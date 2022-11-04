import State.ConfigDatabase;
import UI.Home;

public class App {
    public static void main(String[] args) throws Exception {
        new ConfigDatabase().ConnectDatabase();
        new Home();

    }
}
