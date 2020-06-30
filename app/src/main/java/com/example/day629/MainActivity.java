package com.example.day629;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.day629.adapter.VpAdapter;
import com.example.day629.bean.TreeBean;
import com.example.day629.net.ApiService;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tl)
    TabLayout tl;
    @BindView(R.id.vp)
    ViewPager vp;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> list;
    private VpAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @SuppressLint("CheckResult")
    private void initData() {
        new Retrofit.Builder()
                .baseUrl(ApiService.BASS)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class)
                .getTree()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<TreeBean>() {
                    @Override
                    public void onNext(TreeBean treeBean) {
                        Log.i("3333", "onNext: "+treeBean.toString());
                        List<TreeBean.DataBean> data = treeBean.getData();
                        for (int i = 0; i <data.size() ; i++) {
                           list.add(data.get(i).getName());
                           fragments.add(new PublicFragment(data.get(i).getId()));
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView() {
        fragments = new ArrayList<>();
        list = new ArrayList<>();
        adapter = new VpAdapter(getSupportFragmentManager(), fragments, list);
        tl.setupWithViewPager(vp);
        vp.setAdapter(adapter);

    }
}
