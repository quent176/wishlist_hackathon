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
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import static fr.wcs.wishlisthackathon.R.layout.tab2_offered;
import static fr.wcs.wishlisthackathon.WishActivity.wishRef;


public class Tab2_Offered extends Fragment {

    private WishAdapter adapter;

    String userId= "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(tab2_offered, container, false);


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        userId = sharedPreferences.getString("mUserId", userId);

        final ListView myList = rootView.findViewById(R.id.listOffered);

        wishRef.orderByChild("object_offered").equalTo(true).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<ObjectModel> wishList = new ArrayList<>();
                for (DataSnapshot photoSnapshot : dataSnapshot.getChildren()){
                    ObjectModel myObjectModel = photoSnapshot.getValue(ObjectModel.class);
                    if(myObjectModel.getObject_user_id().equals(userId)){
                        wishList.add(0, myObjectModel);
                    }
                }

                // TODO creating adapter
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
        ImageView testPicasso = (ImageView) rootView.findViewById(R.id.imageViewTest1);
        Picasso.with(getContext()).load("http://i.imgur.com/DvpvklR.png").into(testPicasso);

        return rootView;
    }
}
