package fl.developer.teleproject;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;

import fl.developer.teleproject.model.Category;
import fl.developer.teleproject.model.DriveEvent;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.events_container, new EventsFragment())
                    .add(R.id.info_container, new InfoFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class EventsFragment extends Fragment {

        ExpandableListView eventsView;

        public EventsFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_events, container, false);
            eventsView = (ExpandableListView) rootView.findViewById(R.id.eventsListView);
            eventsView.setAdapter(new CategoriesAdapter(getActivity(),initCategories(),initEvents()));
            return rootView;
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

            for (int i = 0; i <3; i++) {
                categoryChildren = new ArrayList<DriveEvent>();
                event = new DriveEvent("9:29 near Woodhouse Rd");
                categoryChildren.add(event);
                event = new DriveEvent("12:42 near Bromton Rd, SW1X 7XL");
                categoryChildren.add(event);
                events.add(categoryChildren);
            }
            return events;
        }


    }

    public static class InfoFragment extends Fragment {


        public InfoFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_info, container, false);
            return rootView;
        }
    }
}
