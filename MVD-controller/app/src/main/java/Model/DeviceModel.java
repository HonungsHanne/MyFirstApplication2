package Model;

import java.io.Serializable;

/**
 * Created by dee on 2016-09-26.
 */

public class DeviceModel implements Serializable {
    public String id;
    public String name;
    public String deviceAdress;
    public String status;


    public DeviceModel(String id, String name, String deviceAdress, String status){
        this.id=id;
        this.name=name;
        this.deviceAdress=deviceAdress;
        this.status=status;
    }

    public String id() {
        return id;
    }

    public void setDeviceId(String deviceID) {
        this.id = deviceID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceAdress() {
        return deviceAdress;
    }

    public void setAddress(String address) {
        this.deviceAdress = address;
    }

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *Print method which print device info according to
     * requirement 6.1.3
     */
    public String toString(){
        return id + " " + name + " " + deviceAdress;
    }
}
