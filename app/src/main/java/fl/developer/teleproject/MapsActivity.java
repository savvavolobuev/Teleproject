package fl.developer.teleproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import fl.developer.teleproject.model.Data;


public class MapsActivity extends Activity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mapBackButton = (Button) findViewById(R.id.mapBackButton);
        event1Button = (Button) findViewById(R.id.event1Button);
        event2Button = (Button) findViewById(R.id.event2Button);
        event3Button = (Button) findViewById(R.id.event3Button);
        event4Button = (Button) findViewById(R.id.event4Button);
        eventBackButton = (Button) findViewById(R.id.eventBackButton);
        eventNextButton = (Button) findViewById(R.id.eventNextButton);

        mapTitleTextView = (TextView) findViewById(R.id.mapTitleTextView);
        footerPinkTextView = (TextView) findViewById(R.id.footerPinkTextView);
        footerGrayTextView = (TextView) findViewById(R.id.footerGrayTextView);

        event1AnimImageView = (ImageView) findViewById(R.id.event1AnimImageView);
        event2AnimImageView = (ImageView) findViewById(R.id.event2AnimImageView);
        event3AnimImageView = (ImageView) findViewById(R.id.event3AnimImageView);
        event4AnimImageView = (ImageView) findViewById(R.id.event4AnimImageView);

        event1ImageView = (ImageView) findViewById(R.id.event1ImageView);
        event2ImageView = (ImageView) findViewById(R.id.event2ImageView);
        event3ImageView = (ImageView) findViewById(R.id.event3ImageView);
        event4ImageView = (ImageView) findViewById(R.id.event4ImageView);

        //updateInfo(getIntent().getIntExtra(Data.CATEGORY_CODE_TITLE,0), getIntent().getIntExtra(Data.EVENT_CODE_TITLE,0) );
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

    private void updateInfo(int groupPosition, int childPosition ) {

    }
}
