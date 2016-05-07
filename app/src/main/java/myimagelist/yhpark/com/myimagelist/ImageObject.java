package myimagelist.yhpark.com.myimagelist;

/**
 * Created by ppyh0 on 2016-05-07.
 */
public class ImageObject {
    private String imgUrl;

    ImageObject(String imgUrl) {
        setImgUrl(imgUrl);
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
