package Model;

/**
 * Created by dee on 2016-09-26.
 */



public class SensorModel extends DeviceModel {
    public String temperature;
    public String pressure;
    public String humidity;
    public String magnetmoeter;
    public String accelorometer;
    public String gyroscope;

    public SensorModel(String id, String name, String deviceAddress, String status){
        super(id,name,deviceAddress,status);
    }

}
