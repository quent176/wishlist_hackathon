package fr.wcs.wishlisthackathon;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static fr.wcs.wishlisthackathon.R.layout.tab3_tooffer;

public class Tab3_ToOffer extends Fragment {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mUsersDatabaseReference;
    private DatabaseReference mObjectDatabaseReference;

    private ArrayAdapter<String> usersAdapter = null;
    private AutoCompleteTextView autoCompleteSearchUsers;
    private ArrayList<String> listUsers = new ArrayList<>();

    private String friendSearched;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(tab3_tooffer, container, false);
        final AutoCompleteTextView SearchBarUsers = (AutoCompleteTextView) rootView.findViewById(R.id.autoCompleteSearchUsers);

        //Initialize Firebase components
        mDatabase = FirebaseDatabase.getInstance();

        //Get User on Firebase
        mUsersDatabaseReference = mDatabase.getReference().child("User");

        mUsersDatabaseReference.orderByChild("user_name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listUsers.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    User myUser = data.getValue(User.class);
                    listUsers.add(myUser.getUser_name());
                    Log.d("TAG", myUser.getUser_name());
                }
                usersAdapter = new ArrayAdapter<String>(getActivity(), R.layout.search_box, R.id.tvHintCompletion, listUsers);
                autoCompleteSearchUsers = (AutoCompleteTextView) rootView.findViewById(R.id.autoCompleteSearchUsers);
                autoCompleteSearchUsers.setAdapter(usersAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Button searchButton = (Button) rootView.findViewById(R.id.button2);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friendSearched = SearchBarUsers.getText().toString();
                Log.d("TAG", friendSearched);

                mObjectDatabaseReference = mDatabase.getReference().child("Object");
                mObjectDatabaseReference.orderByChild("object_user_name").equalTo(friendSearched)
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                for (DataSnapshot data : dataSnapshot.getChildren()) {
                                    ObjectModel wish = data.getValue(ObjectModel.class);
                                    wish.getObject_image();
                                    ImageButton imageTest = (ImageButton) rootView.findViewById(R.id.imageButton1);
                                    Picasso.with(getContext()).load(wish.getObject_image()).into(imageTest);
                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

            }
        });




        return rootView;
    }
}
