package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application
{
    public static Label statusBar;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Scene scene;
        BorderPane layout = new BorderPane();

        layout.setStyle("-fx-background-color: #E8E8E8"); // set a gray-ish background color

        // status bar
        statusBar = new Label("");
        statusBar.setAlignment(Pos.BOTTOM_CENTER);
        layout.setMargin(statusBar, new Insets(0, 0, 10, 10)); // setting padding
        layout.setBottom(statusBar);

        // the top layout taken from the TopLayout class
        TopLayout topLayout = new TopLayout();
        layout.setTop(topLayout.getLayout());

        // the center layout taken from the CenterLayout class
        CenterLayout centerLayout = new CenterLayout();
        layout.setCenter(centerLayout.getLayout());

        scene = new Scene(layout, 660, 595);
        primaryStage.setTitle("Movie Information");
        primaryStage.setResizable(false); // you cant resize it
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
