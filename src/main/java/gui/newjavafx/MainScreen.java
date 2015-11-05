package gui.newjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;

/**
 * Created by Admin on 06.08.15.
 */
@SuppressWarnings("all")
public class MainScreen extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private static Stage stage;
    private Button btn;

    @Override
    public void start(Stage stage) throws Exception {
        String mainFxml = "/main.fxml";
        //File file = new File(mainFxml);
        //System.out.println(file.getAbsolutePath());
        //System.out.println(file.exists());
        URL url = getClass().getResource(mainFxml);
        //System.out.println(url.toString());

        btn = new Button();

        Parent root = FXMLLoader.load(getClass().getResource(mainFxml));
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 1900 / 2, 1000));
        stage.setMaximized(true);
        this.stage = stage;
        stage.show();
    }

    static Stage getStage() {
        return stage;
    }

}
