package com.funnythingz.furusatotaxnotifications.infra;

import com.funnythingz.furusatotaxnotifications.domain.Author;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "feed")
public class GetFurusatoTaxNotificationsFeed {

    @Element(name = "id")
    String id;

    @Element(name = "title")
    String title;

    @Element(name = "updated")
    String updated;

    @Element(name = "author")
    Author author;

    @Element(name = "rights")
    String rights;

    @Element(name = "link")
    String link;
}
