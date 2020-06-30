package com.example.day629;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.day629.adapter.ProcAdapter;
import com.example.day629.base.BaseFragment;
import com.example.day629.bean.ProcBean;
import com.example.day629.presenter.ProcPresenter;
import com.example.day629.view.ProcView;

import java.util.ArrayList;

import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PublicFragment extends BaseFragment<ProcPresenter> implements ProcView {
    @BindView(R.id.rv)
    RecyclerView rv;
    private int cid;
    private boolean iscreate;
    private boolean isDataLoad;
    private ArrayList<ProcBean.DataBean.DatasBean> list;
    private ProcAdapter adapter;

    //@Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser&&isVisibleToUser&&!isDataLoad){
//            initData();
//            isDataLoad=true;
//        }
//    }

    public PublicFragment(int id) {
        // Required empty public constructor
        cid = id;
    }


    @Override
    protected void initLister() {
        adapter.setOnItemClickLister(new ProcAdapter.OnItemClickLister() {
            @Override
            public void onItemClick(int position) {
                boolean isck = list.get(position).isIsck();
                if (isck==false){
                   list.get(position).setIsck(true);
                 adapter.notifyDataSetChanged();
              }else {
                    list.get(position).setIsck(false);
                  adapter.notifyDataSetChanged();
              }
            }
        });
    }

    @Override
    protected void initData() {
            mPresenter.getData(cid);
    }

    @Override
    protected void initView() {
    iscreate=true;
    rv.setLayoutManager(new LinearLayoutManager(getActivity()));
    rv.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        list = new ArrayList<>();
        adapter = new ProcAdapter(getActivity(), list);
        rv.setAdapter(adapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_public;
    }

    @Override
    protected void initPresnter() {
        mPresenter=new ProcPresenter();
    }

    @Override
    public void setData(ProcBean procBean) {
            list.addAll(procBean.getData().getDatas());
            adapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String str) {

    }
}
