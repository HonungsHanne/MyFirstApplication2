package Connection;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;



/**
 * Created by dee on 2016-09-26.
 */

public class ConnectionHandler extends AsyncTask<Void,Void,String>{
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
        System.out.println(out.toString());
        return out.toString();
    }

    @Override
    protected String doInBackground(Void... params) {
        HttpURLConnection conn=null;

        String text="";
        try {

            conn=(HttpURLConnection)(new URL("http://vm39.cs.lth.se:9000/device").openConnection());
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", USER_AGENT);
            InputStream is=new BufferedInputStream(conn.getInputStream());
            //test
            text = getASCIIContentFromEntity(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }

    //This will be run along with doInback. Could be used to update a progressbar
    protected void onProgressUpdate(){

    }

    //This will be executed when doInbackground is done
    protected void onPostExecute(String result){

    }
}
