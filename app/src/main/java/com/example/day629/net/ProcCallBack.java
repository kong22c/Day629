package com.example.day629.net;

public interface ProcCallBack<T> {
   void onSucess(T t) ;
   void onFain(String str);
}
