package com.example.admin.datecard.http;



import java.util.List;

import retrofit2.http.GET;
import rx.Observable;
import rx.subjects.Subject;

/**
 * Created by admin on 2017/2/8.
 */
public interface ApiService {
    @GET("/list")
    Observable<HttpResult<List<Subject>>> getImgList();
}
