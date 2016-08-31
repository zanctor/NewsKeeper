package com.mozidev.newskeeper.domain.common;

import javax.inject.Inject;

/**
 * Created by mozi on 31.08.16.
 */
public class RestService {

    private RestInterface rest;

    @Inject
    public RestService(RestInterface rest) {
        this.rest = rest;
    }
}
