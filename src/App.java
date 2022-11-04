import State.KhoaState;
import UI.Home;

public class App {
    public static void main(String[] args) throws Exception {
        new Home();
        KhoaState khoaState = new KhoaState();
        khoaState.view();

    }
}
