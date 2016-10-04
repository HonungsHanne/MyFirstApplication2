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
 * Class that facilitates the communication between the backend and the deviceModels.
 */
public class ConnectionHandler {

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
            String strJSON="";
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
                        conn.setRequestProperty("User-Agent","Chrome");
                        InputStream is = new BufferedInputStream(conn.getInputStream());
                        //test
                        text = getASCIIContentFromEntity(is);
                        // Removes all brackets and curlybrackets from start and end of string.
                        if(text.length()>3){ strJSON = text.substring(2, text.length() - 2);}
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
                        rootObject = new JSONObject();
                        rootObject.put("deviceAddress", id);
                        rootObject.put("value", value);
                        conn.setDoOutput(true);
                        System.out.println(url);
                        System.out.println(rootObject);
                        OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                        out.write(rootObject.toString());
                        out.flush();
                        out.close();
                        conn.getResponseCode();
                        break;
                    default:
                        throw new IllegalArgumentException("Wrong HTTP method TYPE");
                }


            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException p) {
                p.printStackTrace();
            }
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

    /**
     *
     * @param m SensorModel only contains id, before firt call of respective methods in
     *          connectionhandler.
     *          Method updates the model with curren temperature from sensor.
     *
     */
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

    /**
     *
     * @param m SensorModel only contains id, before first call of respective methods in
     *          connectionhandler.
     *          Method updates the model with current pressure from sensor.
     *
     */
    public void updatePressure(SensorModel m) {
        try {
            IOConnection c = new IOConnection("GET", "data/device", m.id, "pressure", "");
            c.execute();
            ArrayList<JSONObject> list = c.get(10000, TimeUnit.MILLISECONDS);
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

    /**
     *
     * @param m SensorModel only contains id, before first call of respective methods in
     *          connectionhandler.
     *          Method updates the model with current humidity from sensor.
     *
     */
    public void updateHumidity(SensorModel m) {
        try {
            IOConnection c = new IOConnection("GET", "data/device", m.id, "humidity", "");
            c.execute();
            ArrayList<JSONObject> list = c.get(10000, TimeUnit.MILLISECONDS);
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

    /**
     *
     * @param m SensorModel only contains id, before first call of respective methods in
     *          connectionhandler.
     *          Method updates the model with current value of the magentic values from sensor.
     *
     */
    public void updateMagnometer(SensorModel m) {
        try {
            IOConnection c = new IOConnection("GET", "data/device", m.id, "magnometer", "");
            c.execute();
            ArrayList<JSONObject> list = c.get(10000, TimeUnit.MILLISECONDS);
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

    /**
     *
     * @param m SensorModel only contains id, before first call of respective methods in
     *          connectionhandler.
     *          Method updates the model with current accelerometer from sensor.
     *
     */
    public void updateAccelerometer(SensorModel m) {
        try {
            IOConnection c = new IOConnection("GET", "data/device", m.id, "accelerometer", "");
            c.execute();
            ArrayList<JSONObject> list = c.get(10000, TimeUnit.MILLISECONDS);
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

    /**
     *
     * @param m SensorModel only contains id, before first call of respective methods in
     *          connectionhandler.
     *          Method updates the model with current gyroscopical values from sensor.
     *
     */
    public void updateGyroscope(SensorModel m) {
        try {
            IOConnection c = new IOConnection("GET", "data/device", m.id, "gyroscope", "");
            c.execute();
            ArrayList<JSONObject> list = c.get(10000, TimeUnit.MILLISECONDS);
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

    /**
     *
     * @param m LightbulbModel only contains id, before first call of respective methods in
     *          connectionhandler.
     *          Method updates the model with the current color values from the lightbulb.
     *
     */
    public void updateColor(LightbulbModel m) {
        try {
            IOConnection c = new IOConnection("GET", "data/device", m.id, "color", "");
            c.execute();
            ArrayList<JSONObject> list = c.get(10000, TimeUnit.MILLISECONDS);
            //fick denna i mergen, denna borde va den som stämmer
            JSONObject json = list.get(list.size()-1);
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

    /**
     *
     * @param m LightbulbModel only contains id, before first call of respective methods in
     *          connectionhandler.
     *          Method sends the chosen color values from model to backend in order
     *          to update the color of the lamp.
     *
     */
    public void setColor(LightbulbModel m) {
            IOConnection c = new IOConnection("PUT", "device/value", m.deviceAdress, "", m.color);
            c.execute();

    }


    /**
     * This method queries the server and retrieves all active devices from backend.
     *
     *
     * @return List of devicemodels with the correct values set from backend.
     */
    public List<DeviceModel> getDevices() {
        ArrayList<DeviceModel> l = new ArrayList<DeviceModel>();
        try {
            IOConnection c = new IOConnection("GET", "device", "", "", "");
            c.execute();
            ArrayList<JSONObject> list = c.get(10000, TimeUnit.MILLISECONDS);
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

    /**
     *
     * @param model DeviceModel superClass to all other models.
     * @param power Value of zero or one that is sent to the backend to represent status of device.
     */
    public void updateStatus(DeviceModel model, String power) {
        new IOConnection("PUT","device/status",model.deviceAdress,"",power).execute();
    }
}
