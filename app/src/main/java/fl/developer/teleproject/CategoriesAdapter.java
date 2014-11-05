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

    public static volatile boolean hasNewEvents = true;

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
        String scoreString = String.valueOf(category.getScore());
        score.setText(scoreString);
        score.setTextColor(category.getScore() < category.getSiteAverage() ? Color.parseColor("#ff39d000") : Color.parseColor("#ffff8800"));


        // hardcode for new events notification
//        if (0 == groupPosition && !useForOldEvents) {
//            if (hasNewEvents && !isExpanded) {
//                String newLabel = " (1 new)";
//                SpannableString ss =  new SpannableString(scoreString + newLabel);
//                ss.setSpan(new RelativeSizeSpan(0.6f), scoreString.length(),ss.length(), 0); // set size
//                score.setText(ss);
//            } else {
//                hasNewEvents = false;
//            }
//        }

        TextView siteAverage = (TextView) view.findViewById(R.id.site_average);
        siteAverage.setText(category.getSiteAverageString());
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
        if ("Today".equals(day)) {
            description.setText(Html.fromHtml("<font color=#e91e63>" + day + ", " + event.getAddress() + "</font>"));
        } else {
            description.setText(Html.fromHtml(day + ", " + event.getAddress()));
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
