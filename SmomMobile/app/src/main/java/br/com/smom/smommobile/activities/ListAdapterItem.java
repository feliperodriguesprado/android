package br.com.smom.smommobile.activities;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.smom.smommobile.R;
import br.com.smom.smommobile.entities.CustomerEntity;

/**
 * Created by Diego on 01/12/2015.
 */
public class ListAdapterItem extends ArrayAdapter<CustomerEntity>{

    private Context context;
    private ArrayList<CustomerEntity> lista;

    public ListAdapterItem(Context context, ArrayList<CustomerEntity> lista) {
        super(context, 0, lista);
        this.context = context;
        this.lista = lista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomerEntity itemPosicao = this.lista.get(position);

        TextView textView = (TextView) convertView.findViewById(R.id.txCustumer);
        textView.setText(itemPosicao.getName());

        return convertView;
    }
}
