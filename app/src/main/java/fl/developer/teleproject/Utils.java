package fl.developer.teleproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by alexk on 03.11.2014.
 */
public class Utils {

    public static void imageViewAnimatedChange(Context c, final ImageView v, final Bitmap new_image, int animOutRes, int animInRes) {
        final Animation animOut = AnimationUtils.loadAnimation(c, animOutRes);
        final Animation animIn = AnimationUtils.loadAnimation(c, animInRes);
        animOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                v.setImageBitmap(new_image);
                animIn.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                    }
                });
                v.startAnimation(animIn);
            }
        });
        v.startAnimation(animOut);
    }

    public static void imageViewAnimatedChange(Context c, final ImageView v, final Bitmap new_image) {
        imageViewAnimatedChange(c,v,new_image,android.R.anim.fade_out,android.R.anim.fade_in);
    }
}
