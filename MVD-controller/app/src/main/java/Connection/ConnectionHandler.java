package Connection;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;

import Model.DeviceModel;
import Model.LightbulbModel;
import Model.SensorModel;


/**
 * Created by dee on 2016-09-26.
 */

public class ConnectionHandler {
    public static final String USER_AGENT = "Chrome";

    private class IOConnection extends AsyncTask<Void, Void, ArrayList> {
        private String type;
        private String command;
        private String id;
        private String sensorType;
        private String url = "http://vm39.cs.lth.se:9000/";
        private String value;
        private ArrayList<JSONObject> ret;

        private IOConnection(String type, String command, String id, String sensorType, String value) {
            this.type = type;
            this.command = command;
            this.id = id;
            this.sensorType = sensorType;
            url += command;
            this.value = value;
            ret = null;
            if(!id.isEmpty() && !type.equals("PUT")){
                url += "/" + id;
            }
            if(!sensorType.isEmpty()) {
                url += "/" + sensorType;
            }

            System.out.println(url);
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
            System.out.println(out);
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
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Accept", "application/json");
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
                        conn.setRequestProperty("Content-Type","application/json");
                        rootObject = new JSONObject();
                        rootObject.put("deviceAddress", id);
                        rootObject.put("value", value);
                        conn.setDoOutput(true);
                        OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                        out.write(rootObject.toString());
                        out.flush();
                        out.close();

                        System.out.println(conn.getResponseCode());
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
            ret = jsonArray;
        }


    }

    public void updateTemp(SensorModel m) {
        try {
            IOConnection c = new IOConnection("GET", "data/device", m.id, "temperature", "");
            c.execute();
            ArrayList<JSONObject> list = c.get(5000, TimeUnit.MILLISECONDS);
            JSONObject json = list.get(0);
            m.temperature = (String) json.get("value");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public void updatePressure(SensorModel m) {
        try {
            IOConnection c = new IOConnection("GET", "data/device", m.id, "pressure", "");
            c.execute();
            ArrayList<JSONObject> list = c.get(5000, TimeUnit.MILLISECONDS);
            JSONObject json = list.get(0);
            m.pressure = (String) json.get("value");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public void updateHumidity(SensorModel m) {
        try {
            IOConnection c = new IOConnection("GET", "data/device", m.id, "humidity", "");
            c.execute();
            ArrayList<JSONObject> list = c.get(5000, TimeUnit.MILLISECONDS);
            JSONObject json = list.get(0);
            m.humidity = (String) json.get("value");
        } catch(JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public void updateMagnometer(SensorModel m) {
        try {
            IOConnection c = new IOConnection("GET", "data/device", m.id, "magnometer", "");
            c.execute();
            ArrayList<JSONObject> list = c.get(5000, TimeUnit.MILLISECONDS);
            JSONObject json = list.get(0);
            m.magnometer = (String) json.get("value");
        } catch(JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public void updateAccelerometer(SensorModel m) {
        try {
            IOConnection c = new IOConnection("GET", "data/device", m.id, "accelerometer", "");
            c.execute();
            ArrayList<JSONObject> list = c.get(5000, TimeUnit.MILLISECONDS);
            JSONObject json = list.get(0);
            m.accelorometer = (String) json.get("value");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public void updateGyroscope(SensorModel m) {
        try {
            IOConnection c = new IOConnection("GET", "data/device", m.id, "gyroscope", "");
            c.execute();
            ArrayList<JSONObject> list = c.get(5000, TimeUnit.MILLISECONDS);
            JSONObject json = list.get(0);
            m.gyroscope = (String) json.get("value");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public void updateColor(LightbulbModel m) {
        try {
            IOConnection c = new IOConnection("GET", "data/device", m.id, "color", "");
            c.execute();
            ArrayList<JSONObject> list = c.get(5000, TimeUnit.MILLISECONDS);
            JSONObject json = list.get(0);
            m.color = (String) json.get("value");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
    public void setColor(LightbulbModel lightBulb) {
            IOConnection c = new IOConnection("PUT", "device/value", lightBulb.deviceAdress, "", lightBulb.color);
            c.execute();

    }


    public List<DeviceModel> getDevices() {
        ArrayList<DeviceModel> l = new ArrayList<DeviceModel>();
        try {
            IOConnection c = new IOConnection("GET", "device", "", "", "");
            c.execute();
            ArrayList<JSONObject> list = c.get(5000, TimeUnit.MILLISECONDS);
            for (JSONObject json : list) {
                if(json.get("name").equals("Nexturn")) {
                    l.add((DeviceModel) new LightbulbModel(json.getString("id"), json.getString("name"), json.getString("deviceAddress"), json.getString("status")));
                } else if(json.get("name").equals("WICED Sense2 Kit")) {
                    l.add((DeviceModel) new SensorModel(json.getString("id"), json.getString("name"), json.getString("deviceAddress"), json.getString("status")));
                }
            }
        } catch(JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        return l;
    }

    public void updateStatus(DeviceModel model, String power) {
        new IOConnection("PUT","device/status",model.deviceAdress,"",power).execute();
    }
}
