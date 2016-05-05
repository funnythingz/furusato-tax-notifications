package com.funnythingz.furusatotaxnotifications.domain;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "author")
public class Author {

    @Element(name = "name")
    String name;

    @Element(name = "uri")
    String uri;

    public String getName() {
        return name;
    }

    public String getUri() {
        return uri;
    }
}
