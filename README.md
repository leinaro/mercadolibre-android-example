# mercadolibre-examples

Esta aplicacion muestra una lista de productos y su detalle.
Esta compuesta por 3 vistas: 
- Lista de categorias con algunos productos.
- Lista de todos los productos para una categoria.
- Detalle de un producto.

Usa algunos de los servicios publicos de la plataforma abierta de mercadolibre para desarrolladores https://developers.mercadolibre.com.co/
y un API rest para obtener los datos de categorias y productos.

## Bases

Desarrollado en kotlin, usa Oriented Object Programing y algo de Functional Programing.  

## Arquitectura

La arquitectura usada es UI/Domain/Data, con UseCase/Interactors, esta basada en la arquitectura recomendada en https://developer.android.con/jetpack/guide?#recommended-app-arch y en el blog The Clean Architecture https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html.

El proyecto esta organizado de la manera que hay una actividad principal con un navigation component, y cada fragment se comunica con un viewmodel, este viewmodel tiene una dependencia con uno o varios casos de uso, los casos de uso tiene una dependencia con el repositorio y el repositorio tiene dependencias con el cliente remoto, cliente local y los mappers.

La estructura de carpetas es la siguiente:
- presentation // fragmentos y viewmodels
  - Category 
  - Product
  - ProductDetails
  - Models // modelo de datos para la capa de presentación
- domain
  - Mapper // clases encargadas de mapeo de datos (modelos de la capa de datasource a la capa de presentación)
  - Repository // maneja las operacion de datos, llamadas al API o Bases de datos de la capa datasource 
  - UseCase // contratos entre la capa de domain y presentation divididos por caso de uso
- datasource
  - local // room database
  - remote // retrofit2 y mercadolibre API
  - model // modelo de datos de la capa de datos

## Mercadolibre Services

Podrás ver la implementacion de los siguientes endpoing

- Obtener todas las categories para Colombia(MCO)
GET https://api.mercadolibre.com/sites/MCO/categories"
- Obtener los productos por categoria
GET https://api.mercadolibre.com/sites/MCO/search?category={categoryId}"
- Obtener el detalle de un producto incluidas las imagenes
GET https://api.mercadolibre.com/items/{productId}"
- Obtener la description de un producto
GET https://api.mercadolibre.com/tems/{productId}/description"

## Design Patterns
- Creacionales: Dependency Injection, Singleton,
- Estructurales: MVVM
- Comportamiento: Observer

## Android Architecture Components
- LiveData
- ViewModel
- Room 

## Jetpack
- Couroutines + Flow
- Navigation
- Databinding
- Viewbinding
- lifecycle
- viewpager
- material design


## Other Dependencies
- Hilt
- Glide
- Mockito
- Retrofit2
- Okhttp
