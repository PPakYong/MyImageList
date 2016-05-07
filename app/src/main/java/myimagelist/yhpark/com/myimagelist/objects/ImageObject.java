package myimagelist.yhpark.com.myimagelist.objects;

/**
 * Created by YHPark on 2016-05-07.
 */
public class ImageObject {
    private String imgUrl;

    public ImageObject(String imgUrl) {
        setImgUrl(imgUrl);
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
