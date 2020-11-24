package com.example.hyperlogger.Network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadLogsResponse {

    @SerializedName("log_id")
    @Expose
    private String log_id;



    public UploadLogsResponse(String log_id) {
        this.log_id = log_id;

    }

    public String getLog_id() {
        return log_id;
    }

    public void setLog_id(String log_id) {
        this.log_id = log_id;
    }
}
