// IBookManager.aidl
package com.hust_twj.zademo.aidl;

//引入Book的包名，需要以aidl结尾
import com.hust_twj.zademo.aidl.Book;

//定义服务接口，在客户端调用
interface IBookManager {

    //自定义的类必须加in、out、inout等修饰符，否则会报错！
    void addBook(in Book book);

    List<Book> getBookList();
}
