package com.example.day629.net;

import com.example.day629.bean.ProcBean;
import com.example.day629.bean.TreeBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    String BASS="https://www.wanandroid.com/";
    @GET("project/tree/json")
    Flowable<TreeBean>getTree();
    @GET("project/list/1/json")
    Flowable<ProcBean>getProc(@Query("cid")int id);
}
