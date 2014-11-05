package fl.developer.teleproject.model;

import java.util.ArrayList;

import fl.developer.teleproject.R;

/**
 * Created by Katrina on 02.11.2014.
 */
public class Data {

    private static ArrayList<Category> categories;
    private static ArrayList<ArrayList<DriveEvent>> events;
    private static ArrayList<ArrayList<DriveEvent>> oldEvents;
    private static ArrayList<DriveEvent> allEvents = new ArrayList<DriveEvent>();

    public static final int CATEGORY_CODE = 0;
    public static final String CATEGORY_CODE_TITLE = "category";
    public static final int EVENT_CODE = 1;
    public static final String EVENT_CODE_TITLE = "event";

    static {
        categories = initCategories();
        events = initEvents();
        oldEvents = initOldEvents();
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
        Category category = new Category(R.drawable.revving, "Revving:", 12, 19);
        categories.add(category);
        category = new Category(R.drawable.idling, "Idling:", 15, 15);
        categories.add(category);
        category = new Category(R.drawable.acceleration, "Acceleration:", 2, 2);
        categories.add(category);
        category = new Category(R.drawable.breaking, "Braking:", 1, 4);
        categories.add(category);
        category = new Category(R.drawable.speeding, "Over Speeding:", 2, 3);
        categories.add(category);
        category = new Category(R.drawable.fuel, "Fuel:", 3, 5);
        categories.add(category);
        return categories;
    }

    private static ArrayList<ArrayList<DriveEvent>> initEvents() {
        ArrayList<ArrayList<DriveEvent>> events = new ArrayList<ArrayList<DriveEvent>>();
        int id = 0;

        // Revving
        ArrayList<DriveEvent> categoryChildren = new ArrayList<DriveEvent>();
        DriveEvent event = new DriveEvent("10:10 near <b>5 Green Avenue, Apt. 3</b>", "5 Green Avenue, Apt. 3", "10:10", "Today", id, false);
        categoryChildren.add(event);
        id++;
        events.add(categoryChildren);
        allEvents.addAll(categoryChildren);


        // Idling
        categoryChildren = new ArrayList<DriveEvent>();
        event = new DriveEvent("12:01 near <b>Woodhouse Rd</b>", "Woodhouse Rd", "12:01", "Today", id, false);
        categoryChildren.add(event);
        id++;
        event = new DriveEvent("10:10 near <b>Bromton Rd, SW1X 7XL</b>", "Bromton Rd, SW1X 7XL", "10:10", "Wednesday", id, true);
        categoryChildren.add(event);
        id++;
        event = new DriveEvent("10:40 near <b>Bromton Rd, SW1X 7XL</b>", "Bromton Rd, SW1X 7XL", "10:40", "Tuesday", id, true);
        categoryChildren.add(event);
        id++;
        events.add(categoryChildren);
        allEvents.addAll(categoryChildren);

        // Acceleration
        categoryChildren = new ArrayList<DriveEvent>();
        event = new DriveEvent("17:20 near <b>20 Lombard Str.</b>", "20 Lombard Str.", "17:20", "Today", id, false);
        categoryChildren.add(event);
        id++;
        event = new DriveEvent("17:50 near <b>20 Lombard Str.</b>", "20 Lombard Str.", "17:50", "Today", id, false);
        categoryChildren.add(event);
        id++;
        events.add(categoryChildren);
        allEvents.addAll(categoryChildren);

        // Braking
        categoryChildren = new ArrayList<DriveEvent>();
        event = new  DriveEvent("10:20 near <b>Bromton Rd, SW1X 7XL</b>", "Bromton Rd, SW1X 7XL", "10:20", "Tuesday", id, true);
        categoryChildren.add(event);
        id++;
        events.add(categoryChildren);
        allEvents.addAll(categoryChildren);

        //Over Speeding
        categoryChildren = new ArrayList<DriveEvent>();
        event = new  DriveEvent("13:22 near <b>Bromton Rd, SW1X 7XL</b>", "Bromton Rd, SW1X 7XL", "13:22", "Today", id, true);
        categoryChildren.add(event);
        id++;
        event = new  DriveEvent("15:40 near <b>Bromton Rd, SW1X 7XL</b>", "Bromton Rd, SW1X 7XL", "15:40", "Today", id, true);
        categoryChildren.add(event);
        id++;
        event = new  DriveEvent("12:20 near <b>Bromton Rd, SW1X 7XL</b>", "Bromton Rd, SW1X 7XL", "12:20", "Wednesday", id, true);
        categoryChildren.add(event);
        id++;
        event = new  DriveEvent("13:01 near <b>Bromton Rd, SW1X 7XL</b>", "Bromton Rd, SW1X 7XL", "13:01", "Wednesday", id, true);
        categoryChildren.add(event);
        id++;
        event = new  DriveEvent("10:10 near <b>Bromton Rd, SW1X 7XL</b>", "Bromton Rd, SW1X 7XL", "10:10", "Tuesday", id, true);
        categoryChildren.add(event);
        id++;
        event = new  DriveEvent("10:44 near <b>Bromton Rd, SW1X 7XL</b>", "Bromton Rd, SW1X 7XL", "10:44", "Tuesday", id, true);
        categoryChildren.add(event);
        id++;
        events.add(categoryChildren);
        allEvents.addAll(categoryChildren);

        // Fuel
        categoryChildren = new ArrayList<DriveEvent>();
        events.add(categoryChildren);
        event = new  DriveEvent("17:00 near <b>Bromton Rd, SW1X 7XL</b>", "Bromton Rd, SW1X 7XL", "17:00", "Today", id, true);
        categoryChildren.add(event);
        id++;
        event = new  DriveEvent("15:20 near <b>Bromton Rd, SW1X 7XL</b>", "Bromton Rd, SW1X 7XL", "15:20", "Wednesday", id, true);
        categoryChildren.add(event);
        id++;
        event = new  DriveEvent("10:59 near <b>Bromton Rd, SW1X 7XL</b>", "Bromton Rd, SW1X 7XL", "10:59", "Tuesday", id, true);
        categoryChildren.add(event);
        id++;
        events.add(categoryChildren);
        allEvents.addAll(categoryChildren);

        return events;
    }

