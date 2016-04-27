package com.udacity.gradle.builditbigger;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

import io.github.jokedisplay.aj326.DisplayJoke;

/**
 * Created by ahmed on 4/23/16.
 */
class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private ProgressDialog progressDialog;

    public EndpointsAsyncTask(ProgressDialog pd) {
        this.progressDialog = pd;
        progressDialog.setCancelable(true);
    }

    @Override
    protected String doInBackground(Context... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                                                      new AndroidJsonFactory(), null)
                    .setRootUrl("https://peaceful-field-128921.appspot.com/_ah/api/");
            myApiService = builder.build();
        }
        context = params[0];
        try {
            return myApiService.fetchJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setMessage("Fethching Joke");
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(String result) {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        context.startActivity(new Intent(context,DisplayJoke.class).putExtra("joke", result));

    }
}