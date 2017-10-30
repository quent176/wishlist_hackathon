package fr.wcs.wishlisthackathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    final String userName = "NameKey";
    final String userPassword = "PasswordKey";
    private String mUserId = "UserKey";
    private String mEncrypt = "encrypt";
    private boolean auth = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editTextUserName = findViewById(R.id.connexionUserName);
        final EditText editTextUserPassword = findViewById(R.id.connexionUserPassword);
        Button buttonSend = findViewById(R.id.buttonConnexionSend);
    }
}
