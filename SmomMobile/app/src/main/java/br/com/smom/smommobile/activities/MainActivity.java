package br.com.smom.smommobile.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.com.smom.smommobile.R;
import br.com.smom.smommobile.controllers.CustomerController;
import br.com.smom.smommobile.controllers.ReleasesController;
import br.com.smom.smommobile.entities.CustomerEntity;

public class MainActivity extends Activity {

    private CustomerController customerController;
    private ReleasesController releasesController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customerController = new CustomerController(this);

        List<CustomerEntity> customerList = customerController.getListAll();
        ListAdapterItem listaAdapterItem = new ListAdapterItem(this, customerList);

        final ListView listView = (ListView) findViewById(R.id.listViewCustumers);

        listView.setAdapter(listaAdapterItem);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomerEntity customerEntity = (CustomerEntity) listView.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, DetailsCustomerActivity.class);
                intent.putExtra("id", customerEntity.getId());
                intent.putExtra("nome", customerEntity.getName());
                intent.putExtra("documento", customerEntity.getCpfCnpj());
                startActivity(intent);

            }
        });

    }

    public void sair(View view){
        finish();
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
