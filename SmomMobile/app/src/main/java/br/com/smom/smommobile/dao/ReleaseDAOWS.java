package br.com.smom.smommobile.dao;

import android.content.Context;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import br.com.smom.smommobile.entities.ReleaseEntity;
import br.com.smom.smommobile.to.ReleaseListResponseTO;
import br.com.smom.smommobile.utils.RequestWSAsyncTask;

public class ReleaseDAOWS {

    private Context context;
    private RequestWSAsyncTask requestWS;

    public ReleaseDAOWS(Context context) {
        this.context = context;
        requestWS = new RequestWSAsyncTask(this.context);
    }

    public List<ReleaseEntity> getListAll() {

        String result;
        Gson gson;
        ReleaseListResponseTO releaseListResponse;

        requestWS.execute("GET", "http://smom.com.br/modules/financial/releases/resources/rest/list?createDateEnd=2016-12-31&createDateStart=2015-01-01");

        while (!requestWS.isCancelled()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        result = requestWS.getResult();

        if (result != null) {
            gson = new Gson();
            releaseListResponse = gson.fromJson(result, ReleaseListResponseTO.class);
            return releaseListResponse.getReleaseList();
        } else {
            return null;
        }
    }

    public List<ReleaseEntity> getListByCustomer(int customerId) {

        List<ReleaseEntity> releaseListAll;
        List<ReleaseEntity> releaseList = null;

        releaseListAll = getListAll();

        if (releaseListAll != null) {

            releaseList = new ArrayList<>();

            for (ReleaseEntity release : releaseListAll) {
                if (release.getPeopleId() == customerId) {
                    releaseList.add(release);
                }
            }
        }

        return releaseList;
    }

}
