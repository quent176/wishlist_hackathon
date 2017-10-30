package fr.wcs.wishlisthackathon;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

import static fr.wcs.wishlisthackathon.R.layout.tab3_tooffer;

public class Tab3_ToOffer extends Fragment {


    private WishAdapter adapter;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mUsersDatabaseReference;

    private ArrayAdapter<String> usersAdapter = null;
    private AutoCompleteTextView autoCompleteSearchUsers;
    private ArrayList<String> listUsers = new ArrayList<>();

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

                adapter = new WishAdapter(getActivity(), wishList);
                if(wishList.size() > 0){
                    //   mBeMyFirst.setVisibility(View.GONE);
                }
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

        return rootView;
    }
}
