package fr.wcs.wishlisthackathon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddWishActivity extends AppCompatActivity {

    ImageView wishImage;
    EditText descriptionImage, linkImage;
    String descriptionImageContent, linkImageContent;
    Button cancel, create;
    String mUserId, userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wish);

        // Retour à la page précédente
        cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), WishActivity.class));
            }
        });

        // Lien PopUp
        wishImage = findViewById(R.id.wishImage);
        wishImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddWishActivity.this, AddWishActivity_PopUp.class);
                startActivity(intent);
            }
        });

        // Pour recuperer la key d'un user (pour le lier a une quête)
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mUserId = sharedPreferences.getString("mUserId", mUserId);
        userName = sharedPreferences.getString("NameKey", userName);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Object");

        create = findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                descriptionImage = findViewById(R.id.descriptionImage);
                linkImage = findViewById(R.id.linkImage);
                descriptionImageContent = descriptionImage.getText().toString();
                linkImageContent = linkImage.getText().toString();

                ObjectModel objectModel = new ObjectModel(descriptionImageContent, linkImageContent,
                        false, mUserId, userName, "null");

                myRef.push().setValue(objectModel);

                startActivity(new Intent(getApplicationContext(), WishActivity.class));
            }
        });
    }
}
