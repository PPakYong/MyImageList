package myimagelist.yhpark.com.myimagelist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by ppyh0 on 2016-05-07.
 */
public class GridAdapter extends ArrayAdapter<ImageObject> {

    ViewHolder holder;

    public GridAdapter(Context context, int resource, List<ImageObject> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_image, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Glide.with(getContext()).load(getItem(position).getImgUrl()).fitCenter().into(holder.getImageView());
        return convertView;
    }
}
