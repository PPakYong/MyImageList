package myimagelist.yhpark.com.myimagelist;

import android.view.View;
import android.widget.ImageView;

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
