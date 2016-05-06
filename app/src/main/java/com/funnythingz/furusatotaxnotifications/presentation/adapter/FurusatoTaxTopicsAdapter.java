package com.funnythingz.furusatotaxnotifications.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.funnythingz.furusatotaxnotifications.R;
import com.funnythingz.furusatotaxnotifications.domain.Entry;
import com.funnythingz.furusatotaxnotifications.presentation.adapter.holder.FurusatoTaxTopicsViewHolder;

import java.util.List;

public class FurusatoTaxTopicsAdapter extends ArrayAdapter<Entry> {

    public FurusatoTaxTopicsAdapter(Context context, int resource, List<Entry> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        FurusatoTaxTopicsViewHolder holder;

        if (convertView != null) {
            holder = (FurusatoTaxTopicsViewHolder) convertView.getTag();
        } else {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_furusato_tax_topics, parent, false);
            holder = new FurusatoTaxTopicsViewHolder(convertView);
            convertView.setTag(holder);
        }

        Entry entry = getItem(position);

        holder.entryAuthor.setText(entry.getAuthor());
        holder.entryTitle.setText(entry.getTitle());
        if (entry.getPubDate() != null) {
            holder.entryPubdate.setText(entry.getPubDate());
        }

        return convertView;
    }
}
