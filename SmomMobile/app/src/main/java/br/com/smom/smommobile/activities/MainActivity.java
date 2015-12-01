package br.com.smom.smommobile.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.smom.smommobile.R;
import br.com.smom.smommobile.controllers.CustomerController;
import br.com.smom.smommobile.dao.CustomerDAOWS;
import br.com.smom.smommobile.entities.CustomerEntity;

public class MainActivity extends Activity {

    private CustomerController customerController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customerController = new CustomerController(getApplicationContext());
        List<CustomerEntity> customerList = customerController.getCustomerList();

        for (CustomerEntity customer : customerList) {
            System.out.println(customer.getId());
            System.out.println(customer.getName());
            System.out.println(customer.getCpfCnpj());
        }

        /*ListAdapterItem listaAdapterItem = new ListAdapterItem(this, customerList);

        ListView listView = (ListView) findViewById(R.id.listViewCustumers);

        listView.setAdapter(listaAdapterItem);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
