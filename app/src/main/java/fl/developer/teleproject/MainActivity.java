package fl.developer.teleproject;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import java.util.TimerTask;

import fl.developer.teleproject.model.Data;


public class MainActivity extends Activity {

    private InfoFragment infoFragment;
    private CalendarFragment calendarFragment;
    private Handler timer;
    private Runnable changeWeatherTask = new TimerTask() {
        @Override
        public void run() {
            if (infoFragment.isVisible()) {
                infoFragment.changeWeather();
            }
            timer.postDelayed(changeWeatherTask, 7000);
        }
    };


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
        timer = new Handler();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //Log.d("weather", "onResume");
        changeWeatherTask.run();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Log.d("weather", "onPause");
        timer.removeCallbacks(changeWeatherTask);
    }

    @Override
    protected void onDestroy() {
        infoFragment = null;
        calendarFragment = null;
        timer.removeCallbacks(changeWeatherTask);
        timer = null;
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
            eventsView.setAdapter(new CategoriesAdapter(getActivity(), data.getCategories(), data.getEvents()));
            return rootView;
        }

    }

    public static class InfoFragment extends Fragment {

        public static int[] weather_backgrounds;
        public static int[] weather_texts;
        private int currentWeather = 0;

        {
            weather_backgrounds = new int[]{R.drawable.background_weather};
            weather_texts = new int[]{R.drawable.weather};
        }

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
                    ((MainActivity) getActivity()).changeInfoFragment();
                }
            });

            ImageView meterCursor = (ImageView) rootView.findViewById(R.id.meter_cursor);
            meterCursor.setVisibility(View.GONE);
            Animation clockTurn = AnimationUtils.loadAnimation(getActivity(), R.anim.meter_anim);
            meterCursor.startAnimation(clockTurn);
            meterCursor.setVisibility(View.VISIBLE);
            return rootView;
        }


        public void changeWeather() {
           // Log.d("weather", "changeWeather, isVisible: " + isVisible() + ", currentWeather: " + currentWeather);
            if (weather_backgrounds.length > 1) {

                if (currentWeather < weather_backgrounds.length - 1) {
                    currentWeather++;
                } else {
                    currentWeather = 0;
                }

                Bitmap newBackground = BitmapFactory.decodeResource(getResources(), weather_backgrounds[currentWeather]);
                Bitmap newText = BitmapFactory.decodeResource(getResources(), weather_texts[currentWeather]);
                Utils.imageViewAnimatedChange(getActivity(),(ImageView) getView().findViewById(R.id.weather_background),newBackground);
                Utils.imageViewAnimatedChange(getActivity(),(ImageView) getView().findViewById(R.id.weather),newText);
            }
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
                    ((MainActivity) getActivity()).changeInfoFragment();
                }
            });
            return rootView;
        }
    }
}
