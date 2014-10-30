package fl.developer.teleproject;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.events_container, new EventsFragment())
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
            eventsView.setAdapter(initAdapter());
            return rootView;
        }

        private ExpandableListAdapter initAdapter() {
            // event categories
            String[] categories = new String[]{"Revving", "Idling", "Acceleration"};

            // events
            String[] events = new String[]{"9:29 near Woodhouse Rd", "12:42 near Bromton Rd, SW1X 7XL"};

            // categories collection
            ArrayList<Map<String, String>> categoriesData;

            // category data collection
            ArrayList<Map<String, String>> childDataItem;

            // collections wrapper
            ArrayList<ArrayList<Map<String, String>>> childData;

            // attributes map
            Map<String, String> m;

            categoriesData = new ArrayList<Map<String, String>>();
            for (String group : categories) {
                m = new HashMap<String, String>();
                m.put("groupName", group);
                categoriesData.add(m);
            }

            String groupFrom[] = new String[]{"groupName"};
            int groupTo[] = new int[]{android.R.id.text1};

            childData = new ArrayList<ArrayList<Map<String, String>>>();

            for (int i = 0; i < 3; i++) {
                childDataItem = new ArrayList<Map<String, String>>();
                for (String event : events) {
                    m = new HashMap<String, String>();
                    m.put("eventName", event);
                    childDataItem.add(m);
                }
                childData.add(childDataItem);
            }

            String childFrom[] = new String[]{"eventName"};
            int childTo[] = new int[]{android.R.id.text1};

            SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                    getActivity(),
                    categoriesData,
                    android.R.layout.simple_expandable_list_item_1,
                    groupFrom,
                    groupTo,
                    childData,
                    android.R.layout.simple_list_item_1,
                    childFrom,
                    childTo);

            return adapter;
        }


    }
}
