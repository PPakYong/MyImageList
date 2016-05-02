package myimagelist.yhpark.com.myimagelist;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> imageList = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);

        task.execute();
    }

    AsyncTask task = new AsyncTask() {
        InputStream in;

        @Override
        protected Object doInBackground(Object[] params) {
            Log.i(getClass().getSimpleName(), "doInBackground");
            try {
                Document document = Jsoup.connect("http://www.gettyimagesgallery.com/collections/archive/slim-aarons.aspx").get();
                Elements item = document.getElementsByTag("img");

                for (int i = 0; i < item.size(); i++) {
                    imageList.add(item.get(i).attr("abs:src"));
                }

                return imageList;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            listView.setAdapter(new MyImageAdapter(getApplicationContext(), R.layout.row_image, (ArrayList) o));
        }
    };

    class MyImageAdapter extends ArrayAdapter<String> {
        LayoutInflater inflater;
        public MyImageAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (null == convertView) {
                convertView = new ImageView(getContext());
                ((ImageView) convertView).setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            }

            Glide.with(getContext()).load(getItem(position)).into((ImageView) convertView);
            return convertView;
        }
    }
}
