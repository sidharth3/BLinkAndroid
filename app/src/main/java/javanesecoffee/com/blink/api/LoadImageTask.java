package javanesecoffee.com.blink.api;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javanesecoffee.com.blink.constants.Config;
import javanesecoffee.com.blink.constants.Endpoints;
import javanesecoffee.com.blink.helpers.RequestHandler;

public class LoadImageTask extends AsyncTask<String, Void, Bitmap> {

    BLinkApiException exception;
    ImageLoadObserver delegate;

    public LoadImageTask(ImageLoadObserver delegate)
    {
        this.delegate = delegate;
    }


    @Override
    protected Bitmap doInBackground(String... params) {
        String id = params[0];
        String endpoint = params[1];
        String url_extension = endpoint + id;

        Bitmap image = null;
        try {
            image = RequestHandler.GetImage(url_extension);
        } catch (BLinkApiException e) {
            this.exception = e;
        }
        return image;
    }


    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        if(delegate != null)
        {
            if(exception == null)
            {
                delegate.onImageLoad(bitmap);
            }
            else
            {
                delegate.onImageLoadFailed(this.exception);
            }
        }
    }
}
