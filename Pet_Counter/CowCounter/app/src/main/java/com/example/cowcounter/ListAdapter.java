package com.example.cowcounter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Imtiaz on 15.11.2019.
 */

public class ListAdapter extends ArrayAdapter<CowDTO> {
    private Context context;
    int resource;

    public ListAdapter(Context context, int resource, ArrayList<CowDTO> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int id = getItem(position).getId();
        int breed = getItem(position).getBreed();

        convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        TextView breedText = (TextView) convertView.findViewById(R.id.breed_list);
        TextView idText = (TextView) convertView.findViewById(R.id.id_list);

        breedText.setText(Integer.toString(breed));
        idText.setText(Integer.toString(id));

        return convertView;
    }
}
