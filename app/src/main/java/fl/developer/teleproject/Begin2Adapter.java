package fl.developer.teleproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Katrina on 30.10.2014.
 */
public class Begin2Adapter extends BaseAdapter {

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Begin2Item> objects;

    public Begin2Adapter(Context ctx, ArrayList<Begin2Item> objects) {
        this.ctx = ctx;
        this.objects = objects;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        // используем созданные, но не используемые view
        View localView = view;
        if (localView == null) {
            localView = lInflater.inflate(R.layout.item_begin2, viewGroup, false);
        }

        Begin2Item begin2Item = (Begin2Item) getItem(i);

        // заполняем View в пункте списка данными из товаров: наименование, цена
        // и картинка
        TextView personNameView = (TextView) localView.findViewById(R.id.personName);
        personNameView.setText(begin2Item.personName);
        ((TextView) localView.findViewById(R.id.description)).setText(begin2Item.description);
        ((TextView) localView.findViewById(R.id.number)).setText(begin2Item.number);
        ImageView personImageView = (ImageView) localView.findViewById(R.id.personImage);
        personImageView.setImageResource(begin2Item.personImage);
/*
        // set circle bitmap
        Bitmap bm = BitmapFactory.decodeResource(ctx.getResources(),
                begin2Item.personImage);

        ((ImageView) localView.findViewById(R.id.personImage)).setImageBitmap(getCircleBitmap(bm));
*/
        personNameView.setTag(i);
        personImageView.setTag(i);

        personNameView.setOnClickListener(clickListener);
        personImageView.setOnClickListener(clickListener);

        return localView;
    }

    View.OnClickListener clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            if (view != null && ((Integer) view.getTag()).intValue() == 0) {
                Intent intent = new Intent(ctx, LoginActivity.class);
                ((Activity)ctx).startActivityForResult(intent, Begin2Activity.LOGIN_CODE);
            }
        }
    };

    private Bitmap getCircleBitmap(Bitmap bitmap) {
        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final int color = Color.RED;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawOval(rectF, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        //bitmap.recycle();

        return output;
    }

}
