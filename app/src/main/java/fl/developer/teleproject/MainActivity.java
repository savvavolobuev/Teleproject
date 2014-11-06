package fl.developer.teleproject;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.TimerTask;

import fl.developer.teleproject.model.Data;


public class MainActivity extends Activity {

    private static boolean isFirstShow = true;
    public static final int WEATHER_ROTATION_DELAY = 7500;
    private InfoFragment infoFragment;
    private CalendarFragment calendarFragment;
    private EventsFragment eventsFragment;
    private Handler timer;
    private Runnable changeWeatherTask = new TimerTask() {
        @Override
        public void run() {
            if (infoFragment.isVisible()) {
                infoFragment.changeWeather();
            }
            //timer.postDelayed(changeWeatherTask, WEATHER_ROTATION_DELAY);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        infoFragment = new InfoFragment();
        calendarFragment = new CalendarFragment();
        eventsFragment = new EventsFragment();
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.events_container, eventsFragment)
                    .add(R.id.info_container, infoFragment)
                    .commit();
        }
        if (isFirstShow) {
            timer = new Handler();
            timer.postDelayed(changeWeatherTask, WEATHER_ROTATION_DELAY);
            isFirstShow = false;
        }
//        Toast t = Toast.makeText(this,"Welcome back to work, Diego!",Toast.LENGTH_LONG);
//        t.setGravity(Gravity.TOP,0,64);
//        t.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Log.d("weather", "onResume");
        // changeWeatherTask.run();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Log.d("weather", "onPause");
        //timer.removeCallbacks(changeWeatherTask);
    }

    @Override
    protected void onDestroy() {
        infoFragment = null;
        calendarFragment = null;
        if (timer != null) {
            timer.removeCallbacks(changeWeatherTask);
            timer = null;
        }
        super.onDestroy();
    }

    private void changeInfoFragment() {
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_top, R.anim.slide_out_bottom)
                .replace(R.id.info_container, calendarFragment.isVisible() ? infoFragment : calendarFragment)
                .commit();
    }

    public void resetEvents(boolean areOldEvents) {
        CategoriesAdapter adapter;
        if (areOldEvents) {
            adapter = new CategoriesAdapter(this, Data.getCategories(), Data.getOldEvents());
            adapter.setUseForOldEvents(true);
        } else {
            adapter = new CategoriesAdapter(this, Data.getCategories(), Data.getEvents());
        }
        eventsFragment.getEventsView().setAdapter(adapter);
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

        public ExpandableListView getEventsView() {
            return eventsView;
        }

        ExpandableListView eventsView;

        public EventsFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_events, container, false);
            TextView scoresLabel = (TextView) rootView.findViewById(R.id.scores_label);
            scoresLabel.setText(Html.fromHtml("<font color=#4d4d4d>Events score</font><font color=#808080> vs site average</font>"));
            eventsView = (CategoriesView) rootView.findViewById(R.id.eventsListView);
            eventsView.setAdapter(new CategoriesAdapter(getActivity(), Data.getCategories(), Data.getEvents()));
            return rootView;
        }

    }

    public static class InfoFragment extends Fragment {

        public static int[] weather_backgrounds;
        public static int[] weather_texts;
        private int currentWeather = 0;
        private static boolean isFirstShow = true;

        {
            weather_backgrounds = new int[]{R.drawable.background_weather,R.drawable.background_weather};
            weather_texts = new int[]{R.drawable.weather_header, R.drawable.weather};
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
            if (isFirstShow) {
                ImageView weather = (ImageView) rootView.findViewById(R.id.weather);
                weather.setImageResource(R.drawable.weather_header);
                isFirstShow = false;
            }
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

                // Bitmap newBackground = BitmapFactory.decodeResource(getResources(), weather_backgrounds[currentWeather]);
                Bitmap newText = BitmapFactory.decodeResource(getResources(), weather_texts[currentWeather]);
                //Utils.imageViewAnimatedChange(getActivity(), (ImageView) getView().findViewById(R.id.weather_background), newBackground);
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
            final View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);
            ImageView toInfo = (ImageView) rootView.findViewById(R.id.toInfo);
            toInfo.setClickable(true);
            toInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) getActivity()).changeInfoFragment();

                }
            });
            final ImageButton toPrevWeek = (ImageButton) rootView.findViewById(R.id.prev_week);
            final ImageButton toLastWeek = (ImageButton) rootView.findViewById(R.id.last_week);
            final ImageView scores = (ImageView) rootView.findViewById(R.id.scores_image);
            toLastWeek.setEnabled(false);
            toPrevWeek.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("calendar", "toPrevWeek pressed");
                    switchWeek(true,toPrevWeek,toLastWeek,scores);
                }
            });

            toLastWeek.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("calendar", "toLastWeek pressed");
                    switchWeek(false, toPrevWeek, toLastWeek, scores);
                }
            });
            return rootView;
        }

        private void switchWeek(boolean toPrev, ImageButton toPrevWeek, ImageButton toLastWeek, ImageView scores) {
            toLastWeek.setEnabled(toPrev);
            toPrevWeek.setEnabled(!toPrev);
            Bitmap nextImage = BitmapFactory.decodeResource(getResources(),toPrev ? R.drawable.cycle_prev : R.drawable.cycle);
            if (toPrev) {
                Utils.imageViewAnimatedChange(getActivity(),scores,nextImage,android.R.anim.slide_out_right,android.R.anim.slide_in_left);
            } else {
                Utils.imageViewAnimatedChange(getActivity(),scores,nextImage,R.anim.slide_out_left,R.anim.slide_in_right);
            }
            ((MainActivity) getActivity()).resetEvents(toPrev);
        }
    }
}
