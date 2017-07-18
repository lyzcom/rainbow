package com.lyz.rainbow.view;

import com.lyz.rainbow.data.bean.Essay;

import java.util.List;

/**
 * 历史上的今天 相关view操作接口
 * Created by ring on 17/7/17.
 */

public interface EssayView {

    void showEssay(List<Essay> list);

    void showLoading();

    void hideLoading();

    void showError();
}
