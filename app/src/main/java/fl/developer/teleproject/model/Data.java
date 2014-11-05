package fl.developer.teleproject.model;

import java.util.ArrayList;
import java.util.Collections;

import fl.developer.teleproject.R;

/**
 * Created by Katrina on 02.11.2014.
 */
public class Data {

    private static ArrayList<Category> categories;
    private static ArrayList<ArrayList<DriveEvent>> events;
    private static ArrayList<ArrayList<DriveEvent>> oldEvents;
    private static ArrayList<DriveEvent> mapEvents = new ArrayList<DriveEvent>();

    public static final int CATEGORY_CODE = 0;
    public static final String CATEGORY_CODE_TITLE = "category";
    public static final int EVENT_CODE = 1;
    public static final String EVENT_CODE_TITLE = "event";

    static {
        categories = initCategories();
        events = initEvents();
        oldEvents = new ArrayList<ArrayList<DriveEvent>>(events);
        Collections.shuffle(oldEvents);
    }

    public static ArrayList<Category> getCategories() {
        return categories;
    }

    public static ArrayList<ArrayList<DriveEvent>> getEvents() {
        return events;
    }

    public static ArrayList<ArrayList<DriveEvent>> getOldEvents() {
        return oldEvents;
    }

    private static ArrayList<Category> initCategories() {
        ArrayList<Category> categories = new ArrayList<Category>();
        Category category = new Category(R.drawable.revving, "Revving:", 5.5, 11.1);
        categories.add(category);
        category = new Category(R.drawable.idling, "Idling:", 41.0, 42.2);
        categories.add(category);
        category = new Category(R.drawable.acceleration, "Acceleration:", 12.8, 10.0);
        categories.add(category);
        category = new Category(R.drawable.breaking, "Breaking:", 1.3, 3.8);
        categories.add(category);
        category = new Category(R.drawable.speeding, "Over Speeding:", 66.0, 59.2);
        categories.add(category);
        category = new Category(R.drawable.fuel, "Fuel:", 22.2, 21.0);
        categories.add(category);
        return categories;
    }

    private static ArrayList<ArrayList<DriveEvent>> initEvents() {
        ArrayList<ArrayList<DriveEvent>> events = new ArrayList<ArrayList<DriveEvent>>();
        int id = 20;

        // Revving
        ArrayList<DriveEvent> categoryChildren = new ArrayList<DriveEvent>();
        DriveEvent event = new DriveEvent("10:10 near <b>5 Green Avenue, Apt. 3</b>", "5 Green Avenue, Apt. 3", "10:10", "Today",5.5, 0, false);
        categoryChildren.add(event);
        mapEvents.add(event);
        id++;
        events.add(categoryChildren);


        // Idling
        categoryChildren = new ArrayList<DriveEvent>();
        event = new DriveEvent("12:01 near <b>Woodhouse Rd</b>", "Woodhouse Rd", "12:01", "Today", 15.0,1, false);
        categoryChildren.add(event);
        mapEvents.add(event);
        id++;
        event = new DriveEvent("10:10 near <b>Bromton Rd, SW1X 7XL</b>", "Bromton Rd, SW1X 7XL", "10:10", "Wednesday", 12.5,id, true);
        categoryChildren.add(event);
        id++;
        event = new DriveEvent("10:40 near <b>Bromton Rd, SW1X 7XL</b>", "Bromton Rd, SW1X 7XL", "10:40", "Tuesday", 13.5, id, true);
        categoryChildren.add(event);
        id++;
        events.add(categoryChildren);

        // Acceleration
        categoryChildren = new ArrayList<DriveEvent>();
        event = new DriveEvent("17:20 near <b>20 Lombard Str.</b>", "20 Lombard Str.", "17:20", "Today", 3.8,2, false);
        mapEvents.add(event);
        categoryChildren.add(event);
        id++;
        event = new DriveEvent("17:50 near <b>20 Lombard Str.</b>", "20 Lombard Str.", "17:50", "Today", 9.0,3, false);
        mapEvents.add(event);
        categoryChildren.add(event);
        id++;
        events.add(categoryChildren);

        // Braking
        categoryChildren = new ArrayList<DriveEvent>();
        event = new DriveEvent("10:20 near <b>Bromton Rd, SW1X 7XL</b>", "Bromton Rd, SW1X 7XL", "10:20", "Tuesday", 1.3,id, true);
        categoryChildren.add(event);
        id++;
        events.add(categoryChildren);

        //Over Speeding
        categoryChildren = new ArrayList<DriveEvent>();
        event = new DriveEvent("13:22 near <b>Bromton Rd, SW1X 7XL</b>", "Bromton Rd, SW1X 7XL", "13:22", "Today", 11.0,id, true);
        categoryChildren.add(event);
        id++;
        event = new DriveEvent("15:40 near <b>Bromton Rd, SW1X 7XL</b>", "Bromton Rd, SW1X 7XL", "15:40", "Today", 11.0,id, true);
        categoryChildren.add(event);
        id++;
        event = new DriveEvent("12:20 near <b>Bromton Rd, SW1X 7XL</b>", "Bromton Rd, SW1X 7XL", "12:20", "Wednesday",11.0, id, true);
        categoryChildren.add(event);
        id++;
        event = new DriveEvent("13:01 near <b>Bromton Rd, SW1X 7XL</b>", "Bromton Rd, SW1X 7XL", "13:01", "Wednesday", 11.0,id, true);
        categoryChildren.add(event);
        id++;
        event = new DriveEvent("10:10 near <b>Bromton Rd, SW1X 7XL</b>", "Bromton Rd, SW1X 7XL", "10:10", "Tuesday", 11.0,id, true);
        categoryChildren.add(event);
        id++;
        event = new DriveEvent("10:44 near <b>Bromton Rd, SW1X 7XL</b>", "Bromton Rd, SW1X 7XL", "10:44", "Tuesday",11.0, id, true);
        categoryChildren.add(event);
        id++;
        events.add(categoryChildren);

        // Fuel
        categoryChildren = new ArrayList<DriveEvent>();
        events.add(categoryChildren);
        event = new DriveEvent("17:00 near <b>Bromton Rd, SW1X 7XL</b>", "Bromton Rd, SW1X 7XL", "17:00", "Today", 11.0,id, true);
        categoryChildren.add(event);
        id++;
        event = new DriveEvent("15:20 near <b>Bromton Rd, SW1X 7XL</b>", "Bromton Rd, SW1X 7XL", "15:20", "Wednesday", 7.2,id, true);
        categoryChildren.add(event);
        id++;
        event = new DriveEvent("10:59 near <b>Bromton Rd, SW1X 7XL</b>", "Bromton Rd, SW1X 7XL", "10:59", "Tuesday", 4.0,id, true);
        categoryChildren.add(event);
        id++;
        events.add(categoryChildren);

        return events;
    }

    public Object getEvent(int categoriesId, int eventsId) {
        return events.get(categoriesId).get(eventsId);
    }

    public static ArrayList<DriveEvent> getMapEvents() {
        return mapEvents;
    }
}
