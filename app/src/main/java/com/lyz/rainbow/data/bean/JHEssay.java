package com.lyz.rainbow.data.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ring on 17/7/24.
 */

public class JHEssay {
    @SerializedName("result")
    private List<Essay> essays;
    @SerializedName("reason")
    private String reason;
    @SerializedName("error_code")
    private String errorCode;

    public List<Essay> getEssays() {
        return essays;
    }

    public void setEssays(List<Essay> essays) {
        this.essays = essays;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
