package com.example.dramaapp.main.drama.presenter;


import com.example.dramaapp.base.BasePresenter;
import com.example.dramaapp.dto.Dto.response.DramaResponse;
import com.example.dramaapp.model.DramaModel;

public class DramaPresenter extends BasePresenter {
    private DramaModel dramaModel;
    private DramaCallback dramaCallback;

    public DramaPresenter(DramaCallback callback) {
        dramaModel = new DramaModel();
        this.dramaCallback = callback;
    }

    public interface DramaCallback {
        void onDramaSuccess(DramaResponse dto);

        void onDramaFail(String errorMsg);
    }

    public void getDrama() {
        disposable.add(DramaModel.getDrama().subscribe(
				dramaCallback::onDramaSuccess
                , throwable -> dramaCallback.onDramaFail(throwable.getMessage())));
    }
}
