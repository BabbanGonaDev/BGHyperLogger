package com.babbangona.hyperlogger.Network;

import android.content.Context;

import com.babbangona.hyperlogger.Database.AppDatabase;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyncController {


    Context context;
    public SyncController(Context context){
        this.context = context;
    }





    public void uploadLogs() {

        Call<List<UploadLogsResponse>> call = NetworkClient
                .getInstance(context)
                .getApi()
                .uploadGeneralLogs(new Gson().toJson(AppDatabase.getInstance(context).appLogsDao().getUnSyncedLogs()));

        call.enqueue(new Callback<List<UploadLogsResponse>>() {
            @Override
            public void onResponse(Call<List<UploadLogsResponse>> call, Response<List<UploadLogsResponse>> response) {
                List<UploadLogsResponse> result = response.body();
                if(response.isSuccessful()) {
                    if (result != null) {
                        if (!result.isEmpty()) {


                            for (int i = 0; i < result.size(); i++) {
                                String log_id = result.get(i).getLog_id();

                                try {
                                    AppDatabase.getInstance(context).appLogsDao().updateSyncFlag(log_id);

                                } catch (Exception e) {

                                }
                            }

                        }

                    }
                    uploadAuditLogs();
                }else{
                    uploadAuditLogs();
                }
                }

            @Override
            public void onFailure(Call<List<UploadLogsResponse>> call, Throwable t) {
                uploadAuditLogs();
            }
        });

        //return "done";

    }

    public void  uploadAuditLogs() {

        Call<List<UploadLogsResponse>> call = NetworkClient
                .getInstance(context)
                .getApi()
                .uploadAuditLogs(new Gson().toJson(AppDatabase.getInstance(context).hyperLoggerDao().getUnsyncedLogs()));

        call.enqueue(new Callback<List<UploadLogsResponse>>() {
            @Override
            public void onResponse(Call<List<UploadLogsResponse>> call, Response<List<UploadLogsResponse>> response) {
                List<UploadLogsResponse> result = response.body();
                if (response.isSuccessful()) {
                    if (result != null) {
                        if (!result.isEmpty()) {


                            for (int i = 0; i < result.size(); i++) {
                                String log_id = result.get(i).getLog_id();

                                try {
                                    AppDatabase.getInstance(context).hyperLoggerDao().updateSyncFlag(log_id);

                                } catch (Exception e) {

                                }
                            }

                        }

                    }

                } else {

                }
            }

            @Override
            public void onFailure(Call<List<UploadLogsResponse>> call, Throwable t) {

            }
        });

    }





    private String getDate(String module){
        SimpleDateFormat dateFormat1;
        if (module.matches("concat")) {
            dateFormat1 = new SimpleDateFormat("yyMMddHHmmss", Locale.getDefault());
        }else if (module.matches("spread")) {
            dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        }else{
            dateFormat1 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        }
        Date date1 = new Date();
        return dateFormat1.format(date1);
    }





}

