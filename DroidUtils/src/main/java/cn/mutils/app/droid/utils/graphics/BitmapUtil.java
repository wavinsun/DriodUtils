package cn.mutils.app.droid.utils.graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

import cn.mutils.app.droid.utils.io.IOUtil;

/**
 * 位图工具
 */
public class BitmapUtil {

    private BitmapUtil() {

    }

    /**
     * 保存位图到指定路径
     *
     * @param bitmap 位图
     * @param path   路径
     * @return 是否保存成功
     */
    public static boolean save(Bitmap bitmap, String path) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            IOUtil.closeQuietly(fos);
        }
    }

    /**
     * 将位图转换为byte数组
     *
     * @param bitmap 位图
     * @return byte数组
     */
    public static byte[] toByteArray(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            return baos.toByteArray();
        } catch (Exception e) {
            return null;
        } finally {
            IOUtil.closeQuietly(baos);
        }
    }

    /**
     * 将位图变灰
     *
     * @param bitmap 位图
     * @return 变灰的位图
     */
    public static Bitmap toGreyBitmap(Bitmap bitmap) {
        Bitmap grey = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(grey);
        Paint p = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        p.setColorFilter(new ColorMatrixColorFilter(cm));
        c.drawBitmap(bitmap, 0, 0, p);
        return grey;
    }

    /**
     * 将显示资源变灰
     *
     * @param drawable 显示资源
     * @return 灰色资源
     */
    public static Drawable toGreyDrawable(Drawable drawable) {
        int w = drawable.getMinimumWidth();
        int h = drawable.getMinimumHeight();
        if (w <= 0 || h <= 0) {
            return drawable;
        }
        Rect bounds = drawable.getBounds();
        Bitmap grey = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(grey);
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        drawable.setColorFilter(new ColorMatrixColorFilter(cm));
        drawable.setBounds(0, 0, w, h);
        drawable.draw(c);
        drawable.clearColorFilter();
        drawable.setBounds(bounds);
        BitmapDrawable bd = new BitmapDrawable(grey);
        bd.setBounds(0, 0, w, h);
        return bd;
    }

    /**
     * 将视图转换为位图
     *
     * @param v 视图
     * @return 位图
     */
    public static Bitmap toBitmap(View v) {
        int w = v.getWidth();
        int h = v.getHeight();
        if (w <= 0 || h <= 0) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }

    /**
     * 将显示资源转换为位图
     *
     * @param drawable 显示资源
     * @return 位图
     */
    public static Bitmap toBitmap(Drawable drawable) {
        int w = drawable.getMinimumWidth();
        int h = drawable.getMinimumHeight();
        if (w <= 0 || h <= 0) {
            return null;
        }
        Rect bounds = drawable.getBounds();
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        drawable.setBounds(bounds);
        return bitmap;
    }
}
