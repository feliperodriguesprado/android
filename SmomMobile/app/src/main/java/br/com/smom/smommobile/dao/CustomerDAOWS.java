package br.com.smom.smommobile.dao;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.util.List;

import br.com.smom.smommobile.entities.CustomerEntity;
import br.com.smom.smommobile.to.CustomerListResponseTO;
import br.com.smom.smommobile.utils.RequestWSAsyncTask;

public class CustomerDAOWS {

    private Context context;
    private RequestWSAsyncTask requestWS;

    public CustomerDAOWS (Context context) {
        this.context = context;
        requestWS = new RequestWSAsyncTask(this.context);
    }

    public List<CustomerEntity> getCustomerList() {

        String result;
        Gson gson;
        CustomerListResponseTO customerListResponse;

        requestWS.execute("GET", "http://smom.com.br/modules/customer/register/resources/rest/list/all");

        while (!requestWS.isCancelled()) {
            try {
                Thread.sleep(500);
                System.out.println(requestWS.getStatus().name());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        result = requestWS.getResult();

        if (result != null) {
            gson = new Gson();
            customerListResponse =  gson.fromJson(result, CustomerListResponseTO.class);
            return customerListResponse.getCustomerList();
        } else {
            return null;
        }

    }

}
