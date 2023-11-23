package com.app1.db

class Items {
    var id: Int = 0
    var itemName: String? = null
    var itemPrice: String? = null

    constructor(name: String,price: String) {
        this.itemName = name
        this.itemPrice = price
    }

    constructor(id: Int, name: String,price: String) {
        this.id = id
        this.itemName = name
        this.itemPrice = price
    }
}