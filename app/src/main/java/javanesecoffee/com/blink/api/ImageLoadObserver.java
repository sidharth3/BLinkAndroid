package javanesecoffee.com.blink.api;

import android.graphics.Bitmap;

public interface ImageLoadObserver {
    void onImageLoad(Bitmap bitmap);
    void onImageLoadFailed(BLinkApiException exception);
}
