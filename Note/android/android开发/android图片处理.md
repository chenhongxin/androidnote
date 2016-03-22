1.画图
画图需要创建一张画布
Canvas mCanvas = new Canvas(bitmap);
// 绘画马赛克
Paint  mosaicPaint = new Paint();// 将底下的第二张图片呈现出来
mosaicPaint.setAntiAlias(true);
mosaicPaint.setAlpha(0); // 设置透明
mosaicPaint.setStrokeCap(Cap.ROUND); 
mosaicPaint.setStrokeJoin(Join.ROUND); // 设置笔触和连接处能更加圆滑
mosaicPaint.setDither(true); // 设置绘画过程流畅
mosaicPaint.setXfermode(new PorterDuffXfermode(Mode.DST_IN)); // 设置底图显示
mosaicPaint.setStyle(Style.STROKE); // 设置空心
mosaicPaint.setStrokeWidth(25.f); // 设置马赛克的笔25
// 绘画颜色
Paint colorPaint = new Paint();
colorPaint.setColor(Color.parseColor("#30ff0010"));
colorPaint.setStrokeCap(Cap.ROUND);
colorPaint.setStrokeJoin(Join.ROUND);
colorPaint.setDither(true);
colorPaint.setStyle(Style.STROKE);
colorPaint.setStrokeWidth(10.f);
Path path = new Path(); // 绘画的路径
2.旋转、翻转、缩放
1.1通过矩阵实现：旋转、翻转、缩放
    (1).定义float[]
        float[] imageMatrix = new float[9];
        {
            scale_x, skew_x, trans_x,
            skew_y, scale_y, trans_y,
            0, 0, 1
        }
        scale_x和scale_y设置x、y轴的缩放大小
        skew_x和skew_y设置x、y轴的错切变换
        trans_x和trans_y设置x、y轴的平移大小
    (2).定义Matrix设置矩阵
        Matrix matrix = new Matrix();
        maxtrix.setValues(imageMatrix);
1.2通过post实现：旋转、翻转、缩放
    postRotate(x, y); // 旋转
    postScale(x, y); // 缩放
    postTransaction(x, y); //平移
    postSkew(x, y); //错切

3.马赛克
(1).马赛克算法
/**
 * 馬賽克
 *
 * @param bitmap
 * @param targetRect
 * @param blockSize
 * @return
 * @throws Exception
 */
public Bitmap makeMosaic(Bitmap bitmap, Rect targetRect, int blockSize)
        throws OutOfMemoryError {
    if (bitmap == null || bitmap.getWidth() == 0 || bitmap.getHeight() == 0
            || bitmap.isRecycled()) {
        throw new RuntimeException("bad bitmap to add mosaic");
    }
    if (blockSize < CUBE) {
        blockSize = CUBE;
    }
    if (targetRect == null) {
        targetRect = new Rect();
    }
    int bw = bitmap.getWidth();
    int bh = bitmap.getHeight();
    if (targetRect.isEmpty()) {
        targetRect.set(0, 0, bw, bh);
    }
    //
    int rectW = targetRect.width();
    int rectH = targetRect.height();
    int[] bitmapPxs = new int[bw * bh];
    // fetch bitmap pxs
    bitmap.getPixels(bitmapPxs, 0, bw, 0, 0, bw, bh);
    //
    int rowCount = (int) Math.ceil((float) rectH / blockSize);
    int columnCount = (int) Math.ceil((float) rectW / blockSize);
    int maxX = bw;
    int maxY = bh;
    for (int r = 0; r < rowCount; r++) { // row loop
        for (int c = 0; c < columnCount; c++) {// column loop
            int startX = targetRect.left + c * blockSize + 1;
            int startY = targetRect.top + r * blockSize + 1;
            dimBlock(bitmapPxs, startX, startY, blockSize, maxX, maxY);
        }
    }
    return Bitmap.createBitmap(bitmapPxs, bw, bh, Bitmap.Config.ARGB_8888);
}

/**
 * 从块内取样，并放大，从而达到马赛克的模糊效果
 *
 * @param pxs
 * @param startX
 * @param startY
 * @param blockSize
 * @param maxX
 * @param maxY
 */
