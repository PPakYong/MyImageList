package myimagelist.yhpark.com.myimagelist;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ppyh0 on 2016-05-07.
 */
public class WebPageLoader extends AsyncTask<String, Object, List<ImageObject>> {
    private Context context;
    private IWebPageLoader listener;
    WebPageLoader(Context context, IWebPageLoader listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected List<ImageObject> doInBackground(String... params) {
        if(params == null || params[0] == null) {
            throw new NullPointerException("params is null");
        }

        List<ImageObject> list = new ArrayList<ImageObject>();

        try {
            Document document = Jsoup.connect(params[0]).get();
            Elements item = document.getElementsByTag("img");

            for (int i = 0; i < item.size(); i++) {
                list.add(new ImageObject(item.get(i).attr("abs:src")));
                publishProgress(new Object[] {i, new ImageObject(item.get(i).attr("abs:src"))});
            }
        } catch (MalformedURLException e) {
            Toast.makeText(context, "MalformedURLException", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            Toast.makeText(context, "SocketTimeoutException", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(context, "IOException", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        return list;
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        super.onProgressUpdate(values);
        listener.onProgress((int) values[0], (ImageObject) values[1]);
    }

    @Override
    protected void onPostExecute(List<ImageObject> list) {
        super.onPostExecute(list);
        listener.onComplete(list);
    }
}
