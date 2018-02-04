package com.example.vianneyribeiro.noticiasapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Noticia>> {

    private static final String GUARDIAN_NEWS_BASE_URL = "http://content.guardianapis.com/";
    private static final String SEARCH = GUARDIAN_NEWS_BASE_URL + "search?q=debate&tag=politics/politics&from-date=2014-01-01&api-key=test";
    private static final int NOTICIA_LOADER_ID = 1;
    private TextView mEmptyStateTextView;
    private NoticiaAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView noticiaListView = findViewById(R.id.list);

        mEmptyStateTextView = findViewById(R.id.empty_view);
        noticiaListView.setEmptyView(mEmptyStateTextView);

        mAdapter = new NoticiaAdapter(this, new ArrayList<Noticia>());
        noticiaListView.setAdapter(mAdapter);

        noticiaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Noticia currentNoticia = mAdapter.getItem(position);
                assert currentNoticia != null;
                Uri NoticiaWebUri = Uri.parse(currentNoticia.getWebURL());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, NoticiaWebUri);
                startActivity(websiteIntent);
            }
        });

        // Check network connectivity.
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connMgr != null;
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(NOTICIA_LOADER_ID, null, this);
        } else {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
    }


    @Override
    public Loader<List<Noticia>> onCreateLoader(int i, Bundle bundle) {
        return new NoticiaLoader(this, SEARCH);
    }

    @Override
    public void onLoadFinished(Loader<List<Noticia>> loader, List<Noticia> stories) {
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        mEmptyStateTextView.setText(R.string.no_news_found);

        mAdapter.clear();
        if (stories != null && !stories.isEmpty()) {
            mAdapter.addAll(stories);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Noticia>> loader) {
        mAdapter.clear();
    }

}

