package fr.wcs.wishlisthackathon;

import android.os.Bundle;
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

/**
 * Created by apprenti on 10/30/17.
 */

public class Tab3_ToOffer extends Fragment {

    private RecyclerView.Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(tab3_tooffer, container, false);

        // TODO récupérer le user id
        final String userId = "bibi";

        // Recycler View
        final RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView3);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView.LayoutManager mLayoutManager;
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);

        wishRef.orderByChild("pigeon_user_id").equalTo(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<ObjectModel> wishList = new ArrayList<>();
                for (DataSnapshot photoSnapshot : dataSnapshot.getChildren()){
                    ObjectModel myObjectModel = photoSnapshot.getValue(ObjectModel.class);
                    wishList.add(0, myObjectModel);
                }

                // TODO creating adapter
                adapter = new WishAdapter(getActivity(), wishList);
                if(wishList.size() > 0){
                    //   mBeMyFirst.setVisibility(View.GONE);
                }

                //adding adapter to recyclerview
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return rootView;
    }
}