    private static ArrayList<ArrayList<DriveEvent>> initOldEvents() {
        ArrayList<ArrayList<DriveEvent>> events = new ArrayList<ArrayList<DriveEvent>>();
        int id = 0;

        ArrayList<DriveEvent> categoryChildren = new ArrayList<DriveEvent>();
        DriveEvent event = new DriveEvent("9:29 near <b>Woodhouse Rd</b>", "Woodhouse Rd", "9:29", "Monday", id, true);
        categoryChildren.add(event);
        id++;
        event = new DriveEvent("12:42 near <b>Bromton Rd, SW1X 7XL</b>", "Bromton Rd, SW1X 7XL", "12:42", "Monday", id, true);
        categoryChildren.add(event);
        id++;
        events.add(categoryChildren);

        categoryChildren = new ArrayList<DriveEvent>();
        event = new DriveEvent("06:55 near <b>20 Lombard Str.</b>", "20 Lombard Str.", "06:55", "Monday", id, true);
        categoryChildren.add(event);
        id++;
        events.add(categoryChildren);

        categoryChildren = new ArrayList<DriveEvent>();
        event = new DriveEvent("10:35 near <b>5 Green Avenue, Apt. 3</b>", "5 Green Avenue, Apt. 3", "10:35", "Monday", id, true);
        categoryChildren.add(event);
        id++;
        events.add(categoryChildren);

        return events;
    }

    public Object getEvent(int categoriesId, int eventsId) {
        return events.get(categoriesId).get(eventsId);
    }

    public static ArrayList<DriveEvent> getAllEvents() {
        return allEvents;
    }
}
