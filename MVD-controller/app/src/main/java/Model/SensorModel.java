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
    public String gyroscopeX;
    public String gyroscopeY;
    public String gyroscopeZ;

    public SensorModel(String id, String name, String deviceAddress, String status){
        super(id,name,deviceAddress,status);
    }

    public void setTemperature(String temp) {
        temperature = temp;
    }

    public void setPressure(String pre) {
        pressure = pre;
    }


}
