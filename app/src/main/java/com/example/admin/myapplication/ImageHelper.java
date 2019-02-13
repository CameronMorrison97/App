package com.example.admin.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v4.content.ContextCompat;
import android.view.View;

// Need to create an abstract class as I'm only using two static variables their in no point making an instance of the class.
// Also I'm making a seperate class with two static variables so I can reuse the screenShot code incase we want to use it in multiple activities.
public abstract class ImageHelper {

    // Variable that will be used to store our bitmap image of the screenshot
    // This image can be null in case the user rejects the ability to read/write to the file system.
    // This might cause an exception you have been warned.
    public static Bitmap btMap = null;

    /* Code 'borrowed' form JustinMorris. On Stack Overflow: https://stackoverflow.com/questions/2661536/how-to-programmatically-take-a-screenshot*/
    /* Returns the current view as a bitmap image. Can return null if the permissions have been denied.
     * In this case the background for the config screen will just be a single colour.*/
    public static Bitmap screenShot(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(),
                view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }
}
