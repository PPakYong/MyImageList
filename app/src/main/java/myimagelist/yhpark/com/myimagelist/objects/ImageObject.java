package myimagelist.yhpark.com.myimagelist.objects;

/**
 * Created by YHPark on 2016-05-07.
 */
public class ImageObject {
    private String imgUrl;
    private String imgNm;

    public ImageObject(String imgUrl, String imgNm) {
        this.imgUrl = imgUrl;
        this.imgNm = imgNm;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getImgNm() {
        return imgNm;
    }
}
