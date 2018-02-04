package com.example.vianneyribeiro.noticiasapp;

import android.widget.ArrayAdapter;

/**
 * Created by vianneyribeiro on 28/01/18.
 */

public class Noticia  {

    /**
     * Story information member fields.
     */
    private String mTitle;
    private String mSection;
    private String mWebURL;

    /**
     * Constructor.
     */
    public Noticia(String title, String section, String url) {
        mTitle = title;
        mSection = section;
        mWebURL = url;
    }

    /**
     * Getters.
     */
    public String getTitle() {
        return mTitle;
    }

    public String getSection() {
        return mSection;
    }

    public String getWebURL() {
        return mWebURL;
    }
}
