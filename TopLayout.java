package sample;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class TopLayout
{
    private HBox layout;
    TextField searchField;

    public TopLayout()
    {
        layout = new HBox();

        // search label
        Label searchLabel = new Label("Search: ");
        searchLabel.setAlignment(Pos.TOP_CENTER); // set alignment to top left
        layout.setMargin(searchLabel, new Insets(23, 10, 0, 200)); // add margin(padding) to the edges

        // search text field
        searchField = new TextField();
        searchField.setAlignment(Pos.TOP_CENTER);
        searchField.setPrefSize(200, 10); // set the size of the field
        layout.setMargin(searchField, new Insets(20, 0, 0, 0));

        searchField.setOnAction(e -> justClicked());

        layout.getChildren().addAll(searchLabel, searchField); // adding the objects
    }

    // set status bar to "searching" and then make the other operation
    public void justClicked()
    {
        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                Main.statusBar.setText("Searching...");
            }
        });

        enterClicked();
    }

    // the other operation
    public void enterClicked()
    {
        try {
            Control.setNewSearch(searchField.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HBox getLayout()
    {
        return layout;
    }
}
