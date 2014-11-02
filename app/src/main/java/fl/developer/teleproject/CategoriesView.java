package fl.developer.teleproject;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/**
 * Created by alexk on 01.11.2014.
 */
public class CategoriesView extends ExpandableListView {

    public CategoriesView(Context context) {
        super(context);
    }

    public CategoriesView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CategoriesView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        setIndicatorBoundsRelative(w-82,w-5);
    }
}
