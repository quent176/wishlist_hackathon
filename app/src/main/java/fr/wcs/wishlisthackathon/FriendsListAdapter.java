package fr.wcs.wishlisthackathon;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
            public void onClick(final View view) {
                ImageView buttonOffer = (ImageView) view.findViewById(R.id.buttonOffer);
                buttonOffer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());

                        // Setting Dialog Title
                        alertDialog.setTitle("Offrir un super cadeau");

                        // Setting Dialog Message
                        alertDialog.setMessage("Voulez-vous vraiment payer un tel cadeau à " + item.get(i).getObject_user_name() + " ?");
                        // Setting Positive "Yes" Button
                        alertDialog.setPositiveButton("Oui, j'ai pitié", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int which) {
                                mObjectDatabaseReference = mDatabase.getReference().child("Object").child(object_id);
                                mObjectDatabaseReference.child("object_offered").setValue(true);
                                mObjectDatabaseReference.child("pigeon_user_id").setValue(userId);
                                Toast.makeText(view.getContext(), "Tant pis, je mangerai des pates", Toast.LENGTH_LONG).show();
                            }
                        });

                        // Setting Negative "NO" Button
                        alertDialog.setNegativeButton("En fait, non, j'acheterai un bout de pain", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to invoke NO event
                                Toast.makeText(view.getContext(), "Bon choix, il ne méritait pas", Toast.LENGTH_LONG).show();
                                dialog.cancel();
                            }
                        });

                        // Showing Alert Message
                        alertDialog.show();
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


