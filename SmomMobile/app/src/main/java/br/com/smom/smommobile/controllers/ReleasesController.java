package br.com.smom.smommobile.controllers;

import android.content.Context;

import java.util.List;

import br.com.smom.smommobile.dao.ReleaseDAOLocal;
import br.com.smom.smommobile.dao.ReleaseDAOWS;
import br.com.smom.smommobile.entities.ReleaseEntity;

public class ReleasesController {

    private Context context;
    private ReleaseDAOWS releaseDAOWS;
    private ReleaseDAOLocal releaseDAOLocal;

    public ReleasesController(Context context) {
        this.context = context;
    }

    public List<ReleaseEntity> getListByCustomer(int customerId) {

        List<ReleaseEntity> releaseList = null;
        boolean getLocal = false;

        releaseDAOLocal = new ReleaseDAOLocal(context);
        releaseDAOWS = new ReleaseDAOWS(context);

        if (ConnectionController.checkConnection(context)) {
            releaseList = releaseDAOWS.getListByCustomer(customerId);

            if (releaseList != null) {
                for (ReleaseEntity release : releaseList) {
                    releaseDAOLocal.save(release);
                }
            } else {
                getLocal = true;
            }
        } else {
            getLocal = true;
        }

        if (getLocal) {
            releaseList = releaseDAOLocal.getListByCustomer(customerId);
        }

        return releaseList;
    }

    public List<ReleaseEntity> getListAll() {

        List<ReleaseEntity> releaseList = null;
        boolean getLocal = false;

        releaseDAOLocal = new ReleaseDAOLocal(context);
        releaseDAOWS = new ReleaseDAOWS(context);

        if (ConnectionController.checkConnection(context)) {
            releaseList = releaseDAOWS.getListAll();

            if (releaseList != null) {

                for (ReleaseEntity release : releaseList) {
                    releaseDAOLocal.save(release);
                }

            } else {
                getLocal = true;
            }
        } else {
            getLocal = true;
        }

        if (getLocal) {
            releaseList = releaseDAOLocal.getListAll();
        }

        return releaseList;
    }

}
