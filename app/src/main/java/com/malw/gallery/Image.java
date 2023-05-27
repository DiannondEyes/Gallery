package com.malw.gallery;

import android.os.Parcel;
import android.os.Parcelable;

public class Image implements Parcelable {
    private final String title;
    private final String path;

    public Image(String title, String path) {
        this.title = title;
        this.path = path;
    }

    protected Image(Parcel in) {
        title = in.readString();
        path = in.readString();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getPath() {
        return path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(path);
    }
}
