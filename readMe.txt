Project Setup

Import Maven Application in your project

Open application.properties and define
 
	epam.products=[{"name":"Apple","price":0.60},{"name":"Orange","price":0.25},{"name":"Mango","price":1.99}]

	epam.offers=[{"productName":"Apple","type":"BUY_ONE_GET_ONE_FREE","status":true,"buyQuantity":1,"freeQuantity":1},{"productName":"Orange","type":"BUY_TWO_GET_ONE_FREE","status":true,"buyQuantity":2,"freeQuantity":1}]

	epam.tax=7.5
	
Start Application using below class
	com.epam >> StartEpamApplication.java
	
Run unit tests below packages
	src/test/java
		com.epam.controller.
	
Acces Below WebService for list of Product

	GET http://localhost:8080/products
	Authorization : Username/Password : admin/password
	Response:
	[
		{
			"id": 1,
			"name": "Apple",
			"description": "",
			"price": 0.6
		},
		{
			"id": 2,
			"name": "Orange",
			"description": "",
			"price": 0.25
		},
		{
			"id": 3,
			"name": "Mango",
			"description": "",
			"price": 1.99
		}
	]
		
Acces Below WebService for list of Order
		
	GET http://localhost:8080/orders	
	Authorization : Username/Password : admin/password
	Response:
	[
		{
			"id": 6,
			"customerInfo": "",
			"description": "",
			"subTotal": 0.0,
			"total": 0.0,
			"tax": 0.0,
			"status": "Complete",
			"itemList": []
		},
		{
			"id": 7,
			"customerInfo": "",
			"description": "",
			"subTotal": 0.0,
			"total": 0.0,
			"tax": 0.0,
			"status": "Complete",
			"itemList": []
		},
		{
			"id": 9,
			"customerInfo": "Kalpesh",
			"description": "",
			"subTotal": 22.099999999999998,
			"total": 23.641860465116277,
			"tax": 1.5418604651162788,
			"status": null,
			"itemList": [
				{
					"id": 10,
					"productId": 4,
					"orderId": 9,
					"description": "",
					"quantity": 3,
					"itemTotalPrice": 0.6
				},
				{
					"id": 11,
					"productId": 5,
					"orderId": 9,
					"description": "",
					"quantity": 6,
					"itemTotalPrice": 0.6
				},
				{
					"id": 12,
					"productId": 6,
					"orderId": 9,
					"description": "",
					"quantity": 10,
					"itemTotalPrice": 0.6
				}
			]
		}
	]
	
Acces Below WebService for retrieve Order By ID

	GET http://localhost:8080/orders/9
	Authorization : Username/Password : admin/password
	Response:
	{
		"id": null,
		"customerInfo": "",
		"description": "",
		"subTotal": 22.099999999999998,
		"total": 23.641860465116277,
		"tax": 1.5418604651162788,
		"status": null,
		"itemList": [
			{
				"id": null,
				"productId": 1,
				"orderId": null,
				"description": "",
				"quantity": 3,
				"itemTotalPrice": 1.2
			},
			{
				"id": null,
				"productId": 2,
				"orderId": null,
				"description": "",
				"quantity": 6,
				"itemTotalPrice": 1.0
			},
			{
				"id": null,
				"productId": 3,
				"orderId": null,
				"description": "",
				"quantity": 10,
				"itemTotalPrice": 19.9
			}
		]
	}
	
Acces Below WebService for checkout order and calculate price
	
	POST http://localhost:8080/order/checkout
	Authorization : Username/Password : admin/password
	Request:
	[
        {
            "productId": 1,            
            "quantity": 3
        },
         {
            "productId": 2,            
            "quantity": 6
        },
         {
            "productId": 3,            
            "quantity": 10
        }        
    ]
	Response:
	{
		"id": null,
		"customerInfo": "",
		"description": "",
		"subTotal": 22.099999999999998,
		"total": 23.641860465116277,
		"tax": 1.5418604651162788,
		"status": null,
		"itemList": [
			{
				"id": null,
				"productId": 1,
				"orderId": null,
				"description": "",
				"quantity": 3,
				"itemTotalPrice": 1.2
			},
			{
				"id": null,
				"productId": 2,
				"orderId": null,
				"description": "",
				"quantity": 6,
				"itemTotalPrice": 1.0
			},
			{
				"id": null,
				"productId": 3,
				"orderId": null,
				"description": "",
				"quantity": 10,
				"itemTotalPrice": 19.9
			}
		]
	}

Acces Below WebService for place order 

	POST http://localhost:8080/order/placeOrder
	Authorization : Username/Password : admin/password
	Request:
	{
		"customerInfo": "Kalpesh",
		"description": "",
		"subTotal": 22.099999999999998,
		"total": 23.641860465116277,
		"tax": 1.5418604651162788,
		"itemList": [
			{
				"productId": 4,
				"description": "",
				"quantity": 3,
				"itemTotalPrice": 1.2
			},
			{
				"productId": 5,
				"description": "",
				"quantity": 6,
				"itemTotalPrice": 1.0
			},
			{
				"productId": 6,
				"description": "",
				"quantity": 10,
				"itemTotalPrice": 19.9
			}
		]
	}
	Response:
	{
		"id": null,
		"customerInfo": "",
		"description": "",
		"subTotal": 22.099999999999998,
		"total": 23.641860465116277,
		"tax": 1.5418604651162788,
		"status": null,
		"itemList": [
			{
				"id": null,
				"productId": 1,
				"orderId": null,
				"description": "",
				"quantity": 3,
				"itemTotalPrice": 1.2
			},
			{
				"id": null,
				"productId": 2,
				"orderId": null,
				"description": "",
				"quantity": 6,
				"itemTotalPrice": 1.0
			},
			{
				"id": null,
				"productId": 3,
				"orderId": null,
				"description": "",
				"quantity": 10,
				"itemTotalPrice": 19.9
			}
		]
	}
	
	
	
		