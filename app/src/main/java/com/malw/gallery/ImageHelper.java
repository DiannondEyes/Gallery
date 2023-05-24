package com.malw.gallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageHelper {
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int inSampleSize = 1;
        if (options.outWidth > reqWidth || options.outHeight > reqHeight) {
            int halfWidth = options.outWidth / 2;
            int halfHeight = options.outHeight / 2;
            while ((halfWidth / inSampleSize) > reqWidth && (halfHeight / inSampleSize) > reqHeight) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    public static Bitmap decodeSampleBitmapFromPath(String pathname, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathname, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(pathname, options);
    }
}