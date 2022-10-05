package com.yunusakin.saff.controller.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yunusakin.saff.model.SaffFile;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileResponse {

    @JsonProperty("info")
    private SaffFile saffFile;

    public FileResponse() {
    }

    public FileResponse(SaffFile saffFile) {
        this.saffFile = saffFile;
    }

    public SaffFile getFile() {
        return saffFile;
    }

    public void setFile(SaffFile saffFile) {
        this.saffFile = saffFile;
    }
}
