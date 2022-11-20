package com.connor.splashpose.intent

sealed class Event {
    data class ClickMainImage(val photoId: String) : Event()
}

inline fun Event.onClickMainImage(id: (String) -> Unit) {
    if (this is Event.ClickMainImage) id(photoId)
}
