package myimagelist.yhpark.com.myimagelist;

import java.util.List;

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
