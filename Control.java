package sample;

import javafx.application.Platform;
import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Control
{
    public Control()
    {

    }

    public static void setNewSearch(String search)
    {
        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                try {
                    String inputLine, jsonString = "", url = "", newSearch = "";
                    int responseCode;
                    JSONParser jsonParser;

                    url += "http://www.omdbapi.com/?t=";
                    newSearch = replaceSpacesWithPlus(search);
                    url += newSearch;
                    url += "&y=&plot=short&r=json";

                    // openning the connection
                    URL obj = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                    // optional default is GET
                    con.setRequestMethod("GET");

                    //add request header
                    con.setRequestProperty("User-Agent", "Mozilla/5.0");

                    // get the response code
                    responseCode = con.getResponseCode();

                    // if there is some mistake
                    if (responseCode != 200) {
                        Main.statusBar.setText("ERROR: Can't connect to the database");
                        return;
                    }
                    // reading the json

                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    while ((inputLine = in.readLine()) != null)
                        jsonString += inputLine;

                    in.close();

                    jsonParser = new JSONParser(jsonString);

                    if (jsonParser.containsField("Error"))
                        Main.statusBar.setText("Movie not found!");
                    else
                        setInfo(jsonParser);
                }
                catch(Exception e) {}
            }
        });
    }

    private static String replaceSpacesWithPlus(String str)
    {
        String ret = "";

        for(int i = 0; i < str.length(); i++)
        {
            if(str.charAt(i) == ' ')
                ret += '+';
            else
                ret += str.charAt(i);
        }

        return ret;
    }

    // setting the info that is in the json file in the correct fields
    public static void setInfo(JSONParser jsonParser)
    {
        String str = "";
        String plot = "";

        // updating the fields
        CenterLayout.poster.setImage(new Image(addColonAfterHTTP(jsonParser.getField("Poster")), 150, 200, false, false));
        CenterLayout.titleLabel.setText("Title: " + jsonParser.getField("Title"));
        CenterLayout.yearLabel.setText("Year: " + jsonParser.getField("Year"));
        CenterLayout.genreLabel.setText("Genre: " + jsonParser.getField("Genre"));
        CenterLayout.actorsLabel.setText("Actors: " + jsonParser.getField("Actors"));


        // adding new lines where it needed
        str = jsonParser.getField("Plot");
        for(int i = 0; i < str.length(); i++)
        {
            plot += str.charAt(i);

            if(str.charAt(i) == ',' || str.charAt(i) == '.' && i != str.length()-1) // adding new line every comma and a dot
                plot += '\n';
        }

        CenterLayout.plotLabel.setText("Plot: " + plot);

        Main.statusBar.setText("Movie found!");
    }

    private static String addColonAfterHTTP(String str)
    {
        String ret = "";

        for (int i = 0; i < str.length(); i++)
        {
            if(i == 4)
                ret += ':';
            ret += str.charAt(i);
        }

        return ret;
    }
}
