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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static fr.wcs.wishlisthackathon.R.layout.tab1_wishes;
import static fr.wcs.wishlisthackathon.WishActivity.wishRef;


public class Tab1_Wishes extends Fragment {

    private WishAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(tab1_wishes, container, false);


        final ListView myList = rootView.findViewById(R.id.listShit);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String userId= "";
        userId = sharedPreferences.getString("mUserId", userId);
        Log.e("WISHES",userId );

        wishRef.orderByChild("object_user_id").equalTo(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final ArrayList<ObjectModel> wishList = new ArrayList<>();

                for (DataSnapshot data : dataSnapshot.getChildren()){

                    ObjectModel myObjectModel = data.getValue(ObjectModel.class);
                    Log.e("WISHES",myObjectModel.getObject_description() );
                    Log.e("WISHES", "et un objet! un!" );

                    wishList.add(0, myObjectModel);
                    Log.e("WISHES",String.valueOf(wishList.size()) );
                }

                adapter = new WishAdapter(getActivity(), wishList);

                myList.setAdapter(adapter);
                Log.e("WISHES", "adapter added" );

                myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        ObjectModel item = wishList.get(i);
                        Intent intent = new Intent(getActivity(), ModifyActivity.class);
                        intent.putExtra("objet", item);
                        startActivity(intent);
                    }
                });

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
