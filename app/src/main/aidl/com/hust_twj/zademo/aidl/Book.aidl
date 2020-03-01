// Book.aidl
package com.hust_twj.zademo.aidl;

//服务端：AIDL文件中声明Book类，为了避免出现类名重复导致无法创建文件的错误，
//这里parcelable是个类型，首字母是小写的，和Parcelable接口不是一个东西
parcelable Book;
