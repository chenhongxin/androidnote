package com.haoxitech.canzhaopin.utils.mutiphotochooser.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

import com.haoxitech.HaoConnect.HaoUtility;
import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.base.BaseItemAdapter;
import com.haoxitech.canzhaopin.utils.ReleaseImp;
import com.haoxitech.canzhaopin.utils.mutiphotochooser.ImageUtil;
import com.haoxitech.canzhaopin.utils.mutiphotochooser.PhotoPreviewActivity;
import com.haoxitech.canzhaopin.utils.mutiphotochooser.model.ImageItem;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.File;


/**
 * Created by wangtao on 16/1/29.
 */
public class PhotoChooseGridAdapter extends BaseItemAdapter {

    private int type = 0;       // 0 单选 1 多选
    private boolean showCamera = true;
    private ReleaseImp releaseImp;

    public PhotoChooseGridAdapter(Context context) {
        super(context);
    }

    public PhotoChooseGridAdapter(Context context, int type) {
        super(context);
        this.type = type;
    }

    public PhotoChooseGridAdapter(Context context, int type, ReleaseImp releaseImp) {
        super(context);
        this.type = type;
        this.releaseImp = releaseImp;
    }

    public void setShowCamera(boolean showCamera) {
        this.showCamera = showCamera;
    }

    @Override
    public int getCount() {
        if (showCamera) {
            return super.getCount() + 1;
        } else {
            return super.getCount();
        }
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.adapter_photo_choose_grid, null);
            holder.imageView = (ImageView) view.findViewById(R.id.content_image);
            holder.chooseBtn = (ToggleButton) view.findViewById(R.id.choose_btn);
            holder.photoLayout = (RelativeLayout) view.findViewById(R.id.photo_layout);
            holder.cameraLayout = (RelativeLayout) view.findViewById(R.id.camera_btn_layout);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if (showCamera) {
            if (i == 0) {
                holder.cameraLayout.setVisibility(View.VISIBLE);
                holder.photoLayout.setVisibility(View.GONE);
                holder.cameraLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);

                        String path = ImageUtil.getOutFile().getPath();
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                                .fromFile(new File(path)));
                        ((PhotoPreviewActivity) context).setPhotoTempFile(path);
                        ((PhotoPreviewActivity) context).startActivityForResult(intent, 1);
                    }
                });
            } else {
                final ImageItem item = (ImageItem) dataList.get(i - 1);
                holder.cameraLayout.setVisibility(View.GONE);
                holder.photoLayout.setVisibility(View.VISIBLE);
                ImageLoader.getInstance().displayImage("file://" + item.getImagePath(), holder.imageView, releaseImp);

                if (type == 1) {
                    holder.chooseBtn.setVisibility(View.VISIBLE);
                } else {
                    holder.chooseBtn.setVisibility(View.GONE);
                }

                holder.photoLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            if (type == 1) {

                            } else {
                                String path = ImageUtil.getOutFile().getPath();
                                ((PhotoPreviewActivity) context).setPhotoTempFile(path);
                                ((PhotoPreviewActivity) context).startPhotoZoom(Uri.fromFile(new File(item.getImagePath())));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } else {
            final ImageItem item = (ImageItem) dataList.get(i);
            holder.cameraLayout.setVisibility(View.GONE);
            holder.photoLayout.setVisibility(View.VISIBLE);
            ImageLoader.getInstance().displayImage("file://" + item.getImagePath(), holder.imageView);

            if (type == 1) {
                holder.chooseBtn.setVisibility(View.VISIBLE);
            } else {
                holder.chooseBtn.setVisibility(View.GONE);
            }

            holder.photoLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        if (type == 1) {

                        } else {

                            String path = ImageUtil.getOutFile().getPath();
                            ((PhotoPreviewActivity) context).setPhotoTempFile(path);

//                            ((PhotoPreviewActivity) context).setPhotoTempFile(item.getImagePath());
                            ((PhotoPreviewActivity) context).startPhotoZoom(Uri.fromFile(new File(item.getImagePath())));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        return view;
    }

    static class ViewHolder {
        ImageView imageView;
        ToggleButton chooseBtn;
        RelativeLayout photoLayout;
        RelativeLayout cameraLayout;
    }
}
