package com.github.olegzuev.yukarinotes.data

interface Item {
    val itemId: Int
    val itemName: String
    val iconUrl: String
    val itemType: ItemType
}

enum class ItemType {
    GENERAL_ITEM,
    EQUIPMENT,
    EQUIPMENT_PIECE
}