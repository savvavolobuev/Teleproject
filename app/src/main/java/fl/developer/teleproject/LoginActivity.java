package fl.developer.teleproject;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by alexk on 29.10.2014.
 */
public class LoginActivity extends Activity {

    public static int LOGIN_SUCCESFUL = 0;

    ImageButton closeButton;
    EditText passwordEdit;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        closeButton = (ImageButton) findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.this.finish();
            }
        });
        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setEnabled(false);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.this.setResult(LOGIN_SUCCESFUL);
                finish();
            }
        });
        passwordEdit = (EditText) findViewById(R.id.password);
        passwordEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                 loginButton.setEnabled(editable.length() > 0);
            }
        });
    }
}
