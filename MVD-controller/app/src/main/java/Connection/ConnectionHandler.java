package Connection;

import android.os.AsyncTask;
import android.widget.Switch;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by dee on 2016-09-26.
 */

public class ConnectionHandler {
    public static final String USER_AGENT = "Chrome";

    private class IOConnection extends AsyncTask<Void, Void, ArrayList> {
        private String type = "";
        private String command = "";
        private int id = 0;
        private String sensorType = "";
        private String url = "http://vm39.cs.lth.se:9000/";
        private String value;

        private IOConnection(String type, String command, int id, String sensorType, String value) {
            this.type = type;
            this.command = command;
            this.id = id;
            this.sensorType = sensorType;
            url += command;
            this.value = value;
            if(id != -1 && type.equals("PUT")){
                url += "/" + id;
            }
            if(!sensorType.isEmpty()) {
                url += "/" + sensorType;
            }


        }

        //will run before doInBackground. Could be used to setup a thinking icon
        protected void onPreExecute() {

        }

        protected String getASCIIContentFromEntity(InputStream entity) throws IllegalStateException, IOException {
            InputStream in = entity;

            StringBuffer out = new StringBuffer();
            int n = 1;
            while (n > 0) {
                byte[] b = new byte[4096];
                n = in.read(b);


                if (n > 0) out.append(new String(b, 0, n));
            }
            return out.toString();
        }

        @Override
        protected ArrayList<JSONObject> doInBackground(Void... params) {
            HttpURLConnection conn = null;
            JSONObject rootObject = null;
            String strJSON;
            String text = "";
            String text2 = "";
            String json = "{";
            String jsonEnd = "}";
            ArrayList<JSONObject> jsonArray = new ArrayList<JSONObject>();
            try {

                conn = (HttpURLConnection) (new URL(url).openConnection());
                conn.setRequestMethod(type);
                conn.setRequestProperty("User-Agent", USER_AGENT);
                switch (type){
                    case "GET":

                        InputStream is = new BufferedInputStream(conn.getInputStream());
                        //test
                        text = getASCIIContentFromEntity(is);
                        // Removes all brackets and curlybrackets from start and end of string.
                        strJSON = text.substring(2, text.length() - 2);
                        // Split string into seperate JSON objects
                        String[] jsonFileArray = strJSON.split(Pattern.quote("},{"));
                        for (String s : jsonFileArray) {
                            //Construct our "new" JSON objects.
                            s = "{" + s + "}";
                            rootObject = new JSONObject(s);
                            jsonArray.add(rootObject);
                        }
                        break;
                    case "PUT":
                        rootObject.put("deviceAddress", String.valueOf(id));
                        rootObject.put("value",value);
                        conn.setDoOutput(true);
                        OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                        out.write(rootObject.toString());
                        out.close();
                        break;
                    default:
                        throw new IllegalArgumentException("Wrong HTTP method TYPE");
                }


            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException p) {
                p.printStackTrace();
            }
            //  System.out.println(text2);
            return jsonArray;
        }

        //This will be run along with doInback. Could be used to update a progressbar
        protected void onProgressUpdate() {

        }

        //This will be executed when doInbackground is done
        protected void onPostExecute(ArrayList<JSONObject> jsonArray) {
            
        }
    }
}
