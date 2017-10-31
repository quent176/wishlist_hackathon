package fr.wcs.wishlisthackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ModifyActivity extends AppCompatActivity {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mObjectDatabaseReference;
    private String mUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        //récupération de l'objet via Intent
        ObjectModel wish = getIntent().getExtras().getParcelable("wish");

        //déclaration variable et récupération données via getter
        final String photo = wish.getObject_image();
        String description = wish.getObject_description();
        String lien = wish.getObject_url();

        //Find id
        final TextView editTextDescription = (TextView) findViewById(R.id.descriptionImage);
        final TextView editTextURL = (TextView) findViewById(R.id.linkImage);
        ImageView showImage = (ImageView) findViewById(R.id.wishImage);

        //input
        editTextDescription.setText(description);
        editTextURL.setText(lien);
        Picasso.with(this).load(photo).into(showImage);

        //Initialize Firebase components
        mDatabase = FirebaseDatabase.getInstance();
        mObjectDatabaseReference = mDatabase.getReference().child("Object");

        //Get Object on Firebase
        mObjectDatabaseReference.orderByChild("object_description").equalTo(description).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    mUid = child.getKey().toString();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Button buttonModify = (Button) findViewById(R.id.buttonModify);
        buttonModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mObjectDatabaseReference.child(mUid).child("object_description").setValue(editTextDescription.getText().toString());
                mObjectDatabaseReference.child(mUid).child("object_url").setValue(editTextURL.getText().toString());
              //  mObjectDatabaseReference.child(mUid).child("object_image").setValue(editTextURL.getText().toString());
                Toast.makeText(ModifyActivity.this, "Nouvelle description enregistrée",Toast.LENGTH_SHORT).show();
            }
        });

        Button buttonCancel = (Button) findViewById(R.id.cancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), WishActivity.class));
            }
        });

    }
}
