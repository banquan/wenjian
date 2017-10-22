package cn.tablayouttest.model;

import android.os.Parcel;
import android.os.Parcelable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by yu on 2017/10/17.
 */

public class FileAnd extends BmobObject implements Parcelable{

    private BmobFile mBmobFile;
    private String mFileType;//通过这个标识可以通过复合查询查询到需要的数据封装到list中适配显示。
    private String mFileName;
    private String mFileSize;

    public FileAnd(){

    }


    public FileAnd(BmobFile bmobFile,String fileName, String fileType, String fileSize){
        this.mBmobFile = bmobFile;
        this.mFileName = fileName;
        this.mFileType = fileType;
        this.mFileSize = fileSize;
    }

    public static final Creator<FileAnd> CREATOR = new Creator<FileAnd>() {
        @Override
        public FileAnd createFromParcel(Parcel in) {
            FileAnd fileAnd = new FileAnd();
            fileAnd.mFileName = in.readString();
            fileAnd.mFileSize = in.readString();
            fileAnd.mFileType = in.readString();
            return fileAnd;
        }

        @Override
        public FileAnd[] newArray(int size) {
            return new FileAnd[size];
        }
    };

    public String getFileSize() {
        return mFileSize;
    }

    public void setFileSize(String fileSize) {
        mFileSize = fileSize;
    }

    public String getFileName() {
        return mFileName;
    }

    public void setFileName(String fileName) {
        mFileName = fileName;
    }

    public String getFileType() {
        return mFileType;
    }

    public void setFileType(String fileType) {
        mFileType = fileType;
    }

    public BmobFile getBmobFile() {
        return mBmobFile;
    }

    public void setBmobFile(BmobFile bmobFile) {
        mBmobFile = bmobFile;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mFileType);
        dest.writeString(mFileName);
        dest.writeString(mFileSize);
    }
}
