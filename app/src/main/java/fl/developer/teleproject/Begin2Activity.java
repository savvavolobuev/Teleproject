package fl.developer.teleproject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;

public class Begin2Activity extends Activity {

    public static final int LOGIN_CODE = 0;
    ArrayList<Begin2Item> itemsGeneral = new ArrayList<Begin2Item>();
    ArrayList<Begin2Item> itemsAdditional = new ArrayList<Begin2Item>();
    Begin2Adapter begin2AdapterGeneral;
    Begin2Adapter begin2AdapterAdditional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin2);
        //** ActionBar
        final ViewGroup actionBarLayout = (ViewGroup) getLayoutInflater().inflate(
                R.layout.action_bar, null);

        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(actionBarLayout);

        // инициализация данных
        fillData();

        // ** первый список
        // создаем адаптер
        begin2AdapterGeneral = new Begin2Adapter(this, itemsGeneral, Begin2Adapter.GENERAL_LIST_MODE);

        // настраиваем список
        ListView listView1 = (ListView) findViewById(R.id.begin2_listView1);
        listView1.setAdapter(begin2AdapterGeneral);

        // ** второй список
        // создаем адаптер
        begin2AdapterAdditional = new Begin2Adapter(this, itemsAdditional, Begin2Adapter.ADDITOINAL_LIST_MODE);

        // настраиваем список
        ListView listView2 = (ListView) findViewById(R.id.begin2_listView2);
        listView2.setAdapter(begin2AdapterAdditional);
    }

    // генерируем данные для адаптера
    void fillData() {
        itemsGeneral.add(new Begin2Item( R.drawable.face_1, "Diego Alves", "Tomorrow, 17:32", ""));
        itemsGeneral.add(new Begin2Item( R.drawable.face_2, "Alve Diegos", "26 October, 9:21", ""));

        itemsAdditional.add((new Begin2Item( R.drawable.face_3, "Rafael Cabral", "", "52,1")));
        itemsAdditional.add((new Begin2Item( R.drawable.face_4, "Thiago Silva", "", "52,6")));
        itemsAdditional.add((new Begin2Item( R.drawable.face_5, "David Luiz", "", "55,2")));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.begin2, menu);
        return false;
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(LOGIN_CODE == requestCode && LoginActivity.LOGIN_SUCCESFUL == resultCode) {
            Intent intent = new Intent(Begin2Activity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