private static void dimBlock(int[] pxs, int startX, int startY,
                             int blockSize, int maxX, int maxY) {
    int stopX = startX + blockSize - 1;
    int stopY = startY + blockSize - 1;
    if (stopX > maxX) {
        stopX = maxX;
    }
    if (stopY > maxY) {
        stopY = maxY;
    }
    //
    int sampleColorX = startX + blockSize / 2;
    int sampleColorY = startY + blockSize / 2;
    //
    if (sampleColorX > maxX) {
        sampleColorX = maxX;
    }
    if (sampleColorY > maxY) {
        sampleColorY = maxY;
    }
    int colorLinePosition = (sampleColorY - 1) * maxX;
    int sampleColor = pxs[colorLinePosition + sampleColorX - 1];// 像素从1开始，但是数组层0开始
    for (int y = startY; y <= stopY; y++) {
        int p = (y - 1) * maxX;
        for (int x = startX; x <= stopX; x++) {
            // 像素从1开始，但是数组层0开始
            pxs[p + x - 1] = sampleColor;
        }
    }
}
4.变颜色

5.剪切
用CropImageView类进行剪切
CropImageView类
CropMode -> 默认比例为CropMode.RATIO_1_1 (比例为：1:1)
    可选项：    CropMode.RATIO_FIT_IMAGE (跟图片同等比例)
                CropMode.RATIO_4_3 (比例为: 4:3)
                CropMode.RATIO_3_4 (比例为: 3:4)
                CropMode.RATIO_16_9 (比例为: 16:9)
                CropMode.RATIO_9_16 (比例为：9:16)
                CropMode.RATIO_FREE (自由比例)
                CropMode.RATIO_CUSTOM (自定义比例)
                CropMode.CIRCLE (圆形)

ShowMode -> 控制边框(HandleShowMode)跟内部横条(GuideShowMode)的属性
    可选项：    ShowMode.SHOW_ALWAYS (一直显示，默认值)
                ShowMode.SHOW_ON_TOUCH (触摸时显示)
                ShowMode.NOT_SHOW (一直不显示)

TouchArea   -> 剪切框触摸的范围
    可选项；    TouchArea.OUT_OF_BOUNDS (默认值: 在剪切框范围外)
                TouchArea.CENTER (剪切框中间，移动剪切框)
                TouchArea.LEFT_TOP (剪切框左上点)
                TouchArea.RIGHT_TOP (剪切框右上点)
                TouchArea.LEFT_BOTTOM (剪切框左下点)
                TouchArea.RIGHT_BOTTOM (剪切框右下点)

RotateDegrees   -> 图片旋转参数
    可选项：    RotateDegrees.ROTATE_90D
                RotateDegrees.ROTATE_180D
                RotateDegrees.ROTATE_270D

变量：
    mBackgroundColor    ->  背景颜色 (图片背景的颜色)
    mOverlayColor       ->  图片剪切时遮盖没有选择区域的颜色 (默认值：0xBB000000)
    mFrameColor         ->  边框颜色 (默认值：0xFFFFFF)
    mHandleColor        ->  剪切框4个点的颜色 (默认值：0xFFFFFF)
    mGuideColo          ->  剪切框内直线颜色 (默认值: 0xBBFFFFFF)
    mHandleSize         ->  剪切框4个点的大小 (默认值: 16dp)
    mTouchPadding       ->  剪切框触摸的宽度 (默认值: 0dp)
    mMinFrameSize       ->  最小剪切值 (默认值:　50dp)
    mFrameStrokeWeight  ->  剪切边框的大小 
    mGuideStrokeWeight  ->  内部直线的大小
    mInitialFrameScale  ->  剪切框与图片的大小比例 (默认值: 0.75)
    mIsCropEnabled      ->  是否开启剪切
    mIsInitialized      ->  默认值为false 在初始化后则为true
    mTouchArea          ->  剪切框触摸的点，默认值：OUT_OF_BOUNDS

画笔：
    mPaintTransparent   ->  剪切框以外背景画笔
    mPaintFrame         ->  剪切框边框和里面直线画笔

函数：
    initCropFrame()             ->  初始化剪切框跟图片大小
    adjustRatio()               ->  重新计算比例设置剪切框的大小位置
    drawEditFrame(Canvas)       ->  画剪切框跟背景
    checkMoveBounds()           ->  当移动剪切框时，不得超出图片
    checkScaleBounds()          ->  设置剪切框的大小不得超出图片
    rotateImage(RotateDegrees)  ->  图片旋转
    setCustomRatio(int, int)    ->  自定义剪切框比例
