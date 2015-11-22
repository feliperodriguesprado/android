package android.prototypes.taskmanager.services;

import android.content.Context;
import android.os.AsyncTask;
import android.prototypes.taskmanager.to.LocationTO;
import android.prototypes.taskmanager.utils.HTTPUtil;
import android.widget.Toast;

import com.google.gson.Gson;

public class SearchCEPService extends AsyncTask<String, Void, String> {

    private Context context;
    private LocationTO location;

    public SearchCEPService (Context context) {
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
        Gson gson = new Gson();
        setLocation(gson.fromJson(result, LocationTO.class));
        Toast.makeText(context, getLocation().getLocality(), Toast.LENGTH_LONG).show();
    }

    public LocationTO getLocation() {
        return location;
    }

    public void setLocation(LocationTO location) {
        this.location = location;
    }
}
