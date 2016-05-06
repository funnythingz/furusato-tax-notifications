package com.funnythingz.furusatotaxnotifications.presentation.adapter.holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.funnythingz.furusatotaxnotifications.R;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FurusatoTaxTopicsViewHolder {

    @Bind(R.id.furusato_tax_topics_list)
    public LinearLayout furusatoTaxTopicsList;

    @Bind(R.id.furusato_tax_topic_entry_title)
    public TextView entryTitle;

    @Bind(R.id.furusato_tax_topic_entry_description)
    public HtmlTextView entryDescription;

    @Bind(R.id.furusato_tax_topic_entry_pubdate)
    public TextView entryPubdate;

    @Bind(R.id.furusato_tax_topic_entry_author)
    public TextView entryAuthor;

    public FurusatoTaxTopicsViewHolder(View view) {
        ButterKnife.bind(this, view);
    }
}
