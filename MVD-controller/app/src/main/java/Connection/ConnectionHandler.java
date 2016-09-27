package Connection;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by dee on 2016-09-26.
 */

public class ConnectionHandler extends AsyncTask<Void,Void,ArrayList>{
    public static final String USER_AGENT = "Chrome";

    //will run before doInBackground. Could be used to setup a thinking icon
    protected void onPreExecute(){

    }

    protected String getASCIIContentFromEntity(InputStream entity) throws IllegalStateException, IOException {
        InputStream in = entity;

        StringBuffer out = new StringBuffer();
        int n = 1;
        while (n>0) {
            byte[] b = new byte[4096];
            n =  in.read(b);


            if (n>0) out.append(new String(b, 0, n));
        }
        //System.out.println(out.toString());
        return out.toString();
    }

    @Override
    protected ArrayList<JSONObject> doInBackground(Void... params) {
        HttpURLConnection conn=null;
        JSONObject rootObject = null;
        String strJSON;
        String text = "";
        String text2 = "";
        String json = "{";
        String jsonEnd = "}";
        ArrayList<JSONObject> jsonArray = new ArrayList<JSONObject>();
        try {

            conn=(HttpURLConnection)(new URL("http://vm39.cs.lth.se:9000/device").openConnection());
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", USER_AGENT);
            InputStream is= new BufferedInputStream(conn.getInputStream());
            //test
            text = getASCIIContentFromEntity(is);
            // Removes all brackets and curlybrackets from start and end of string.
            strJSON = text.substring(2, text.length() - 2);
            // Split string into seperate JSON objects
            String[] jsonFileArray = strJSON.split(Pattern.quote("},{"));
            for(String s : jsonFileArray){
                //Construct our "new" JSON objects.
                s = "{" + s + "}";
                rootObject = new JSONObject(s);
                jsonArray.add(rootObject);

                //text2 = rootObject.getString("id");
                //System.out.println(s + text2);
            }


        }catch (JSONException e) {
            e.printStackTrace();
        }catch(IOException p){
            p.printStackTrace();
        }
      //  System.out.println(text2);
        return jsonArray;
    }

    //This will be run along with doInback. Could be used to update a progressbar
    protected void onProgressUpdate(){

    }

    //This will be executed when doInbackground is done
    protected void onPostExecute(ArrayList<JSONObject> jsonArray){

    }
}
