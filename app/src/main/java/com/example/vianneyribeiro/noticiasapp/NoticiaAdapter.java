package com.example.vianneyribeiro.noticiasapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by vianneyribeiro on 28/01/18.
 */

public class NoticiaAdapter extends ArrayAdapter<Noticia>{


    public NoticiaAdapter(Context context, List<Noticia> stories) {
        super(context, 0, stories);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate list item view.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.noticia_item, parent, false);
        }

        // Alternative background colors.
        if (position % 2 == 1) {
            listItemView.setBackgroundColor(Color.GRAY);
        } else {
            listItemView.setBackgroundColor(Color.WHITE);
        }

        Noticia currentNoticia = getItem(position);

        TextView titleView = listItemView.findViewById(R.id.noticia_title);
        String title = currentNoticia.getTitle();
        titleView.setText(title);

        TextView descriptionView = listItemView.findViewById(R.id.noticia_url);
        String url = currentNoticia.getWebURL();
        descriptionView.setText(url);

        TextView authorView = listItemView.findViewById(R.id.noticia_section);
        String section = currentNoticia.getSection();
        authorView.setText(section);

        return listItemView;
    }

}
