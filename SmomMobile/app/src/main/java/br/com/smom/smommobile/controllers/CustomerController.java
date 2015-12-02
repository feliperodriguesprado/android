package br.com.smom.smommobile.controllers;

import android.content.Context;

import java.util.List;

import br.com.smom.smommobile.dao.CustomerDAOLocal;
import br.com.smom.smommobile.dao.CustomerDAOWS;
import br.com.smom.smommobile.entities.CustomerEntity;

public class CustomerController {

    private Context context;
    private CustomerDAOWS customerDAOWS;
    private CustomerDAOLocal customerDAOLocal;

    public CustomerController(Context context) {
        this.context = context;
    }

    public CustomerEntity getById(int id) {

        CustomerEntity customer;

        customerDAOLocal = new CustomerDAOLocal(context);
        customerDAOWS = new CustomerDAOWS(context);
        customer = customerDAOLocal.get(id);
        return customer;
    }

    public List<CustomerEntity> getListAll() {

        List<CustomerEntity> customerList = null;
        boolean getLocal = false;

        customerDAOLocal = new CustomerDAOLocal(context);
        customerDAOWS = new CustomerDAOWS(context);

        if (ConnectionController.checkConnection(context)) {
            customerList = customerDAOWS.getList();

            if (customerList != null) {

                for (CustomerEntity customer : customerList) {
                    customerDAOLocal.save(customer);
                }

            } else {
                getLocal = true;
            }
        } else {
            getLocal = true;
        }

        if (getLocal) {
            customerList = customerDAOLocal.getListAll();
        }

        return customerList;
    }

}
