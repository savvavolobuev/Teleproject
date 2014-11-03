package fl.developer.teleproject;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import java.util.ArrayList;

import fl.developer.teleproject.model.Category;
import fl.developer.teleproject.model.Data;
import fl.developer.teleproject.model.DriveEvent;


public class MainActivity extends Activity {

    public static final int INFO_FRAGMENT = 0;
    public static final int CALENDAR_FRAGMENT = 1;
    private InfoFragment infoFragment;
    private CalendarFragment calendarFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        infoFragment = new InfoFragment();
        calendarFragment = new CalendarFragment();
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.events_container, new EventsFragment())
                    .add(R.id.info_container, infoFragment)
                    .commit();
        }
    }

    @Override
    protected void onDestroy() {
        infoFragment = null;
        calendarFragment = null;
        super.onDestroy();
    }

    private void changeInfoFragment() {
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_top, R.anim.slide_out_bottom)
                .replace(R.id.info_container, calendarFragment.isVisible() ? infoFragment : calendarFragment)
                .commit();
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
            eventsView = (CategoriesView) rootView.findViewById(R.id.eventsListView);
            Data data = new Data();
            eventsView.setAdapter(new CategoriesAdapter(getActivity(),data.getCategories(),data.getEvents()));
            return rootView;
        }


/*
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
                event = new DriveEvent("9:29 near <b>Woodhouse Rd</b>");
                categoryChildren.add(event);
                event = new DriveEvent("12:42 near <b>Bromton Rd, SW1X 7XL</b>");
                categoryChildren.add(event);
                events.add(categoryChildren);
            }
            return events;
        }
*/

    }

    public static class InfoFragment extends Fragment {


        public InfoFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_info, container, false);
            ImageView toCalendar = (ImageView) rootView.findViewById(R.id.toCalendar);
            toCalendar.setClickable(true);
            toCalendar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity)getActivity()).changeInfoFragment();
                }
            });

            ImageView meterCursor = (ImageView) rootView.findViewById(R.id.meter_cursor);
            meterCursor.setVisibility(View.GONE);
            Animation clockTurn = AnimationUtils.loadAnimation(getActivity(), R.anim.meter_anim);
            meterCursor.startAnimation(clockTurn);
            meterCursor.setVisibility(View.VISIBLE);
            return rootView;
        }
    }

    public static class CalendarFragment extends Fragment {


        public CalendarFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);
            ImageView toInfo = (ImageView) rootView.findViewById(R.id.toInfo);
            toInfo.setClickable(true);
            toInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity)getActivity()).changeInfoFragment();
                }
            });
            return rootView;
        }
    }
}
