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

    private Context mContext;
    private ArrayList<Category> mCategories;
    private ArrayList<ArrayList<DriveEvent>> mEvents;

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
        score.setText(String.valueOf(category.getScore()));
        score.setTextColor(category.getScore() > 10 ? Color.parseColor("#ff39d000") : Color.parseColor("#ffff8800"));

        TextView siteAverage = (TextView) view.findViewById(R.id.site_average);
        siteAverage.setText(category.getSiteAverageString());
        return view;
    }

    View.OnClickListener eventClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(mContext, MapsActivity.class);
            //intent.putExtra(Data.CATEGORY_CODE_TITLE, ((Integer) view.getTag(Data.CATEGORY_CODE)).intValue());
            //intent.putExtra(Data.EVENT_CODE_TITLE, ((Integer) view.getTag(Data.EVENT_CODE)).intValue());

            mContext.startActivity(intent);
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
        description.setText(Html.fromHtml(event.getAddress()));

     //   description.setTag(Data.CATEGORY_CODE,groupPosition);
     //   description.setTag(Data.EVENT_CODE,childPosition);
        childView.setOnClickListener(eventClickListener);

        return childView;
    }

    @Override
    public boolean isChildSelectable(int i, int i2) {
        return true;
    }
}
