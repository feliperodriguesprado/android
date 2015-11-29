package br.com.smom.smommobile.controllers;

import android.content.Context;

import java.util.List;

import br.com.smom.smommobile.dao.CustomerDAOWS;
import br.com.smom.smommobile.entities.CustomerEntity;

public class CustomerController {

    private Context context;
    private CustomerDAOWS customerDAOWS;

    public CustomerController(Context context) {
        this.context = context;
        customerDAOWS = new CustomerDAOWS(context);
    }

    public List<CustomerEntity> getCustomerList() {
        return customerDAOWS.getCustomerList();
    }

}
