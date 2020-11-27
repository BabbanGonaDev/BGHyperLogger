package com.babbangona.hyperlogger.Network;


import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitInterface {

    /**
     * This is the interface class for sync operations
     * */

    @FormUrlEncoded
    @POST("uploadLogs")
    Call<List<UploadLogsResponse>> uploadLogs(@Field("upload_list") String upload_list);

    @FormUrlEncoded
    @POST("uploadAuditLogs")
    Call<List<UploadLogsResponse>> uploadAuditLogs(@Field("upload_list") String upload_list);

}
