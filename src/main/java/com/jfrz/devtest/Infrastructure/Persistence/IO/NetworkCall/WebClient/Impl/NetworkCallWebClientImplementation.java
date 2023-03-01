package com.jfrz.devtest.Infrastructure.Persistence.IO.NetworkCall.WebClient.Impl;

import com.jfrz.devtest.Infrastructure.Persistence.IO.NetworkCall.NetworkCall;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

// Esta clase es para comprobar que al invertir dependencias (usar interfaces) en el momento
// en que queramos cambiar la implementación concreta se puede hacer de manera sencilla.

// Se ha implementado RestTemplate como método de llamadas a un servicio externo pero si en algún
// momento se quieren cambiar a WebClient, ni el repositorio ni el Circuit Breaker se ven afectados
// ya que ha inyectado una interfaz (NetworkApiCall) y no la implementación concreta.

// Al ser la implementación concreta elegida RestTemplate, esta clase no puede ser @Component porque se intentarían
// inyectar las dos para la interfaz NetworkApiCall

//@Component
@RequiredArgsConstructor
public final class NetworkCallWebClientImplementation implements NetworkCall {

    private final WebClient webClient;

    @Override
    public <T> T callGetApi(String url, Class<T> response) {
        return webClient.get().uri(url).retrieve().bodyToMono(response).block();
    }
}
