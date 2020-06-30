package com.example.day629.presenter;

import com.example.day629.base.BasePresenter;
import com.example.day629.bean.ProcBean;
import com.example.day629.model.ProcModel;
import com.example.day629.net.ProcCallBack;
import com.example.day629.view.ProcView;

public class ProcPresenter extends BasePresenter<ProcView> {

    private ProcModel procModel;

    @Override
    protected void initModel() {
        procModel = new ProcModel();
        addModel(procModel);
    }
    public void getData(int cid){
        procModel.getData(cid, new ProcCallBack<ProcBean>() {
            @Override
            public void onSucess(ProcBean procBean) {
                mView.setData(procBean);
            }

            @Override
            public void onFain(String str) {
                mView.showToast(str);
            }
        });
    }
}
