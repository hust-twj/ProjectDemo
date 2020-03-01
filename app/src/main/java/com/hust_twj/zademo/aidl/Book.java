package com.hust_twj.zademo.aidl;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Description ：客户端
 * Created by Wenjing.Tang on 2020/3/1.
 */
public class Book implements Parcelable {

    public int bookID;

    public String bookName;

    public Book(int bookId, String bookName) {
        this.bookID = bookId;
        this.bookName = bookName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.bookID);
        dest.writeString(this.bookName);
    }

    /**
     * readFromParcel和writeToParcel操作数据成员的顺序要一致
     */
    public void readFromParcel(Parcel in) {
        bookID = in.readInt();
        bookName = in.readString();
    }

    public Book() {
    }

    protected Book(Parcel in) {
        this.bookID = in.readInt();
        this.bookName = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}

