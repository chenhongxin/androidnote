package com.haoxitech.canzhaopin.utils.mutiphotochooser;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;

import android.provider.MediaStore.Audio.Albums;
import android.provider.MediaStore.Images.Media;

import com.haoxitech.canzhaopin.app.AppContext;
import com.haoxitech.canzhaopin.utils.mutiphotochooser.model.ImageBucketItem;
import com.haoxitech.canzhaopin.utils.mutiphotochooser.model.ImageItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by wangtao on 16/1/29.
 */
public class ImageMediaLoader extends AsyncTask<Object, Object, Object> {

    public static ImageMediaLoader instance = null;

    public static ImageMediaLoader getInstance() {
        if (instance == null) {
            instance = new ImageMediaLoader();
        }
        return instance;
    }

    ContentResolver contentResolver;

    public ImageMediaLoader() {
        contentResolver = AppContext.getInstance().getContentResolver();
    }

    @Override
    protected Object doInBackground(Object... objects) {
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

    }

    private void getThumbnail() {
        String[] projection = { MediaStore.Images.Thumbnails._ID, MediaStore.Images.Thumbnails.IMAGE_ID,
                MediaStore.Images.Thumbnails.DATA };
        Cursor cursor = contentResolver.query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, projection,
                null, null, null);
        getThumbnailColumnData(cursor);
    }

    HashMap<String, String> thumbnailList = new HashMap<>();
    // 专辑列表
    List<HashMap<String, String>> albumList = new ArrayList<>();
    HashMap<String, ImageBucketItem> bucketList = new HashMap<>();


    private void getThumbnailColumnData(Cursor cur) {
        thumbnailList.clear();
        if (cur.moveToFirst()) {
            int _id;
            int image_id;
            String image_path;
            int _idColumn = cur.getColumnIndex(MediaStore.Images.Thumbnails._ID);
            int image_idColumn = cur.getColumnIndex(MediaStore.Images.Thumbnails.IMAGE_ID);
            int dataColumn = cur.getColumnIndex(MediaStore.Images.Thumbnails.DATA);

            do {
                _id = cur.getInt(_idColumn);
                image_id = cur.getInt(image_idColumn);
                image_path = cur.getString(dataColumn);

                thumbnailList.put("" + image_id, image_path);
            } while (cur.moveToNext());
        }
    }

    private void getAlbum() {
        String[] projection = { Albums._ID, Albums.ALBUM, Albums.ALBUM_ART,
                Albums.ALBUM_KEY, Albums.ARTIST, Albums.NUMBER_OF_SONGS };
        Cursor cursor = contentResolver.query(Albums.EXTERNAL_CONTENT_URI, projection, null,
                null, null);
        getAlbumColumnData(cursor);
    }

    private void getAlbumColumnData(Cursor cur) {
        if (cur.moveToFirst()) {
            int _id;
            String album;
            String albumArt;
            String albumKey;
            String artist;
            int numOfSongs;

            int _idColumn = cur.getColumnIndex(Albums._ID);
            int albumColumn = cur.getColumnIndex(Albums.ALBUM);
            int albumArtColumn = cur.getColumnIndex(Albums.ALBUM_ART);
            int albumKeyColumn = cur.getColumnIndex(Albums.ALBUM_KEY);
            int artistColumn = cur.getColumnIndex(Albums.ARTIST);
            int numOfSongsColumn = cur.getColumnIndex(Albums.NUMBER_OF_SONGS);

            do {
                // Get the field values
                _id = cur.getInt(_idColumn);
                album = cur.getString(albumColumn);
                albumArt = cur.getString(albumArtColumn);
                albumKey = cur.getString(albumKeyColumn);
                artist = cur.getString(artistColumn);
                numOfSongs = cur.getInt(numOfSongsColumn);

                HashMap<String, String> hash = new HashMap<String, String>();
                hash.put("_id", _id + "");
                hash.put("album", album);
                hash.put("albumArt", albumArt);
                hash.put("albumKey", albumKey);
                hash.put("artist", artist);
                hash.put("numOfSongs", numOfSongs + "");
                albumList.add(hash);

            } while (cur.moveToNext());
        }
    }

    private boolean hasBuildImagesBucketList = false;

    void buildImagesBucketList() {
        getThumbnail();
        String columns[] = new String[] { Media._ID, Media.BUCKET_ID,
                Media.PICASA_ID, Media.DATA, Media.DISPLAY_NAME, Media.TITLE,
                Media.SIZE, Media.BUCKET_DISPLAY_NAME };
        Cursor cur = contentResolver.query(Media.EXTERNAL_CONTENT_URI, columns, null, null,
                Media.DATE_MODIFIED + " desc");
        if (cur.moveToFirst()) {
            int photoIDIndex = cur.getColumnIndexOrThrow(Media._ID);
            int photoPathIndex = cur.getColumnIndexOrThrow(Media.DATA);
            int photoNameIndex = cur.getColumnIndexOrThrow(Media.DISPLAY_NAME);
            int photoTitleIndex = cur.getColumnIndexOrThrow(Media.TITLE);
            int photoSizeIndex = cur.getColumnIndexOrThrow(Media.SIZE);
            int bucketDisplayNameIndex = cur
                    .getColumnIndexOrThrow(Media.BUCKET_DISPLAY_NAME);
            int bucketIdIndex = cur.getColumnIndexOrThrow(Media.BUCKET_ID);
            int picasaIdIndex = cur.getColumnIndexOrThrow(Media.PICASA_ID);
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


                ImageBucketItem bucket = bucketList.get(bucketId);
                if (bucket == null) {
                    bucket = new ImageBucketItem();
                    bucketList.put(bucketId, bucket);
                    bucket.imageItemArrayList = new ArrayList<ImageItem>();
                    bucket.directoryName = bucketName;
                }
                bucket.imageCount++;
                ImageItem imageItem = new ImageItem();
                imageItem.imageId = _id;
                imageItem.imagePath = path;
                imageItem.thumbnailPath = thumbnailList.get(_id);
                bucket.imageItemArrayList.add(imageItem);

            } while (cur.moveToNext());
        }
        hasBuildImagesBucketList = true;
    }


    public List<ImageBucketItem> getImagesBucketList(boolean refresh) {
        if (refresh || (!refresh && !hasBuildImagesBucketList)) {
            buildImagesBucketList();
        }
        List<ImageBucketItem> tmpList = new ArrayList<>();
        Iterator<Map.Entry<String, ImageBucketItem>> itr = bucketList.entrySet().iterator();
        //将Hash转化为List
        while (itr.hasNext()) {
            Map.Entry<String, ImageBucketItem> entry =  itr
                    .next();
            tmpList.add(entry.getValue());
        }
        return tmpList;
    }

    /** * 得到原始图像路径 * @param image_id * @return */
    private String getOriginalImagePath(String image_id) {
        String path = null;
        String[] projection = { Media._ID, Media.DATA };
        Cursor cursor = contentResolver.query(Media.EXTERNAL_CONTENT_URI, projection,
                Media._ID + "=" + image_id, null, Media.DATE_MODIFIED+" desc");
        if (cursor != null) {
            cursor.moveToFirst();
            path = cursor.getString(cursor.getColumnIndex(Media.DATA));
        }
        return path;
    }


    public ArrayList<ImageItem> getImages() {
        getThumbnail();
        ArrayList<ImageItem> allImages = new ArrayList<ImageItem>();
        Uri uri = Media.EXTERNAL_CONTENT_URI;
        //获取jpeg和png格式的文件，并且按照时间进行倒序
        Cursor cursor = contentResolver.query(uri, null, Media.MIME_TYPE + "=\"image/jpeg\" or " +
                        Media.MIME_TYPE + "=\"image/png\" or " +
                        Media.MIME_TYPE + "=\"image/jpg\"", null,
                Media.DATE_MODIFIED + " desc");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                ImageItem singleImageModel = new ImageItem();
                String _id = cursor.getString(cursor.getColumnIndexOrThrow(Media._ID));
                int size = cursor.getInt(cursor.getColumnIndexOrThrow(Media.SIZE));
                singleImageModel.imagePath = cursor.getString(cursor.getColumnIndex(Media.DATA));
                singleImageModel.imageId = _id;
                singleImageModel.thumbnailPath = thumbnailList.get(_id);
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

    public ArrayList<String> getImagesDirectory() {
        String columns[] = new String[] { Media._ID, Media.BUCKET_ID,
                Media.PICASA_ID, Media.DATA, Media.DISPLAY_NAME, Media.TITLE,
                Media.SIZE, Media.BUCKET_DISPLAY_NAME };

        ContentResolver contentResolver = AppContext.getInstance().getContentResolver();

        Cursor cur = contentResolver.query(Media.EXTERNAL_CONTENT_URI, columns, null, null,
                Media.DATE_MODIFIED+" desc");
        if (cur.moveToFirst()) {
            int photoIDIndex = cur.getColumnIndexOrThrow(Media._ID);
            int photoPathIndex = cur.getColumnIndexOrThrow(Media.DATA);
            int photoNameIndex = cur.getColumnIndexOrThrow(Media.DISPLAY_NAME);
            int photoTitleIndex = cur.getColumnIndexOrThrow(Media.TITLE);
            int photoSizeIndex = cur.getColumnIndexOrThrow(Media.SIZE);
            int bucketDisplayNameIndex = cur
                    .getColumnIndexOrThrow(Media.BUCKET_DISPLAY_NAME);
            int bucketIdIndex = cur.getColumnIndexOrThrow(Media.BUCKET_ID);
            int picasaIdIndex = cur.getColumnIndexOrThrow(Media.PICASA_ID);
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

            } while (cur.moveToNext());
        }
        return null;
    }

    interface ImageMediaListHandler {
        void getImageList(ArrayList<ImageBucketItem> list);
    }

    private ImageMediaListHandler imageMediaListHandler;

    public void setImageMediaListHandler(ImageMediaListHandler imageMediaListHandler) {
        this.imageMediaListHandler = imageMediaListHandler;
    }

    public void clearData() {
        thumbnailList.clear();
        albumList.clear();
        bucketList.clear();
    }
}
