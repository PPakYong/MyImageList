package myimagelist.yhpark.com.myimagelist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import myimagelist.yhpark.com.myimagelist.R;
import myimagelist.yhpark.com.myimagelist.objects.ImageObject;
import myimagelist.yhpark.com.myimagelist.objects.ViewHolder;

/**
 * Created by YHPark on 2016-05-07.
 */
public class ListAdapter extends ArrayAdapter<ImageObject> {

    ViewHolder holder;

    public ListAdapter(Context context, int resource, List<ImageObject> objects) {
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

        //Glide Library 이용해서 web image를 가져온다.
        Glide.with(getContext())
                .load(getItem(position).getImgUrl())    // url을 통해 이미지를 가져온다
                .diskCacheStrategy(DiskCacheStrategy.ALL) // 원본 사이즈를 그대로 캐시에 담는다
//                .asGif()                              // gif 이미지일 경우 호출
//                .thumbnail(0.1f)                      // 원본 이미지의 10%를 먼저 로딩해서 썸네일로 보여준다
                .fitCenter()                            // ScaleType을 fitCenter로 맞춰준다
                .into(holder.getImageView());           // view에 이미지를 넣어준다

        return convertView;
    }
}
