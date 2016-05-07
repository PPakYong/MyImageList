package myimagelist.yhpark.com.myimagelist.util;

import android.app.ProgressDialog;
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

import myimagelist.yhpark.com.myimagelist.interfaces.IWebPageLoader;
import myimagelist.yhpark.com.myimagelist.objects.ImageObject;

/**
 * Created by YHPark on 2016-05-07.
 */
public class WebPageLoader extends AsyncTask<String, Object, List<ImageObject>> {
    private Context context;
    private IWebPageLoader listener;

    private ProgressDialog dialog;

    public WebPageLoader(Context context, IWebPageLoader listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        dialog = new ProgressDialog(context);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCancelable(false);
        dialog.setMessage("Web page is parsing...");
        dialog.show();
    }

    @Override
    protected List<ImageObject> doInBackground(String... params) {
        if (params == null || params[0] == null) {
            throw new NullPointerException("params is null");
        }

        List<ImageObject> list = new ArrayList<ImageObject>();

        try {
            Document document = Jsoup.connect(params[0]).get();
            Elements item = document.getElementsByTag("img");

            dialog.setMax(item.size());

            for (int i = 0; i < item.size(); i++) {
                Thread.sleep(1);
                ImageObject object = new ImageObject(item.get(i).attr("abs:src"));
                if (!object.getImgUrl().endsWith(".gif")) {
                    list.add(object);
                }
                publishProgress(new Object[]{i, object});
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
        } catch (InterruptedException e) {
            Toast.makeText(context, "InterruptedException", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        return list;
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        super.onProgressUpdate(values);
        dialog.setProgress((int) values[0]);
    }

    @Override
    protected void onPostExecute(List<ImageObject> list) {
        super.onPostExecute(list);
        dialog.dismiss();
        listener.onComplete(list);
    }
}
