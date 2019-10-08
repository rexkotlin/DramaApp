package com.example.dramaapp.model;


import com.example.dramaapp.dto.Dto.response.DramaResponse;
import com.example.dramaapp.service.DramaService;
import com.example.dramaapp.utils.RetrofitUtils;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DramaModel {
    public static Single<DramaResponse> getDrama() {
        return RetrofitUtils.getInstance().create(DramaService.class).getDrama()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
