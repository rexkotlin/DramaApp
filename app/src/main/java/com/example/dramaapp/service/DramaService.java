package com.example.dramaapp.service;

import com.example.dramaapp.dto.Dto.response.DramaResponse;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface DramaService {

    @GET("v2/5a97c59c30000047005c1ed2")
    Single<DramaResponse> getDrama();
}
