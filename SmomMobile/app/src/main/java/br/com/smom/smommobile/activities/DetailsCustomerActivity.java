package br.com.smom.smommobile.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import br.com.smom.smommobile.R;

public class DetailsCustomerActivity extends AppCompatActivity {

    private int customerId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_customer);

        TextView nome = (TextView) findViewById(R.id.txNomeCusomer);
        TextView documento = (TextView) findViewById(R.id.txDocumentoCustomer);

        customerId = getIntent().getExtras().getInt("id");
        nome.setText(getIntent().getExtras().getString("nome"));
        documento.setText(getIntent().getExtras().getString("documento"));
    }

    public void voltar(View view) {
        finish();
    }

    public void details(View view) {
        Intent intent = new Intent(DetailsCustomerActivity.this, ReleaseActivity.class);
        intent.putExtra("id", customerId);
        startActivity(intent);
    }
}
