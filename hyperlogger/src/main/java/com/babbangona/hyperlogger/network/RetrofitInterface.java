 package com.babbangona.hyperlogger.network;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitInterface {

    /**
     * This is the interface class for sync operations
     */

    @FormUrlEncoded
    @POST("uploadGeneralLogs")
    Call<List<UploadResponse>> uploadGeneralLogs(@Field("upload_list") String upload_list);

    @FormUrlEncoded
    @POST("uploadAuditLogs")
    Call<List<UploadResponse>> uploadAuditLogs(@Field("upload_list") String upload_list);

}
