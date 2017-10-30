package fr.wcs.wishlisthackathon;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
// import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
// import com.firebase.ui.storage.images.FirebaseImageLoader;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by wilder on 30/10/17.
 */

public class WishAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ObjectModel> item;

    public WishAdapter(Context context, ArrayList<ObjectModel> item){
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
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (convertView == null){
            convertView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.layout_item, viewGroup, false);
        }

        ObjectModel currentItem = (ObjectModel) getItem(i);
        final int position = i;

        TextView textDescription= convertView.findViewById(R.id.textdescription);
        ImageView imgItem= convertView.findViewById(R.id.imageItem);
        textDescription.setText(currentItem.getObject_description());
        Picasso.with(context).load(currentItem.getObject_image()).resize(450,450).into(imgItem);

        return convertView;
    }

}
