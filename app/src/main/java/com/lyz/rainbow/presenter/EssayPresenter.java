package com.lyz.rainbow.presenter;

import com.lyz.rainbow.data.bean.Essay;
import com.lyz.rainbow.model.EssayModel;
import com.lyz.rainbow.model.impl.EssayModelImpl;
import com.lyz.rainbow.view.EssayView;

import java.util.List;

/**
 * Created by ring on 17/7/17.
 */

public class EssayPresenter implements EssayModel.LoadEssayListener {

    private EssayModel essayModel;
    private EssayView essayView; //在这里调用 视图操作

    public EssayPresenter(EssayView weatherView) {
        this.essayView = weatherView;
        essayModel = new EssayModelImpl();
    }

    /**
     * 加载文章数据
     */
    public void LoadEssays() {
        essayView.showLoading();
        essayModel.loadEssay(this);
    }

    @Override
    public void onSuccess(List<Essay> list) {
        //请求成功
        essayView.showEssay(list);
        essayView.hideLoading();
    }

    @Override
    public void onFailure() {
        //请求失败
        essayView.showError();
        essayView.hideLoading();
    }

    @Override
    public void onComplete(String data) {
        //请求完成
    }
}
