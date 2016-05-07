package myimagelist.yhpark.com.myimagelist;

import java.util.List;

/**
 * Created by ppyh0 on 2016-05-07.
 */
public interface IWebPageLoader {
    void onProgress(int values, ImageObject list);
    void onComplete(List<ImageObject> list);
}
