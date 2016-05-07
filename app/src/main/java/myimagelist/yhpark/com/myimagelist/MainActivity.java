package myimagelist.yhpark.com.myimagelist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.GridView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IWebPageLoader{
    private final String pageUrl = "http://www.gettyimagesgallery.com/collections/archive/slim-aarons.aspx";

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private WebPageLoader loader;

    private GridView gridView;
    private GridAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        recyclerView.getRecycledViewPool().setMaxRecycledViews(0, 50);
//        onComplete(new ArrayList<ImageObject>());

        gridView = (GridView) findViewById(R.id.gridView);

        loader = new WebPageLoader(this, this);
        loader.execute(new String[]{pageUrl});
    }

    @Override
    public void onProgress(int values, ImageObject object) {
//        adapter.getItem().add(object);
//        adapter.notifyDataSetChanged();
    }

    @Override
    public void onComplete(List<ImageObject> list) {
    /*
//        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 3);
//        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3,1);
        adapter = new RecyclerAdapter(getApplicationContext(), list);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    */

        gridAdapter = new GridAdapter(getApplicationContext(), R.layout.row_image, list);
        gridView.setAdapter(gridAdapter);
    }
}
