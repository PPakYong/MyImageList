package myimagelist.yhpark.main;

import android.content.Context;
import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YHPark on 2016-05-07.
 */
public class WebPageLoader extends AsyncTask<String, Object, List<String>> {
    private IWebPageLoader listener;

    public WebPageLoader(IWebPageLoader listener) {
        this.listener = listener;
    }

    @Override
    protected List<String> doInBackground(String... params) {
        List<String> list = new ArrayList<>();

        try {
            Document document = Jsoup.connect(params[0]).get();
            Elements item = document.select("img");

            for (int i = 0; i < item.size(); i++) {
                list.add(item.get(i).attr("abs:src"));
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    protected void onPostExecute(List<String> list) {
        super.onPostExecute(list);
        listener.onComplete(list);
    }
}
