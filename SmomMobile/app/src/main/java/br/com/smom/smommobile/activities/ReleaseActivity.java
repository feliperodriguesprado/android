package br.com.smom.smommobile.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.smom.smommobile.R;
import br.com.smom.smommobile.controllers.CustomerController;
import br.com.smom.smommobile.controllers.ReleasesController;
import br.com.smom.smommobile.entities.CustomerEntity;
import br.com.smom.smommobile.entities.ReleaseEntity;

public class ReleaseActivity extends FragmentActivity {

    MyPageAdapter pageAdapter;
    private CustomerController customerController;
    private ReleasesController releasesController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release);

        customerController = new CustomerController(getApplicationContext());
        releasesController = new ReleasesController(getApplicationContext());

        CustomerEntity customer = customerController.getById(getIntent().getExtras().getInt("id"));
        List<ReleaseEntity> releaseList = releasesController.getListByCustomer(customer.getId());

        List<Fragment> fragments = getFragments(customer, releaseList);

        pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);

        ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setAdapter(pageAdapter);

    }

    private List<Fragment> getFragments(CustomerEntity customer, List<ReleaseEntity> releaseList) {

        List<Fragment> fList = new ArrayList<Fragment>();

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        StringBuilder text = new StringBuilder("");
        text.append(customer.getName());
        text.append("\n\n" + customer.getCpfCnpj());
        fList.add(ReleaseFragment.newInstance(text.toString()));

        for (ReleaseEntity release : releaseList) {
            StringBuilder textRelease = new StringBuilder("");
            textRelease.append("Tipo:\n" + (release.getType() == 1 ? "Conta a receber" : "Conta a pagar"));
            textRelease.append("\n\nCódigo:\n" + release.getId());
            textRelease.append("\n\nDescrição:\n" + release.getDescription());
            textRelease.append("\n\nData de vencimento:\n" + df.format(release.getDueDate()));
            textRelease.append("\n\nValor:\n" + release.getValue());
            fList.add(ReleaseFragment.newInstance(textRelease.toString()));
        }

        return fList;
    }

    private class MyPageAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;

        public MyPageAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }
    }
}
