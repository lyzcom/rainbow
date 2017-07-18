package com.lyz.rainbow.model;

import com.lyz.rainbow.data.bean.Essay;

import java.util.List;

/**
 * Created by ring on 17/7/17.
 */

public interface EssayModel {

    /**
     * 加载文章数据
     */
    void loadEssay(LoadEssayListener listener);

    interface LoadEssayListener {
        void onSuccess(List<Essay> list);

        void onFailure();

        void onComplete(String data);
    }
}
