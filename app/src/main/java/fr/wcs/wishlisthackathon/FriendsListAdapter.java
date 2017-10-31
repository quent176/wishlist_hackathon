package fr.wcs.wishlisthackathon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

import static fr.wcs.wishlisthackathon.WishActivity.wishRef;

/**
 * Created by wilder on 30/10/17.
 */

public class FriendsListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ObjectModel> item;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mObjectDatabaseReference;
    String userId, object_id;


    public FriendsListAdapter(Context context, ArrayList<ObjectModel> item){
        this.context = context;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int i) {
        return item.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, final ViewGroup viewGroup) {
        if (convertView == null){
            convertView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.layout_item_friendslist, viewGroup, false);
        }

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        userId= "";
        userId = sharedPreferences.getString("mUserId", userId);
        String description = item.get(i).getObject_description();

        //Initialize Firebase components
        mDatabase = FirebaseDatabase.getInstance();
        mDatabase.getReference().child("Object").orderByChild("object_description").equalTo(description).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren()){
                   object_id = data.getKey().toString();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView buttonOffer = (ImageView) view.findViewById(R.id.buttonOffer);
                buttonOffer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mObjectDatabaseReference = mDatabase.getReference().child("Object").child(object_id);
                        mObjectDatabaseReference.child("object_offered").setValue(true);
                        mObjectDatabaseReference.child("pigeon_user_id").setValue(userId);
                    }
                });
            }
        });

        ObjectModel currentItem = (ObjectModel) getItem(i);
        final int position = i;

        TextView textDescription= convertView.findViewById(R.id.textdescription);
        ImageView imgItem= convertView.findViewById(R.id.imageItem);
        textDescription.setText(currentItem.getObject_description());
        Picasso.with(context).load(currentItem.getObject_image()).resize(450,450).into(imgItem);

        return convertView;
    }

}


