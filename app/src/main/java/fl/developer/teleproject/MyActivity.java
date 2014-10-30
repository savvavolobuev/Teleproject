package fl.developer.teleproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MyActivity extends Activity {

    public static final int LOGIN_REQUEST = 0;

    private Button toBegin2Button;

    private Button testButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        toBegin2Button = (Button) findViewById(R.id.toBegin2Button);
        toBegin2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(MyActivity.this, Begin1Activity.class);
                startActivity(newIntent);
            }
        });
        testButton = (Button) findViewById(R.id.loginTestButton);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyActivity.this, LoginActivity.class);
                startActivityForResult(intent, LOGIN_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (LOGIN_REQUEST == requestCode && LoginActivity.LOGIN_SUCCESFUL == resultCode) {
            Intent intent = new Intent(MyActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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
}
