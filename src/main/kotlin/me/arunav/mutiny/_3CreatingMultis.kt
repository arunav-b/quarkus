package me.arunav.mutiny

import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.subscription.Cancellable
import java.time.Duration
import java.util.stream.IntStream

// Create a Multi from a list of Ints
val multi = Multi.createFrom().items(1, 2, 3, 4)

// Subscribing to a Multi
val cancellable: Cancellable =
    multi
        .subscribe()
        .with(
            { println("Item $it") }, // Subscribing each item
            { println("Failed with $it") }, // On Failure
            { println("Completed") } // On Completion
        )

// Creating Multi from an Intstream
val items1 = Multi.createFrom()
    .items { IntStream.range(1, 10).boxed() }

// Creating Multi from a collection
val items2 = Multi.createFrom()
    .iterable(listOf(1, 2, 3, 4, 5, 6))

// Creating failing Multis
val exception = Multi.createFrom().failure<Int>(Exception("Some Exception"))
val exception2 = Multi.createFrom().failure<Int> { Exception("Another Exception") }

// Creating empty Multis
val empty = Multi.createFrom().empty<Int>()

// Creating Multi from emitter
val items: Multi<Int> = Multi.createFrom()
    .emitter {
        it.emit(1)
        it.emit(2)
        it.emit(3)
        it.complete()
    }

// Creating Multi from ticks
val ticks = Multi.createFrom()
    .ticks().every(Duration.ofMillis(100))
