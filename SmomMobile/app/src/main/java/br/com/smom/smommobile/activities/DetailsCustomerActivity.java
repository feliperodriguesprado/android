package br.com.smom.smommobile.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import br.com.smom.smommobile.R;

public class DetailsCustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_customer);

        TextView nome = (TextView) findViewById(R.id.txNomeCusomer);
        TextView documento = (TextView) findViewById(R.id.txDocumentoCustomer);

        String id = getIntent().getExtras().getString("id");

        nome.setText(getIntent().getExtras().getString("nome"));
        documento.setText(getIntent().getExtras().getString("documento"));
    }

    public void voltar(View view){
        finish();
    }
}
