package fl.developer.teleproject.model;

import java.io.Serializable;

/**
 * Created by alexk on 01.11.2014.
 */
public class DriveEvent implements Serializable {


    private String day;
    private String time;
    private String addressShort;
    private final String address;

    public DriveEvent(String address) {
        this.address = address;
    }

    public DriveEvent(String address, String addressShort, String time, String day) {
        this.address = address;
        this.addressShort = addressShort;
        this.time = time;
        this.day = day;
    }

    public String getAddress() {
        return address;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public String getAddressShort() {
        return addressShort;
    }

}
