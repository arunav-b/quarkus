package me.arunav.mutiny

import io.smallrye.mutiny.Uni
import io.smallrye.mutiny.subscription.Cancellable

/**
 * Create Uni using item
 */
fun createUni(): Uni<Int> {
    val num = 10
    val item: Uni<Int> = Uni.createFrom().item(num)
    val item2 = Uni.createFrom().item { num * 5 } // Another way to create a Uni using a Supplier
    return item
}

/**
 *  Subscribe to a Uni
 */
fun subscribeToUni() {
    val cancellable: Cancellable =
        createUni()
            .subscribe()
            .with(
                { println(it) },
                { println("Failed with $it") }
            )
}

/**
 * Create failing Unis
 */
fun creatingFailingUnis() {
    val exception: Uni<Int> = Uni.createFrom().failure(Exception("Invalid Int"))
    val exception2 = Uni.createFrom().failure<Int> { Exception("Exception using Supplier") }
}

/**
 * Create empty Unis
 */
fun creatingEmptyUni() {
    val nullItem = Uni.createFrom().nullItem<Int>()
}

/**
 * Create Uni from an Emitter.
 */
fun creatingUniFromEmitter() {
    Uni.createFrom()
        .emitter<String> {
            it.complete("result")
        }
}
