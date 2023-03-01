package com.jfrz.devtest.Infrastructure.Persistence.IO.NetworkCall;

public interface NetworkCall {
    public <T> T callGetApi(String url, Class<T> response);
}
