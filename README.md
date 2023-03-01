# Backend DevTest

Algunas de las consideraciones a tener en cuenta para esta prueba han sido:

## División en capas de Clean Architecture

Se ha decidido dividir el proyecto en tres capas: Infraestructura, Dominio y Servicio.

Generalmente en proyectos de Spring Boot se suele ver "Controlador, Repositorio y Servicio" aunque personalmente he
utilizado esta otra opción y me gusta mucho más, ya que tanto controlador como repositorio dependen de implementaciones
externas por lo que encajan mejor en la capa de infraestructura.

También se ha hecho una división más para cada controlador y servicio y así cumplir en la medida de lo posible SRP y es
añadir en cada paquete la acción que realiza (Find, Delete, Update...) y así se evita tener una clase
gigante (`ProductsService` por ejemplo) donde todas las acciones encajan bien por el propio nombre de la clase y acaba
aglutinando toda la lógica del microservicio. Por lo que dividiendo en, por ejemplo, `FindProductService`
y `UpdateProductService` cada acción quedaría aislada y cada clase tendría menos responsabilidades.

Y una última división es nombrar la carpeta con la implementación final. Lo más normal en proyectos de Spring Boot es
ver un repositorio con nombre similar a `ProductRepositoryImpl` pero considero que es mejor añadir más semántica e
indicar cuál es la implementación concreta para poder modificarla en otro momento. Por ejemplo, si se utiliza una BD
MySQL un buen nombre sería `ProductRepositoryMySqlImpl` y así si en el futuro se cambia de BD se utiliza otra clase que
aprovechará la interfaz y será inyectada.

## Inversión de dependencias para inyectar implementaciones finales

Siguiendo la línea anterior, un principio SOLID muy útil es DI y siempre que se pueda se debe usar, no solo
entre `controlador - servicio - repositorio` sino que considero que lo ideal es -sobre todo- aplicarlo a
implementaciones concretas.

Las implementaciones concretas acaban siendo clases o librerías externas por lo que quedan fuera de nuestro alcance y
pueden cambiar (o podemos decidir nosotros que queremos cambiarlas). En este caso esas implementaciones externas serían
el Circuit Breaker y la llamada a otros servicios.

Se puede considerar que el estándar es utilizar `Resilience4J` y `RestTemplate` para estas dos acciones, pero añadir
directamente uno de esos paquetes al servicio o repositorio acoplaría todo el código a ese paquete externo, por lo que
he decidido añadir una interfaz que es implementada por la clase concreta.

Para el caso de las llamadas externas he añadido un ejemplo para ver qué fácil sería cambiar la implementación concreta
con la inversión de dependencias. Es decir, suponemos que por defecto usamos `RestTemplate`, pues tenemos una
implementación final que hace uso del propio `RestTemplate`. Esa clase concreta está acoplada a `RestTemplate` (alguna
tiene que estarlo si se utiliza el paquete) pero es la única en todo el código.  
Suponemos que con el paso del tiempo queremos cambiar a `WebClient`. Si las llamadas con `RestTemplate` se hiciesen
directamente en una clase (servicio/repositorio) habría que modificar esa clase para cambiar el código, la librería a
utilizar, etc, etc... mientras que usando DI e implementaciones concretas únicamente habría que crear un nuevo
componente que implemente la interfaz.

Por lo que inyectando una interfaz `NetworkCall` se puede implementar la clase concreta `NetworkCallRestTemplateImpl`
o `NetworkCallWebClientImpl` sin cambiar la lógica del servicio. Que al fin y al cabo es lo que se busca, que la lógica
de negocio no dependa de las librerías que se utilizan.

## Llamadas a servicios externos en el repositorio y no en el servicio

Otro punto que creo que no sigue el "estándar" de Spring Boot (o lo que he visto en los últimos años) es hacer llamadas
externas a otros microservicios en el repositorio. Hasta ahora he visto más proyectos que lo realizan en el servicio que
en el propio repositorio porque consideran que es parte de la lógica de negocio.

Bajo mi punto de vista esos datos son información externa que ya venga de una BD o de otro servicio son entidades las
cuales se recuperan del repositorio. No considero que la llamada en sí sea lógica de negocio de ESTE microservicio.

La funcionalidad del microservicio actual no es obtener los datos de la llamada externa per se, sino que los utiliza
para generar una respuesta que cumpla los requisitos del negocio. Lo cual es lo mismo que considerar esos datos como una
llamada a BD para obtener información y modelarla según se requiera.

Es por eso que la llamada a servicios externos la he implementado en el repositorio.

## Uso de Circuit Breaker

Circuit Breaker no es algo que hasta ahora haya tenido que programar desde cero pero desde que descubrí el patrón
circuit breaker siempre intento utilizarlo. En este caso al haber diferentes posibles implementaciones he creado una
interfaz y una implementación específica. Si en algún momento más adelante se decide utilizar algún otro paquete externo
para implementar el CB únicamente habría que crear un nuevo componente que implemente la interfaz (como con el uso
de `RestTemplate` y `WebClient`).

## Uso de VOs

Normalmente se utilizan VOs con algo más de lógica (comprobaciones del dominio) pero para este caso concreto no ha sido
necesario. Además, un uso que lo considero bastante acertado y desde que lo conocí lo intento aplicar siempre es para
encapsular el tipo de dato y compartirlo entre capas.

Por ejemplo, para esta prueba los IDs van encapsulados en VOs de tal manera que si en algún momento alguno de ellos
cambia a, por ejemplo, UUID, no habría que modificar todos los métodos e interfaces por donde se pasa el valor, sino que
únicamente sería necesario modificarlo en el VO.
