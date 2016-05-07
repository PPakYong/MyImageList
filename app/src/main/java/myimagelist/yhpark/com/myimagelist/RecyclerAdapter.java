package myimagelist.yhpark.com.myimagelist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ppyh0 on 2016-05-07.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<ImageObject> item = new ArrayList<ImageObject>();
    private Context context = null;

    RecyclerAdapter(Context context, List<ImageObject> item) {
        this.item = item;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_image, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(item.get(position).getImgUrl()).fitCenter().into(holder.getImageView());
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public List<ImageObject> getItem() {
        return item;
    }
}
