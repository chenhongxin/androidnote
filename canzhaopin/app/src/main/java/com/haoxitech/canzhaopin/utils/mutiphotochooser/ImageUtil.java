package com.haoxitech.canzhaopin.utils.mutiphotochooser;

import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.haoxitech.canzhaopin.app.AppContext;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangtao on 16/1/31.
 */
public class ImageUtil {
    // 生成输出文件
    public static File getOutFile() {

        String storageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_REMOVED.equals(storageState)) {
            Toast.makeText(AppContext.getInstance(), "SD卡不存在",
                    Toast.LENGTH_SHORT).show();
            return null;
        }

        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "ZhaoWaiBao");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.i("ImageUtil", "创建图片存储路径目录失败");
                Log.i("ImageUtil",
                        "mediaStorageDir : " + mediaStorageDir.getPath());
                return null;
            }
        }

        File file = new File(getFilePath(mediaStorageDir));
        return file;
    }

    // 生成输出文件路径
    public static String getFilePath(File mediaStorageDir) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new Date());
        String filePath = mediaStorageDir.getPath() + File.separator;
        filePath += ("IMG_" + timeStamp + ".jpg");

        return filePath;
    }
}
