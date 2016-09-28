package Model;

/**
 * Created by Kevin on 2016-09-27.
 */

public class LightbulbModel extends DeviceModel {

    public String red;
    public String green;
    public String blue;
    public String white;
    public String color;



    public LightbulbModel(String deviceId, String name, String address, String status) {
        super(deviceId,name,address,status);

    }
}
