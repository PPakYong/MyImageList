package myimagelist.yhpark.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import myimagelist.yhpark.main.databinding.ActivityMainBinding;
import myimagelist.yhpark.main.databinding.RowImageBinding;

/**
 * Created by YHPark on 2016-05-02.
 */
public class MainActivity extends AppCompatActivity implements IWebPageLoader {
    private final String pageUrl = "http://www.gettyimagesgallery.com/collections/archive/slim-aarons.aspx";

    private WebPageLoader loader;
    private ActivityMainBinding binding;

    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        loader = new WebPageLoader(this);
        loader.execute(new String[]{pageUrl});
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("list", list);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.getStringArrayList("list") != null) {
            onComplete(savedInstanceState.getStringArrayList("list"));
        }
    }

    @Override
    public void onComplete(final List<String> list) {
        this.list = (ArrayList<String>) list;

        binding.recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 4));
        binding.recyclerView.setAdapter(new RecyclerView.Adapter<ViewHolder>() {
            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new ViewHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.row_image, parent, false));
            }

            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                Glide.with(getApplicationContext()).load(list.get(position)).into(holder.rowImageBinding.imageView);
            }

            @Override
            public int getItemCount() {
                return list.size();
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public RowImageBinding rowImageBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            rowImageBinding = DataBindingUtil.bind(itemView);
        }
    }
}
