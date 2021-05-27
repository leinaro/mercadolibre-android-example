package com.leinaro.mercadolibre_android_example.domain.model

data class Product(
    val id: String,
    val title: String,
    val price: Number,
    val thumbnail: String,
)
/*
{
     "id": "MCO533552419",
     "site_id": "MCO",
     "title": "Dieta Barf Bajo En Grasa Con Fibra 500 G - kg a $28",
     "seller": {
       "id": 88804054,
       "permalink": null,
       "registration_date": null,
       "car_dealer": false,
       "real_estate_agency": false,
       "tags": null
     },
     "price": 2900,
     "prices": {
       "id": "MCO533552419",
       "prices": [
         {
           "id": "c4724b42-adb8-4ad1-8d49-a9dfdebc8240",
           "type": "standard",
           "conditions": {
             "context_restrictions": [],
             "start_time": null,
             "end_time": null,
             "eligible": true
           },
           "amount": 2900,
           "regular_amount": null,
           "currency_id": "COP",
           "exchange_rate_context": "DEFAULT",
           "metadata": {},
           "last_updated": "2020-09-06T22:50:02Z"
         }
       ],
       "presentation": {
         "display_currency": "COP"
       },
       "payment_method_prices": [],
       "reference_prices": [],
       "purchase_discounts": []
     },
     "sale_price": null,
     "currency_id": "COP",
     "available_quantity": 50000,
     "sold_quantity": 5000,
     "buying_mode": "buy_it_now",
     "listing_type_id": "gold_pro",
     "stop_time": "2040-01-05T16:23:17.000Z",
     "condition": "new",
     "permalink": "https://articulo.mercadolibre.com.co/MCO-533552419-dieta-barf-bajo-en-grasa-con-fibra-500-gr-2000-_JM",
     "thumbnail": "http://http2.mlstatic.com/D_909472-MCO40389689836_012020-I.jpg",
     "thumbnail_id": "909472-MCO40389689836_012020",
     "accepts_mercadopago": true,
     "installments": {
       "quantity": 12,
       "amount": 241.67,
       "rate": 0,
       "currency_id": "COP"
     },
     "address": {
       "state_id": "CO-DC",
       "state_name": "Bogotá D.C.",
       "city_id": "TUNPQ0VORzkzMTUz",
       "city_name": "Engativá"
     },
     "shipping": {
       "free_shipping": false,
       "mode": "not_specified",
       "tags": [
         "me2_blocked"
       ],
       "logistic_type": "not_specified",
       "store_pick_up": false
     },
     "seller_address": {
       "id": "",
       "comment": "",
       "address_line": "",
       "zip_code": "",
       "country": {
         "id": "CO",
         "name": "Colombia"
       },
       "state": {
         "id": "CO-DC",
         "name": "Bogotá D.C."
       },
       "city": {
         "id": "TUNPQ0VORzkzMTUz",
         "name": "Engativá"
       },
       "latitude": "",
       "longitude": ""
     },
     "attributes": [
       {
         "id": "BRAND",
         "value_id": null,
         "value_name": "happyBARF",
         "values": [
           {
             "id": null,
             "name": "happyBARF",
             "struct": null,
             "source": 1505
           }
         ],
         "attribute_group_name": "Otros",
         "source": 1505,
         "name": "Marca",
         "value_struct": null,
         "attribute_group_id": "OTHERS"
       },
       {
         "value_struct": null,
         "values": [
           {
             "id": "2230284",
             "name": "Nuevo",
             "struct": null,
             "source": 6808261514773724
           }
         ],
         "attribute_group_name": "Otros",
         "id": "ITEM_CONDITION",
         "value_id": "2230284",
         "attribute_group_id": "OTHERS",
         "source": 6808261514773724,
         "name": "Condición del ítem",
         "value_name": "Nuevo"
       },
       {
         "id": "UNITS_PER_PACKAGE",
         "value_name": "15",
         "value_struct": null,
         "values": [
           {
             "id": null,
             "name": "15",
             "struct": null,
             "source": 6808261514773724
           }
         ],
         "attribute_group_name": "Otros",
         "source": 6808261514773724,
         "name": "Unidades por envase",
         "value_id": null,
         "attribute_group_id": "OTHERS"
       }
     ],
     "differential_pricing": {
       "id": 33602181
     },
     "original_price": null,
     "category_id": "MCO1077",
     "official_store_id": null,
     "domain_id": "MCO-CATS_AND_DOGS_FOODS",
     "catalog_product_id": null,
     "tags": [
       "good_quality_picture",
       "good_quality_thumbnail",
       "loyalty_discount_eligible",
       "immediate_payment",
       "best_seller_candidate"
     ],
     "order_backend": 1,
     "use_thumbnail_id": true
   }
 */