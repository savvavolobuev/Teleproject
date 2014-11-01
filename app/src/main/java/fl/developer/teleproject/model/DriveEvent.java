package fl.developer.teleproject.model;

import java.io.Serializable;

/**
 * Created by alexk on 01.11.2014.
 */
public class DriveEvent implements Serializable {
    private final String address;

    public DriveEvent(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
