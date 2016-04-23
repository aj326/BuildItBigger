package com.udacity.gradle.builditbigger;


/**
 * This is a shadow class for AsyncTask which forces it to run synchronously.
 */
public abstract class AsyncTask<Params, Progress, Result> {

    protected abstract Result doInBackground();

    protected String onPostExecute(Result result) {
        return (String) result;
    }

    protected void onProgressUpdate(Progress... values) {
    }

    public String execute() {
        Result result = doInBackground();
        onPostExecute(result);
        return onPostExecute(result);
    }

    protected abstract String onPostExecute(String result);
}
