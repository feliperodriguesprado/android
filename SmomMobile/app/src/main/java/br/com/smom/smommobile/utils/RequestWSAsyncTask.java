package br.com.smom.smommobile.utils;

import android.content.Context;
import android.os.AsyncTask;

public class RequestWSAsyncTask extends AsyncTask<String, Void, String> {

    private Context context;
    private HTTPUtil httpUtil = new HTTPUtil();
    private String result;

    public RequestWSAsyncTask(Context context) {
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
            contentResponseGET = httpUtil.get(url);
        }

        setResult(contentResponseGET);
        cancel(true);
        return contentResponseGET;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        setResult(result);
    }

    public String getResult() {
        return result;
    }

    private void setResult(String result) {
        this.result = result;
    }
}
