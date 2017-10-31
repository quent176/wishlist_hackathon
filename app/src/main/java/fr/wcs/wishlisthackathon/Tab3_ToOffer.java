package fr.wcs.wishlisthackathon;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static fr.wcs.wishlisthackathon.R.layout.tab3_tooffer;
import static fr.wcs.wishlisthackathon.WishActivity.wishRef;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Tab3_ToOffer extends Fragment {


    private ToOfferAdapter adapter;
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

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String userId= "";
        userId = sharedPreferences.getString("mUserId", userId);

        final ListView myList = rootView.findViewById(R.id.listOffrir);

        wishRef.orderByChild("pigeon_user_id").equalTo(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<ObjectModel> wishList = new ArrayList<>();
                for (DataSnapshot photoSnapshot : dataSnapshot.getChildren()){
                    ObjectModel myObjectModel = photoSnapshot.getValue(ObjectModel.class);
                    wishList.add(0, myObjectModel);
                }

                adapter = new ToOfferAdapter(getActivity(), wishList);
                myList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        //Initialize Firebase components
        mDatabase = FirebaseDatabase.getInstance();

        //Get User on Firebase
        mUsersDatabaseReference = mDatabase.getReference().child("User");


        return rootView;
    }
}
