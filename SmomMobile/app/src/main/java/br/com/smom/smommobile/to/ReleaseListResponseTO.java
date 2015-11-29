package br.com.smom.smommobile.to;

import java.util.ArrayList;
import java.util.List;

import br.com.smom.smommobile.entities.ReleaseEntity;

public class ReleaseListResponseTO {

    private List<ReleaseEntity> releaseList = new ArrayList<>();
    private ResponseResourceTO responseResource;


    public List<ReleaseEntity> getReleaseList() {
        return releaseList;
    }

    public void setReleaseList(List<ReleaseEntity> releaseList) {
        this.releaseList = releaseList;
    }

    public ResponseResourceTO getResponseResource() {
        return responseResource;
    }

    public void setResponseResource(ResponseResourceTO responseResource) {
        this.responseResource = responseResource;
    }
}
