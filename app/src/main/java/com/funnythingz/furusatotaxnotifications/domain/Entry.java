package com.funnythingz.furusatotaxnotifications.domain;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "entry")
public class Entry {

    @Element(name = "id")
    String id;

    @Element(name = "title")
    String title;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
