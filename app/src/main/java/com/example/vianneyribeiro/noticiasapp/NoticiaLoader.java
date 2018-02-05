package com.example.vianneyribeiro.noticiasapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by vianneyribeiro on 28/01/18.
 */

public class NoticiaLoader extends AsyncTaskLoader<List<Noticia>> {

    private String mUrl;

    public NoticiaLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Noticia> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of books.
        return QueryUtils.fetchStoryData(mUrl);
    }

}
