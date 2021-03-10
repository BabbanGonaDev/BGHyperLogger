package com.babbangona.hyperlogger.network;

import android.content.Context;

import com.babbangona.hyperlogger.data.AppDatabase;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyncController {
    Context context;
    AppDatabase db;

    public SyncController(Context context) {
        this.context = context;
        db = AppDatabase.getInstance(context);
    }

    public void uploadGeneralLogs() {

        Call<List<UploadResponse>> call = RetrofitClient
                .getInstance(context)
                .getApi()
                .uploadGeneralLogs(new Gson().toJson(db.getGeneralLogsDao().getUnSyncedLogs()));

        call.enqueue(new Callback<List<UploadResponse>>() {
            @Override
            public void onResponse(Call<List<UploadResponse>> call, Response<List<UploadResponse>> response) {
                List<UploadResponse> result = response.body();
                if (response.isSuccessful()) {
                    if (result != null) {
                        if (!result.isEmpty()) {


                            for (int i = 0; i < result.size(); i++) {
                                String log_id = result.get(i).getLog_id();

                                try {
                                    AppDatabase.getInstance(context).getGeneralLogsDao().updateSyncFlag(log_id);

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
            public void onFailure(Call<List<UploadResponse>> call, Throwable t) {
                uploadAuditLogs();
            }
        });

        //return "done";

    }

    public void  uploadAuditLogs() {

        Call<List<UploadResponse>> call = RetrofitClient
                .getInstance(context)
                .getApi()
                .uploadAuditLogs(new Gson().toJson(db.getLogsDao().getUnsyncedLogs()));

        call.enqueue(new Callback<List<UploadResponse>>() {
            @Override
            public void onResponse(Call<List<UploadResponse>> call, Response<List<UploadResponse>> response) {
                List<UploadResponse> result = response.body();
                if (response.isSuccessful()) {
                    if (result != null) {
                        if (!result.isEmpty()) {

                            for (int i = 0; i < result.size(); i++) {
                                String log_id = result.get(i).getLog_id();

                                try {
                                    AppDatabase.getInstance(context).getLogsDao().updateSyncFlag(log_id);

                                } catch (Exception e) {

                                }
                            }

                        }

                    }

                } else {

                }
            }

            @Override
            public void onFailure(Call<List<UploadResponse>> call, Throwable t) {

            }
        });

    }

}

