package com.szm.administrator.loaderlibs.cache;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.szm.administrator.loaderlibs.cache.Utils.DiskLruCache;
import com.szm.administrator.loaderlibs.cache.Utils.IOUtil;
import com.szm.administrator.loaderlibs.cache.api.BitmapCacheAPI;
import com.szm.administrator.loaderlibs.request.BitmapRequest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 硬盘缓存采用LRU算法
 * Created by szm on 2016/3/19.
 */
public class DiskCache implements BitmapCacheAPI{

    private static DiskCache diskCache;

    private DiskLruCache mDiskLruCache;

    //缓存路径
    private String mCacheDir="img_cache";
    private static final int MB=1024*1024;

    private DiskCache(Context context){
        initDiskCache(context);
    }

    /**
     * 初始化
     * @param context
     */
    private void initDiskCache(Context context) {
        File directory=getDiskCacheDir(mCacheDir,context);
        if(!directory.exists()){
            directory.mkdirs();
        }
        try {
            mDiskLruCache= DiskLruCache.open(directory,getVersion(context),1,50*MB);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取应用版本号
     * @param context
     * @return
     */
    private int getVersion(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getPackageInfo(context.getPackageName(),PackageManager.GET_ACTIVITIES).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * 获取缓存路径
     * @param mCacheDir
     * @param context
     * @return
     */
    private File getDiskCacheDir(String mCacheDir, Context context) {
        String cachePath;
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            //外部存储
            cachePath=context.getExternalCacheDir().getPath();
        }else{
            //内部存储
            cachePath=context.getCacheDir().getPath();
        }
        return new File(cachePath+File.separatorChar+mCacheDir);
    }

    public static DiskCache getInStance(Context context){
        if(diskCache==null){
            synchronized (DiskCache.class){
                if(diskCache==null){
                    diskCache=new DiskCache(context);
                }
            }
        }
        return diskCache;
    }

    /**
     * 添加到缓存
     *
     * @param request
     * @param bitmap
     */
    @Override
    public void put(BitmapRequest request, Bitmap bitmap) {
        DiskLruCache.Editor editor;
        OutputStream os=null;
        try {
            editor=mDiskLruCache.edit(request.getImageUriMD5());
            os=editor.newOutputStream(0);
            if(persistBitmap2Disk(bitmap,os)){
                editor.commit();
            }else{
                editor.abort();
            }
            mDiskLruCache.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtil.closeQuietly(os);
        }
    }

    /**
     * 将图片持久化到硬盘
     * @param bitmap
     * @param os
     * @return
     */
    private boolean persistBitmap2Disk(Bitmap bitmap, OutputStream os) {
        BufferedOutputStream baos=null;
        try {
            baos=new BufferedOutputStream(os);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
            baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }finally{
            IOUtil.closeQuietly(baos);
        }
        return true;
    }

    /**
     * 从缓存中获取一个bitmap
     *
     * @param request
     * @return
     */
    @Override
    public Bitmap get(BitmapRequest request) {
        InputStream in=null;
        try {
            DiskLruCache.Snapshot snapShort= mDiskLruCache.get(request.getImageUriMD5());
            in=snapShort.getInputStream(0);
            return BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtil.closeQuietly(in);
        }
        return null;
    }

    /**
     * 从缓存中移除bitmap
     *
     * @param request
     */
    @Override
    public void remove(BitmapRequest request) {
        try {
            mDiskLruCache.remove(request.getImageUriMD5());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置缓存路径
     * @param mCacheDir
     */
    public void setmCacheDir(String mCacheDir) {
        this.mCacheDir = mCacheDir;
    }
}
