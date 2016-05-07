package myimagelist.yhpark.com.myimagelist.interfaces;

import java.util.List;

import myimagelist.yhpark.com.myimagelist.objects.ImageObject;

/**
 * Created by YHPark on 2016-05-07.
 */
public interface IWebPageLoader {
    /**
     * WebPageLoader가 완료되었을 때 (onPostExecute) 호출
     * @param item
     */
    void onComplete(List<ImageObject> item);
}
