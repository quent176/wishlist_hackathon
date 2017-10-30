package fr.wcs.wishlisthackathon;

/**
 * Created by apprenti on 10/30/17.
 */

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static fr.wcs.wishlisthackathon.R.layout.tab1_wishes;
import static fr.wcs.wishlisthackathon.WishActivity.wishRef;


public class Tab1_Wishes extends Fragment {

    private RecyclerView.Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(tab1_wishes, container, false);

        // Recycler View
        final RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView1);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView.LayoutManager mLayoutManager;
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String userId= "";
        userId = sharedPreferences.getString("mUserId", userId);
        Log.e("WISHES",userId );

        wishRef.orderByChild("object_user_id").equalTo(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("WISHES", "caca" );

                ArrayList<ObjectModel> wishList = new ArrayList<>();

                for (DataSnapshot data : dataSnapshot.getChildren()){

                    ObjectModel myObjectModel = data.getValue(ObjectModel.class);
                    Log.e("WISHES",myObjectModel.getObject_description() );
                    Log.e("WISHES", "prout" );

                    wishList.add(0, myObjectModel);
                    Log.e("WISHES",String.valueOf(wishList.size()) );
                }

                // TODO creating adapter
              //  adapter = new WishAdapter(getActivity(), wishList);
                if(wishList.size() > 0){
                 //   mBeMyFirst.setVisibility(View.GONE);
                }

                //adding adapter to recyclerview
              //  recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        // Floating Button
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AddWishActivity.class));
            }
        });

        return rootView;


    }
}
