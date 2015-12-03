package br.com.smom.smommobile.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.smom.smommobile.R;
import br.com.smom.smommobile.entities.CustomerEntity;

/**
 * Created by Diego on 01/12/2015.
 */
public class ListAdapterItem extends ArrayAdapter<CustomerEntity>{

    private Context context;
    private List<CustomerEntity> lista;

    public ListAdapterItem(Context context, List<CustomerEntity> lista) {
        super(context, 0, lista);
        this.context = context;
        this.lista = lista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomerEntity itemPosicao = this.lista.get(position);

        convertView = LayoutInflater.from(this.context).inflate(R.layout.custumer, null);

        TextView textView = (TextView) convertView.findViewById(R.id.txCustumer);
        textView.setText(itemPosicao.getName());

        return convertView;
    }
}
