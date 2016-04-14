package com.funnythingz.furusatotaxnotifications.infra;

import com.funnythingz.furusatotaxnotifications.domain.Entry;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "feed")
public class GetFurusatoTaxNotificationsFeed {

    @Element(name = "entry")
    Entry entry;
}
