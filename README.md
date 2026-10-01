# moneycalculator

## Descripción

Money Calculator es una aplicación en Java que convierte una cantidad de dinero de una moneda a otra usando los tipos de cambio reales de la API de [ExchangeRate-API](https://www.exchangerate-api.com/). Las respuestas de la API vienen en JSON y se leen con la librería Gson.

Tiene una interfaz gráfica hecha con Swing. Al abrirla se cargan todas las monedas disponibles en dos desplegables. Escribes una cantidad, eliges la moneda de origen y la de destino, pulsas **Convertir** y abajo aparece el resultado. Por defecto viene puesto 100 EUR a USD.

El proyecto está organizado en paquetes:

- **model**: los datos de la aplicación como `record`: `Currency` (código y nombre), `Money` (cantidad y moneda) y `ExchangeRate` (fecha, moneda origen, moneda destino y tipo de cambio).
- **io**: las interfaces `CurrencyLoader` y `ExchangeRateLoader`, que sirven para cargar las monedas y los tipos de cambio sin depender de dónde salen.
- **ui**: las interfaces `MoneyDialog`, `CurrencyDialog` y `MoneyDisplay`, que representan de dónde se lee el dinero, a qué moneda se quiere pasar y dónde se muestra el resultado.
- **control**: el patrón Command, con la interfaz `Command` y `ExchangeMoneyCommand`, que junta todo lo anterior para hacer la conversión.
- **swing**: la interfaz gráfica. `SwingMoneyDialog`, `SwingCurrencyDialog` y `SwingMoneyDisplay` implementan las interfaces de `ui`, y `MainFrame` es la ventana que las junta con el botón de convertir.
- **application**: `WebService`, que implementa los loaders llamando a la API, y `Main`, que lo conecta todo.

## Mejoras realizadas

- **Interfaz gráfica con Swing**: antes el programa solo imprimía por consola un tipo de cambio entre las dos primeras monedas de la lista. Ahora hay una ventana, como en el image-viewer, con un campo para la cantidad, dos desplegables de monedas, un botón **Convertir** y una etiqueta para el resultado. Se creó el paquete `swing` con una clase por cada interfaz de `ui`, así que no ha hecho falta cambiar el `ExchangeMoneyCommand` para usarlo con la ventana.
- **Uso del `ExchangeMoneyCommand`**: antes el comando no se usaba en ningún sitio. Ahora `Main` lo crea con los componentes de la ventana y se ejecuta al pulsar el botón.
- **Monedas por defecto**: en vez de coger `currencies.get(0)` y `currencies.get(1)`, se añadió el método `find` para buscar `EUR` y `USD` por su código y dejarlas seleccionadas al abrir.
- **`toString` en `Currency`**: para que en los desplegables salga `EUR - Euro` en vez de `Currency[code=EUR, country=Euro]`.
- **`toString` en `Money`**: el resultado se muestra con dos decimales y el código de la moneda (por ejemplo `113,42 USD`) en vez del `toString` por defecto del record.
- **Validación en `Money`**: no se permite crear dinero con cantidad negativa.
- **Mensajes de error**: si la cantidad no es un número, si es negativa o si falla la conexión con la API, sale una ventana de error en vez de petar el programa. También si no se pueden cargar las monedas al empezar.
- **Misma moneda**: en `ExchangeMoneyCommand`, si la moneda de origen y la de destino son la misma, se devuelve el dinero directamente sin llamar a la API.
- **Quitar `new URL(...)`**: el constructor de `URL` está deprecado en las versiones nuevas de Java, así que se cambió por `URI.create(...).toURL()`.

## Cómo ejecutarlo

Es un proyecto Maven con Java 21. Hay que ejecutar la clase `software.ulpgc.moneycalculator.application.Main` y se abre la ventana. Hace falta conexión a internet para llamar a la API.
