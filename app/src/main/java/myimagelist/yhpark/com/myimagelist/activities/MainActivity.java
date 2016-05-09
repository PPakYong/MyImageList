package myimagelist.yhpark.com.myimagelist.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.List;

import myimagelist.yhpark.com.myimagelist.R;
import myimagelist.yhpark.com.myimagelist.util.WebPageLoader;
import myimagelist.yhpark.com.myimagelist.adapter.ListAdapter;
import myimagelist.yhpark.com.myimagelist.interfaces.IWebPageLoader;
import myimagelist.yhpark.com.myimagelist.objects.ImageObject;

/**
 * Created by YHPark on 2016-05-02.
 */
public class MainActivity extends AppCompatActivity implements IWebPageLoader, AdapterView.OnItemClickListener {
    private final String pageUrl = "http://www.gettyimagesgallery.com/collections/archive/slim-aarons.aspx";

    private WebPageLoader loader;

    private GridView gridView;
    private ListAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridView);

        loader = new WebPageLoader(this, this);
        loader.execute(new String[]{pageUrl});
    }

    @Override
    public void onComplete(List<ImageObject> list) {
        gridAdapter = new ListAdapter(getApplicationContext(), R.layout.row_image, list);
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(MainActivity.this, "image : " + gridAdapter.getItem(position).getImgNm(), Toast.LENGTH_SHORT).show();
    }
}
