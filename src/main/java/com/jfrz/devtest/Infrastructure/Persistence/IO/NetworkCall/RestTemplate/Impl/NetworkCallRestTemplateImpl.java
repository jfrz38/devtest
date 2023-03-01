package com.jfrz.devtest.Infrastructure.Persistence.IO.NetworkCall.RestTemplate.Impl;

import com.jfrz.devtest.Infrastructure.Persistence.IO.NetworkCall.NetworkCall;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public final class NetworkCallRestTemplateImpl implements NetworkCall {

    private final RestTemplate restTemplate;

    @Override
    public <T> T callGetApi(String url, Class<T> response) {
        return restTemplate.getForEntity(url, response).getBody();
    }


}

