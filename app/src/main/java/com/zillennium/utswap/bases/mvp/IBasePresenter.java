package com.zillennium.utswap.bases.mvp;

public interface IBasePresenter<ViewT> {

    void onViewActive(ViewT view);

    void onViewInactive();

    void onInitView();

}
