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
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static fr.wcs.wishlisthackathon.R.layout.tab4_friendslists;

public class Tab4_FriendsLists extends Fragment {

    private FriendsListAdapter adapter;
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
        final View rootView = inflater.inflate(tab4_friendslists, container, false);

        final AutoCompleteTextView SearchBarUsers = (AutoCompleteTextView) rootView.findViewById(R.id.autoCompleteSearchUsers);
        final ListView myList = rootView.findViewById(R.id.listViewSearchFriends);

        //Initialize Firebase components
        mDatabase = FirebaseDatabase.getInstance();

        //Get User on Firebase
        mUsersDatabaseReference = mDatabase.getReference().child("User");

        //Auto Suggestion Friends from Database
        mUsersDatabaseReference.orderByChild("user_name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listUsers.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    User myUser = data.getValue(User.class);
                    listUsers.add(myUser.getUser_name());
                }
                usersAdapter = new ArrayAdapter<String>(getActivity(), R.layout.search_box, R.id.tvHintCompletion, listUsers);
                autoCompleteSearchUsers = (AutoCompleteTextView) rootView.findViewById(R.id.autoCompleteSearchUsers);
                autoCompleteSearchUsers.setAdapter(usersAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // Search for a friend in database and display gifts
        Button searchButton = (Button) rootView.findViewById(R.id.buttonSearchFriend);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friendSearched = SearchBarUsers.getText().toString();

                mObjectDatabaseReference = mDatabase.getReference().child("Object");
                mObjectDatabaseReference.orderByChild("object_user_name").equalTo(friendSearched)
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                ArrayList<ObjectModel> wishList = new ArrayList<>();
                                for (DataSnapshot data : dataSnapshot.getChildren()) {
                                    ObjectModel wish = data.getValue(ObjectModel.class);
                                    if (!wish.isObject_offered()){
                                        wishList.add(0, wish);
                                    }
                                }

                                adapter = new FriendsListAdapter(getActivity(), wishList);
                                myList.setAdapter(adapter);
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
