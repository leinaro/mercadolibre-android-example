package com.leinaro.mercadolibre_android_example.datasource.model

data class SearchQueryResponse(
    val results: List<ProductRemote>,
)

data class ProductRemote(
    val id: String,
    val title: String,
    val price: String,
    val thumbnail: String,
    var category_id: String,
    var pictures: List<PicturesRemote>?,
)

data class PicturesRemote(
    val id: String,
    val url: String,
    var productId: String? = null,
)
/*
{
    "id": "MCO517140144",
    "site_id": "MCO",
    "title": "Planta Electrica Jd3500 2 8kw Sukra Sku39554",
    "subtitle": null,
    "seller_id": 192473432,
    "category_id": "MCO113731",
    "official_store_id": 289,
    "price": 1279000,
    "base_price": 1279000,
    "original_price": 1560270,
    "currency_id": "COP",
    "initial_quantity": 181,
    "available_quantity": 100,
    "sold_quantity": 25,
    ...
    "buying_mode": "buy_it_now",
    "listing_type_id": "gold_special",
    "start_time": "2019-06-12T13:54:28.000Z",
    "stop_time": "2039-06-07T04:00:00.000Z",
    "condition": "new",
    "permalink": "https://articulo.mercadolibre.com.co/MCO-517140144-planta-electrica-jd3500-2-8kw-sukra-sku39554-_JM",
    "thumbnail_id": "823255-MCO42000555857_052020",
    "thumbnail": "http://http2.mlstatic.com/D_823255-MCO42000555857_052020-I.jpg",
    "secure_thumbnail": "https://http2.mlstatic.com/D_823255-MCO42000555857_052020-I.jpg",
    "pictures": [
        {
            "id": "823255-MCO42000555857_052020",
            "url": "http://http2.mlstatic.com/D_823255-MCO42000555857_052020-O.jpg",
            "secure_url": "https://http2.mlstatic.com/D_823255-MCO42000555857_052020-O.jpg",
            "size": "500x400",
            "max_size": "813x652",
            "quality": ""
        },
        {
            "id": "827308-MCO42000555858_052020",
            "url": "http://http2.mlstatic.com/D_827308-MCO42000555858_052020-O.jpg",
            "secure_url": "https://http2.mlstatic.com/D_827308-MCO42000555858_052020-O.jpg",
            "size": "386x500",
            "max_size": "772x1000",
            "quality": ""
        }
    ],
    "video_id": null,
    "descriptions": [
        {
            "id": "MCO517140144-2134237251"
        }
    ],
    "accepts_mercadopago": true,
    "non_mercado_pago_payment_methods": [],
    ....
    "status": "active",
    ...
}
 */