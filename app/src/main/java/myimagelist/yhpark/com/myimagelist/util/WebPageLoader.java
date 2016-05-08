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
            //html 문서를 읽어온다.
            Document document = Jsoup.connect(params[0]).get();

            /*gif와 text image까지 모두 가져올 경우

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
            }*/


            //slider 내에 있는 이미지는 id값을 이용해서 element를 가져온다.
            Elements element = document.getElementById("slider").getElementsByAttribute("src");
            for (int i = 0; i < element.size(); i++) {
                ImageObject object = new ImageObject(element.get(i).attr("abs:src"), "slider image " + (i + 1));
                list.add(object);
            }

            /*중앙 메인 이미지들과 그 link에 기록된 text를 가져올 경우
            <body>
                <form name="apsnetForm"  ...>
                   <div id="middle-content-bg">
                     <div id="middle-content-main">
                         <div class="content">
                             <div class="fullwidthbordertop">
                                 <div class="pageimageheaders">
                                    <div class="gallery-wrap exitemrepeater">
                                        <div class="gallery-item-group exitemrepeater">    <-- 이 곳에 정보가 기록되어 있다. */


            Elements imgList = document.getElementsByClass("gallery-item-group");
            dialog.setMax(imgList.size());

            for (int i = 0; i < imgList.size(); i++) {
                Thread.sleep(1);
                ImageObject object = new ImageObject(imgList.get(i).getElementsByAttribute("src").attr("abs:src"), imgList.get(i).getElementsByAttribute("href").get(1).text());
                list.add(object);
                publishProgress(new Object[]{i, object});
            }
        } catch (MalformedURLException e) {
            publishProgress(new Object[]{"MalformedURLException"});
            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            publishProgress(new Object[]{"SocketTimeoutException"});
            e.printStackTrace();
        } catch (IOException e) {
            publishProgress(new Object[]{"IOException"});
            e.printStackTrace();
        } catch (InterruptedException e) {
            publishProgress(new Object[]{"InterruptedException"});
            e.printStackTrace();
        }

        return list;
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        super.onProgressUpdate(values);

        if (values[0] instanceof String) {
            Toast.makeText(context, (String) values[0], Toast.LENGTH_SHORT).show();
        } else if (values[0] instanceof Integer) {
            dialog.setProgress((int) values[0]);
        }


    }

    @Override
    protected void onPostExecute(List<ImageObject> list) {
        super.onPostExecute(list);
        dialog.dismiss();
        listener.onComplete(list);
    }
}
