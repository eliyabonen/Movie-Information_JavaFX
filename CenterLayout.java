package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class CenterLayout
{
    private VBox layout;
    public static Label titleLabel, yearLabel, genreLabel, actorsLabel, plotLabel;
    public static ImageView poster;

    public CenterLayout()
    {
        layout = new VBox();
        layout.setSpacing(0); // set spacing between every object to 20 pixels vertical space

        // poster
        poster = new ImageView();
        VBox.setMargin(poster, new Insets(20, 0, 10, 250));

        // title
        titleLabel = new Label("");
        titleLabel.setAlignment(Pos.CENTER_LEFT); // set alignment to center left
        layout.setMargin(titleLabel, new Insets(20, 10, 0, 10)); // add margin(padding) to the edges

        // year
        yearLabel = new Label("");
        yearLabel.setAlignment(Pos.CENTER_LEFT);
        layout.setMargin(yearLabel, new Insets(10, 10, 0, 10));

        // genre
        genreLabel = new Label("");
        genreLabel.setAlignment(Pos.CENTER_LEFT);
        layout.setMargin(genreLabel, new Insets(10, 10, 0, 10));

        // actors
        actorsLabel = new Label("");
        actorsLabel.setAlignment(Pos.CENTER_LEFT);
        layout.setMargin(actorsLabel, new Insets(10, 10, 0, 10));

        // plot
        plotLabel = new Label("");
        plotLabel.setAlignment(Pos.CENTER_LEFT);
        layout.setMargin(plotLabel, new Insets(10, 10, 0, 10));

        layout.getChildren().addAll(poster, titleLabel, yearLabel, genreLabel, actorsLabel, plotLabel);
    }

    public VBox getLayout()
    {
        return layout;
    }
}
