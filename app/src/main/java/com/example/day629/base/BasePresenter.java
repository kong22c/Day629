package com.example.day629.base;

import java.util.ArrayList;

public abstract class BasePresenter <V extends BaseView> {
    public V mView;
    private ArrayList<BaseModel>baseModels;
    public BasePresenter(){
        initModel();
    }

    protected abstract void initModel();

    public void addModel(BaseModel baseModel){
        if (baseModels==null)
            baseModels=new ArrayList<>();
        baseModels.add(baseModel);
    }
    public void bindView(V view){
        mView=view;
    }
}
