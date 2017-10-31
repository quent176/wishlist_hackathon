package fr.wcs.wishlisthackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ModifyActivity extends AppCompatActivity {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mObjectDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        //récupération de l'objet via Intent
        ObjectModel wish = getIntent().getExtras().getParcelable("wish");

        //déclaration variable et récupération données via getter
        String photo = wish.getObject_image();
        String description = wish.getObject_description();
        String lien = wish.getObject_url();

        //Find id
        TextView editTextDescription = (TextView) findViewById(R.id.descriptionImage);
        TextView editTextURL = (TextView) findViewById(R.id.linkImage);
        ImageView showImage = (ImageView) findViewById(R.id.wishImage);

        //input
        editTextDescription.setText(description);
        editTextURL.setText(lien);
        Picasso.with(this).load(photo).into(showImage);

        //Initialize Firebase components
        mDatabase = FirebaseDatabase.getInstance();
        mObjectDatabaseReference = mDatabase.getReference().child("Object");

        //Get Object on Firebase



    }
}
