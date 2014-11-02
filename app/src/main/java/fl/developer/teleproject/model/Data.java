package fl.developer.teleproject.model;

import java.util.ArrayList;

import fl.developer.teleproject.R;

/**
 * Created by Katrina on 02.11.2014.
 */
public class Data {

    ArrayList<Category> categories = new ArrayList<Category>();
    ArrayList<ArrayList<DriveEvent>> events = new ArrayList<ArrayList<DriveEvent>>();
    public static final int CATEGORY_CODE = 0;
    public static final String CATEGORY_CODE_TITLE = "category";
    public static final int EVENT_CODE = 1;
    public static final String EVENT_CODE_TITLE = "event";

    public Data() {
        categories = initCategories();
        events = initEvents();
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public ArrayList<ArrayList<DriveEvent>> getEvents() {
        return events;
    }

    private ArrayList<Category> initCategories() {
        ArrayList<Category> categories = new ArrayList<Category>();
        Category category = new Category(R.drawable.revving, "Revving:", 12, 19);
        categories.add(category);
        category = new Category(R.drawable.idling, "Idling:", 15, 15);
        categories.add(category);
        category = new Category(R.drawable.acceleration, "Acceleration:", 2, 2);
        categories.add(category);
        return categories;
    }

    private ArrayList<ArrayList<DriveEvent>> initEvents() {
        ArrayList<ArrayList<DriveEvent>> events = new ArrayList<ArrayList<DriveEvent>>();
        ArrayList<DriveEvent> categoryChildren = null;
        DriveEvent event = null;

        categoryChildren = new ArrayList<DriveEvent>();
        event = new  DriveEvent("10:35 near <b>5 Green Avenue, Apt. 3</b>", "5 Green Avenue, Apt. 3", "10:35", "Monday");
        categoryChildren.add(event);
        events.add(categoryChildren);

        categoryChildren = new ArrayList<DriveEvent>();
        event = new  DriveEvent("06:55 near <b>20 Lombard Str.</b>", "20 Lombard Str.", "06:55", "Monday");
        categoryChildren.add(event);
        events.add(categoryChildren);

        categoryChildren = new ArrayList<DriveEvent>();
        event = new  DriveEvent("9:29 near <b>Woodhouse Rd</b>", "Woodhouse Rd", "9:29", "Monday");
        categoryChildren.add(event);
        event = new  DriveEvent("12:42 near <b>Bromton Rd, SW1X 7XL</b>", "Bromton Rd, SW1X 7XL", "12:42", "Monday");
        categoryChildren.add(event);
        events.add(categoryChildren);

        return events;
    }

    public Object getEvent(int categoriesId, int eventsId) {
        return events.get(categoriesId).get(eventsId);
    }

}
