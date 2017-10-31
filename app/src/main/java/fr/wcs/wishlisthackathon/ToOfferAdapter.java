package fr.wcs.wishlisthackathon;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

/**
 * Created by wilder on 30/10/17.
 */

public class ToOfferAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ObjectModel> item;

    public ToOfferAdapter(Context context, ArrayList<ObjectModel> item){
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
                    .inflate(R.layout.layout_item_tooffer, viewGroup, false);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectModel itemObject = (ObjectModel) item.get(i);
                Intent intent = new Intent(viewGroup.getContext(), ModifyActivity.class);
                intent.putExtra("wish", itemObject);
                viewGroup.getContext().startActivity(intent);
            }
        });

        ObjectModel currentItem = (ObjectModel) getItem(i);
        final int position = i;

        TextView textDescription= convertView.findViewById(R.id.textdescription);
        ImageView imgItem= convertView.findViewById(R.id.imageItem);
        textDescription.setText(currentItem.getObject_description());
        Picasso.with(context).load(currentItem.getObject_image()).resize(450,450).into(imgItem);
        TextView textNameToOffer = convertView.findViewById(R.id.TextViewNameOfferTo);
        textNameToOffer.setText("Pour : \n" +  currentItem.getObject_user_name());

        return convertView;
    }

}


