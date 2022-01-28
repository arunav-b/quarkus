package me.arunav.mutiny

import io.smallrye.mutiny.Uni

fun creatingUnis() {
    val num = 10
    val item: Uni<Int> = Uni.createFrom().item(num)
    val supplier = Uni.createFrom().item { num * 5 }
}

fun creatingFailingUnis() {
    val exception = Uni.createFrom().failure<Int>(Exception("Invalid Int"))
    val exceptionWithASupplier = Uni.createFrom().failure<Int> { Exception("Exception using Supplier") }
}

fun creatingNullUni() {
    val nullItem = Uni.createFrom().nullItem<Int>()
}

fun creatingUniFromEmitter() {
    Uni.createFrom().emitter<String> {
        it.complete("result")
    }
}
