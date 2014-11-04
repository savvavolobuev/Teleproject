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
        return categories;
    }

    private static ArrayList<ArrayList<DriveEvent>> initEvents() {
        ArrayList<ArrayList<DriveEvent>> events = new ArrayList<ArrayList<DriveEvent>>();
        int id = 0;

        ArrayList<DriveEvent> categoryChildren = new ArrayList<DriveEvent>();
        DriveEvent event = new DriveEvent("10:35 near <b>5 Green Avenue, Apt. 3</b>", "5 Green Avenue, Apt. 3", "10:35", "Monday", id);
        categoryChildren.add(event);
        id++;
        events.add(categoryChildren);
        allEvents.addAll(categoryChildren);

        categoryChildren = new ArrayList<DriveEvent>();
        event = new  DriveEvent("06:55 near <b>20 Lombard Str.</b>", "20 Lombard Str.", "06:55", "Monday", id);
        categoryChildren.add(event);
        id++;
        events.add(categoryChildren);
        allEvents.addAll(categoryChildren);

        categoryChildren = new ArrayList<DriveEvent>();
        event = new  DriveEvent("9:29 near <b>Woodhouse Rd</b>", "Woodhouse Rd", "9:29", "Monday", id);
        categoryChildren.add(event);
        id++;
        event = new  DriveEvent("12:42 near <b>Bromton Rd, SW1X 7XL</b>", "Bromton Rd, SW1X 7XL", "12:42", "Monday",id );
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
        DriveEvent event = new DriveEvent("9:29 near <b>Woodhouse Rd</b>", "Woodhouse Rd", "9:29", "Monday", id);
        categoryChildren.add(event);
        id++;
        event = new DriveEvent("12:42 near <b>Bromton Rd, SW1X 7XL</b>", "Bromton Rd, SW1X 7XL", "12:42", "Monday", id);
        categoryChildren.add(event);
        id++;
        events.add(categoryChildren);

        categoryChildren = new ArrayList<DriveEvent>();
        event = new DriveEvent("06:55 near <b>20 Lombard Str.</b>", "20 Lombard Str.", "06:55", "Monday", id);
        categoryChildren.add(event);
        id++;
        events.add(categoryChildren);

        categoryChildren = new ArrayList<DriveEvent>();
        event = new DriveEvent("10:35 near <b>5 Green Avenue, Apt. 3</b>", "5 Green Avenue, Apt. 3", "10:35", "Monday", id);
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
