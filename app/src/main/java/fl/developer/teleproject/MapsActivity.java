package fl.developer.teleproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import fl.developer.teleproject.model.Data;
import fl.developer.teleproject.model.DriveEvent;


public class MapsActivity extends Activity implements View.OnClickListener {

    private Button mapBackButton;
    private Button event1Button;
    private Button event2Button;
    private Button event3Button;
    private Button event4Button;
    private Button eventBackButton;
    private Button eventNextButton;

    private TextView mapTitleTextView;
    private TextView footerPinkTextView;
    private TextView footerGrayTextView;

    private ImageView event1AnimImageView;
    private ImageView event2AnimImageView;
    private ImageView event3AnimImageView;
    private ImageView event4AnimImageView;

    private ImageView event1ImageView;
    private ImageView event2ImageView;
    private ImageView event3ImageView;
    private ImageView event4ImageView;

    Data data;
    int currEvent = 0;
    private ArrayList<DriveEvent> events = new ArrayList<DriveEvent>();
    private ArrayList<ImageView> animImages = new ArrayList<ImageView>();
    private ArrayList<ImageView> activeButtonImages = new ArrayList<ImageView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        data = new Data();
        events = data.getAllEvents();

        mapBackButton = (Button) findViewById(R.id.mapBackButton);
        event1Button = (Button) findViewById(R.id.event1Button);
        event2Button = (Button) findViewById(R.id.event2Button);
        event3Button = (Button) findViewById(R.id.event3Button);
        event4Button = (Button) findViewById(R.id.event4Button);
        eventBackButton = (Button) findViewById(R.id.eventBackButton);
        eventNextButton = (Button) findViewById(R.id.eventNextButton);

        mapBackButton.setOnClickListener(this);
        event1Button.setOnClickListener(this);
        event2Button.setOnClickListener(this);
        event3Button.setOnClickListener(this);
        event4Button.setOnClickListener(this);
        eventBackButton.setOnClickListener(this);
        eventNextButton.setOnClickListener(this);

        mapTitleTextView = (TextView) findViewById(R.id.mapTitleTextView);
        footerPinkTextView = (TextView) findViewById(R.id.footerPinkTextView);
        footerGrayTextView = (TextView) findViewById(R.id.footerGrayTextView);

        event1AnimImageView = (ImageView) findViewById(R.id.event1AnimImageView);
        event2AnimImageView = (ImageView) findViewById(R.id.event2AnimImageView);
        event3AnimImageView = (ImageView) findViewById(R.id.event3AnimImageView);
        event4AnimImageView = (ImageView) findViewById(R.id.event4AnimImageView);
        animImages.addAll(Arrays.asList(event1AnimImageView,event2AnimImageView,event3AnimImageView,event4AnimImageView));

        event1ImageView = (ImageView) findViewById(R.id.event1ImageView);
        event2ImageView = (ImageView) findViewById(R.id.event2ImageView);
        event3ImageView = (ImageView) findViewById(R.id.event3ImageView);
        event4ImageView = (ImageView) findViewById(R.id.event4ImageView);
        activeButtonImages.addAll(Arrays.asList(event1ImageView,event2ImageView,event3ImageView,event4ImageView));

        updateInfo(getIntent().getIntExtra(Data.EVENT_CODE_TITLE,0));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.maps, menu);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.event1Button:
                updateInfo(0);
                break;
            case R.id.event2Button:
                updateInfo(1);
                break;
            case R.id.event3Button:
                updateInfo(2);
                break;
            case R.id.event4Button:
                updateInfo(3);
                break;
            case R.id.mapBackButton:
                Intent intent = new Intent(MapsActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.eventBackButton:
                if (currEvent > 0 ) {
                    currEvent--;
                    updateInfo(currEvent);
                }
                break;
            case R.id.eventNextButton:
                if (currEvent < events.size() - 1 ) {
                    currEvent++;
                    updateInfo(currEvent);
                }
                break;
        }
    }

    private void updateInfo(int eventId) {
        currEvent = eventId;
        mapTitleTextView.setText(events.get(eventId).getAddressShort());
        footerPinkTextView.setText("OverSpeeding: " + (78 + eventId*3) + " mph");
        footerGrayTextView.setText("Your average speed: " + (50 + (eventId + 1)*0.3*20) + "  site average: " + (50 + (eventId + 1)*0.3*20 - 4.2));

        for (int i = 0; i < animImages.size(); i++) {
            if (i == eventId) {
                animImages.get(i).setVisibility(View.VISIBLE);
                Animation anim = AnimationUtils.loadAnimation(this, R.anim.event_anim);
                animImages.get(i).startAnimation(anim);
            } else {
                animImages.get(i).setVisibility(View.GONE);
            }
        }

        for (int i = 0; i < activeButtonImages.size(); i++) {
            if (i == eventId) {
                activeButtonImages.get(i).setVisibility(View.VISIBLE);
            } else {
                activeButtonImages.get(i).setVisibility(View.GONE);
            }
        }
    }
}
