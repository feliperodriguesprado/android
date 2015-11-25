package br.com.smom.smommobile.utils;

import android.content.Context;
import android.os.AsyncTask;

public class ServiceUtil extends AsyncTask<String, Void, String> {

    private Context context;
    private String result;

    public ServiceUtil(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {

        String contentResponseGET = null;
        String url = "";

        for (int i = 1; i < params.length; i++) {
            url += params[i];
        }

        if (params[0].equals("GET")) {
            contentResponseGET = HTTPUtil.get(url);
        }

        return contentResponseGET;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
