package fl.developer.teleproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import fl.developer.teleproject.model.Category;
import fl.developer.teleproject.model.Data;
import fl.developer.teleproject.model.DriveEvent;

/**
 * Created by alexk on 31.10.2014.
 */
public class CategoriesAdapter extends BaseExpandableListAdapter {

    private static volatile ArrayList<Boolean> virginCategories;

    private static final int CATEGORIES_COUNT = 6;

    static     {
        virginCategories = new ArrayList<Boolean>(CATEGORIES_COUNT);
        for (int i = 0; i < CATEGORIES_COUNT; i++) {
            virginCategories.add(Boolean.TRUE);
        }
    }

    private Context mContext;
    private ArrayList<Category> mCategories;
    private ArrayList<ArrayList<DriveEvent>> mEvents;

    private boolean useForOldEvents = false;

    public CategoriesAdapter(Context context, ArrayList<Category> categories, ArrayList<ArrayList<DriveEvent>> events) {
        mContext = context;
        mCategories = categories;
        mEvents = events;
    }

    @Override
    public int getGroupCount() {
        return mCategories.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return mEvents.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return mCategories.get(i);
    }

    @Override
    public Object getChild(int i, int i2) {
        return mEvents.get(i).get(i2);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i2) {
        return i2;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        if (isExpanded && virginCategories.get(groupPosition)) {
            virginCategories.set(groupPosition,Boolean.FALSE);
        }
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_category, null);
        }
        Category category = mCategories.get(groupPosition);

        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        icon.setImageResource(category.getIconRes());

        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(category.getName());

        TextView score = (TextView) view.findViewById(R.id.person_value);
        double scoreValue = 0;
        int todayEventsCount = 0;
        for (DriveEvent child : mEvents.get(groupPosition)) {
            scoreValue += child.getScore();
            if ("Today".equals(child.getDay())) {
                todayEventsCount++;
            }
        }
        String scoreString = String.valueOf(scoreValue);
        score.setText(scoreString);
        score.setTextColor(scoreValue < category.getSiteAverage() ? Color.parseColor("#ff39d000") : Color.parseColor("#ffff8800"));

        TextView siteAverage = (TextView) view.findViewById(R.id.site_average);
        siteAverage.setText(category.getSiteAverageString());

        TextView indicator = (TextView) view.findViewById(R.id.category_state_indicator);
        if (useForOldEvents || !virginCategories.get(groupPosition)) {
            indicator.setText("");
            indicator.setBackgroundResource(isExpanded ? R.drawable.indicator_opened : R.drawable.indicator_closed);
        } else if (todayEventsCount > 0) {
            indicator.setText("+" + todayEventsCount);
            indicator.setBackgroundResource(R.drawable.indicator_new);
        } else {
            indicator.setText("");
            indicator.setBackgroundResource(isExpanded ? R.drawable.indicator_opened : R.drawable.indicator_closed);
        }

        return view;
    }

    View.OnClickListener eventClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
           // if (!useForOldEvents || ((Integer) view.getTag()).intValue() >= 0) {
            if (((Integer) view.getTag()).intValue() >= 0) {
                Intent intent = new Intent(mContext, MapsActivity.class);
                intent.putExtra(Data.EVENT_CODE_TITLE, ((Integer) view.getTag()).intValue());
                ((Activity) mContext).startActivity(intent);
            }
        }
    };

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View childView, ViewGroup parent) {
        if (childView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            childView = inflater.inflate(R.layout.item_event, null);
        }

        DriveEvent event = (DriveEvent) getChild(groupPosition, childPosition);

        TextView description = (TextView) childView.findViewById(R.id.address);
        String day = event.getDay();
        if (!useForOldEvents) {
            if ("Today".equals(day)) {
                description.setText(Html.fromHtml("<font color=#e91e63>" + day + ", " + event.getAddress() + "</font>"));
            } else {
                description.setText(Html.fromHtml(day + ", " + event.getAddress()));
            }
        } else {
            description.setText(Html.fromHtml("Monday, " + event.getAddress()));
        }
        if (!event.isFake()) {
            childView.setTag(event.getId());
        } else {
            childView.setTag(-1);
        }

        TextView score = (TextView) childView.findViewById(R.id.score);
        score.setText(String.valueOf(event.getScore()));

        childView.setOnClickListener(eventClickListener);

        return childView;
    }

    @Override
    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    public boolean isUseForOldEvents() {
        return this.useForOldEvents;
    }

    public void setUseForOldEvents(boolean useForOldEvents) {
        this.useForOldEvents = useForOldEvents;
    }
}
