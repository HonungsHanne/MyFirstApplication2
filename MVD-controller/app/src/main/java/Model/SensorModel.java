package Model;

/**
 * Created by dee on 2016-09-26.
 */



public class SensorModel extends DeviceModel {
    String temperature;
    String pressure;
    String humidity;
    String magnetmoeterX;
    String magnetmoeterY;
    String magnetmoeterZ;
    String accelorometerX;
    String accelorometerY;
    String accelorometerZ;
    String gyroscopeX;
    String gyroscopeY;
    String gyroscopeZ;

    public SensorModel(String id, String name, String deviceAddress, String status){
        super(id,name,deviceAddress,status);
    }


}
