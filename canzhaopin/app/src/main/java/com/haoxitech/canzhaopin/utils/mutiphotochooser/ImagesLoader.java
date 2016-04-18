package com.haoxitech.canzhaopin.utils.mutiphotochooser;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.haoxitech.canzhaopin.app.AppContext;
import com.haoxitech.canzhaopin.utils.mutiphotochooser.model.ImageItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wangtao on 16/1/29.
 */
public class ImagesLoader {


    List<HashMap<String, String>> albumList = new ArrayList<HashMap<String, String>>();
//    static HashMap<String, ImageDirectoryItem> directoryItemHashMap = new HashMap<String, ImageDirectoryItem>();

    public static ArrayList<ImageItem> getImages() {
        ContentResolver contentResolver = AppContext.getInstance().getContentResolver();

        ArrayList<ImageItem> allImages = new ArrayList<ImageItem>();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        //获取jpeg和png格式的文件，并且按照时间进行倒序
        Cursor cursor = contentResolver.query(uri, null, MediaStore.Images.Media.MIME_TYPE + "=\"image/jpeg\" or " +
                        MediaStore.Images.Media.MIME_TYPE + "=\"image/png\" or " +
                        MediaStore.Images.Media.MIME_TYPE + "=\"image/jpg\"", null,
                MediaStore.Images.Media.DATE_MODIFIED + " desc");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                ImageItem singleImageModel = new ImageItem();
                String _id = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID));
                int size = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE));
                singleImageModel.imagePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                singleImageModel.imageId = _id;
                if (size > 10240) {
                    allImages.add(singleImageModel);
                }
                if (allImages.size() > 100) {
                    return allImages;
                }
            }
        }
        return allImages;
    }

    public static ArrayList<String> getImagesDirectory() {
        String columns[] = new String[] { MediaStore.Images.Media._ID, MediaStore.Images.Media.BUCKET_ID,
                MediaStore.Images.Media.PICASA_ID, MediaStore.Images.Media.DATA, MediaStore.Images.Media.DISPLAY_NAME, MediaStore.Images.Media.TITLE,
                MediaStore.Images.Media.SIZE, MediaStore.Images.Media.BUCKET_DISPLAY_NAME };

        ContentResolver contentResolver = AppContext.getInstance().getContentResolver();

        Cursor cur = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null,
                MediaStore.Images.Media.DATE_MODIFIED+" desc");
        if (cur.moveToFirst()) {
            int photoIDIndex = cur.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
            int photoPathIndex = cur.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            int photoNameIndex = cur.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME);
            int photoTitleIndex = cur.getColumnIndexOrThrow(MediaStore.Images.Media.TITLE);
            int photoSizeIndex = cur.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE);
            int bucketDisplayNameIndex = cur
                    .getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
            int bucketIdIndex = cur.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_ID);
            int picasaIdIndex = cur.getColumnIndexOrThrow(MediaStore.Images.Media.PICASA_ID);
            int totalNum = cur.getCount();

            do {
                String _id = cur.getString(photoIDIndex);
                String name = cur.getString(photoNameIndex);
                String path = cur.getString(photoPathIndex);
                String title = cur.getString(photoTitleIndex);
                String size = cur.getString(photoSizeIndex);
                String bucketName = cur.getString(bucketDisplayNameIndex);
                String bucketId = cur.getString(bucketIdIndex);
                String picasaId = cur.getString(picasaIdIndex);

//                ImageDirectoryItem directoryItem = directoryItemHashMap.get(bucketId);
//                if (directoryItem == null) {
//                    directoryItem = new ImageDirectoryItem();
//                    directoryItem.imageItemArrayList = new ArrayList<ImageItem>();
//                    directoryItem.directoryName = bucketName;
//                }
//                bucket.count++;
//                ImageItem imageItem = new ImageItem();
//                imageItem.imageId = _id;
//                imageItem.imagePath = path;
//                imageItem.thumbnailPath = thumbnailList.get(_id);
//                bucket.imageList.add(imageItem);

            } while (cur.moveToNext());
        }
        return null;
    }

//    public List<ImageDirectoryItem> getImagesDirectoryList(boolean refresh) {
////		if (refresh || (!refresh && !hasBuildImagesBucketList)) {
//        bucketList.clear();
//        buildImagesBucketList();
////		}
//        List<ImageBucket> tmpList = new ArrayList<ImageBucket>();
//        Iterator<Entry<String, ImageBucket>> itr = bucketList.entrySet()
//                .iterator();
//        while (itr.hasNext()) {
//            Map.Entry<String, ImageBucket> entry = (Map.Entry<String, ImageBucket>) itr
//                    .next();
//            tmpList.add(entry.getValue());
//        }
////		for (int i = 0; i < bList.size(); i++) {
////			ImageBucket bucket = bucketList.get(bList.get(i));
////			tmpList.add(bucket);
////		}
//        return tmpList;
//    }
}
