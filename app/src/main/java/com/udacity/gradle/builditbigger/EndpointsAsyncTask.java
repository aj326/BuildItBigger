package com.udacity.gradle.builditbigger;

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

    @Override
    protected String doInBackground(Context... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
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
    protected void onPostExecute(String result) {
        context.startActivity(new Intent(context,DisplayJoke.class).putExtra("joke", result));

//        context.startActivity(jokeIntent);
    }
}