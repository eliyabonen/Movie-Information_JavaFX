package sample;

public class JSONParser
{
    private String jsonString;

    public JSONParser(String jsonString)
    {
        this.jsonString = jsonString;
    }

    public String getField(String field)
    {
        String value = "";
        int quotes = 0, count = 0, pos = 0;

        // finding the position when the value starts
        for(int i = 0; i < jsonString.length(); i++)
        {
            if(jsonString.charAt(i) == field.charAt(count))
                count++;
            else
                count = 0;

            if(count == field.length())
            {
                pos = i+2; // plus two because i don't want the ending quotes
                break;
            }
        }

        // grabbing the value from the position
        for(int i = pos; i < jsonString.length(); i++)
        {
            // to grab only the string value and not all the other crap
            if(jsonString.charAt(i) != ':')
            {
                // counting quotes
                if(jsonString.charAt(i) == '"')
                {
                    quotes++;
                    continue;
                }

                // if there is two quotes then i have read all the value content already
                if(quotes == 2)
                    break;

                value += jsonString.charAt(i);
            }
        }

        return value;
    }

    public boolean containsField(String field)
    {
        return (jsonString.contains(field));
    }
}
