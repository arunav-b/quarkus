package me.arunav.mutiny

import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni
import java.time.Duration

val multiItems: Multi<Int> = Multi.createFrom()
    .iterable(listOf(1, 2, 3, 4, 5, 6))

fun invoke(): Uni<Int> {
    return Uni.createFrom().item(1)
        .onItem().invoke { i -> println("Invoked on the stream $i") }
}

fun invokeMultiEvents(): Multi<Int> {
    return multiItems
        .onSubscription().invoke { _ -> println("Subscription notification from Upstream -> Downstream") }
        .onItem().invoke { i ->
            println("Received item from upstream: $i")
            if (i > 7)
                throw Exception("Item is greater than 7")
        }
        .onFailure().invoke { ex -> println("Failed with exception $ex") }
        .onCompletion().invoke { println("Upstream notifying downstream about completion of stream") }
        .onCancellation().invoke { println("Cancelled by Downstream") }
        .onRequest().invoke { _ -> println("Downstream requested for items") }
}

fun call(): Multi<Int> {
    return multiItems
        .onItem().call { i ->
            Uni.createFrom()
                .item(i).onItem().delayIt().by(Duration.ofSeconds(1))
        }
        .onItem().invoke { i -> println(i) }
}

fun main() {
    invoke()
        .subscribe().with { println("Subscribing on the stream $it") }

    println("--- Subscribing on Multi Events while using invoke() ---")
    invokeMultiEvents()
        .subscribe().with { println("Subscribing on invokeMultiEvents") }

    println("--- Subscribing on Multi Events while using call() ---")
    call()
        .onItem().invoke { i -> println(i) }
//        .subscribe()
        .onCompletion()
        .invoke { println("Completed") }
//        .subscribe()
//        .with(
//            { i -> println(i) },
//            { f -> throw Exception("Failed with $f") },
//            { println("Completed") }
//        )
}
