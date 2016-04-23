package com.udacity.gradle.builditbigger;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import junit.framework.TestCase;

import java.io.IOException;

public class EndpointsAsyncTaskTest extends TestCase{
    String answer="";
    AsyncTask tester = new AsyncTask() {
        @Override
        protected String doInBackground() {
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                        .setRootUrl("https://peaceful-field-128921.appspot.com/_ah/api/");
                MyApi myApiService= builder.build();


            try {
                return myApiService.fetchJoke().execute().getData();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected String onPostExecute(String result) {
            answer = result;
            return result;


//        context.startActivity(jokeIntent);
        }
    };

    public void testJoke(){
        assertTrue(tester.execute().length()>0);
    }
}