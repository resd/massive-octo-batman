package gui.newjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Admin
 * @since 06.08.15
 */
public class MainScreen extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        String mainFxml = "/main.fxml";
        //File file = new File(mainFxml);
        //System.out.println(file.getAbsolutePath());
        //System.out.println(file.exists());
//        URL url = getClass().getResource(mainFxml);
        //System.out.println(url.toString());

        Parent root = FXMLLoader.load(getClass().getResource(mainFxml));
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 1900 / 2, 1000));
        stage.setMaximized(true);
        MainScreen.stage = stage;
        stage.show();
    }

    static Stage getStage() {
        return stage;
    }

}
