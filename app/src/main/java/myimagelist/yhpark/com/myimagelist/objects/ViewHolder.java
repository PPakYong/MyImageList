package myimagelist.yhpark.com.myimagelist.objects;

import android.view.View;
import android.widget.ImageView;

import myimagelist.yhpark.com.myimagelist.R;

/**
 * Created by YHPark on 2016-05-07.
 */
public class ViewHolder { //extends RecyclerView.ViewHolder
    private ImageView imageView;

    public ImageView getImageView() {
        return imageView;
    }

    public ViewHolder(View itemView) {
//        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
    }
}
