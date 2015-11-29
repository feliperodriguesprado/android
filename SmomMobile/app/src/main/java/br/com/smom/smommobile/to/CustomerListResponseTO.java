package br.com.smom.smommobile.to;

import java.util.ArrayList;
import java.util.List;

import br.com.smom.smommobile.entities.CustomerEntity;

public class CustomerListResponseTO {

    private List<CustomerEntity> customerList = new ArrayList<>();
    private ResponseResourceTO responseResource;

    public List<CustomerEntity> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<CustomerEntity> customerList) {
        this.customerList = customerList;
    }

    public ResponseResourceTO getResponseResource() {
        return responseResource;
    }

    public void setResponseResource(ResponseResourceTO responseResource) {
        this.responseResource = responseResource;
    }
}
