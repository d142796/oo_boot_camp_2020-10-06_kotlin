/*
 * Copyright (c) 2020 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package unit

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import probability.Chance

// Ensures Chance operates correctly
internal class ChanceTest {
    private val certain = Chance(1)
    private val likely = Chance(0.75)
    private val equallyLikely = Chance(0.5)
    private val unlikely = Chance(0.25)
    private val impossible = Chance(0)

    @Test internal fun equals() {
        assertEquals(Chance(0.75), likely)
        assertNotEquals(likely, Chance(0.25))
        assertNotEquals(likely, Any())
        assertNotEquals(likely, null)
    }

    @Test fun `Chance in hash set`() {
        assert(hashSetOf(likely).contains(Chance(0.75)))
        assertEquals(1, hashSetOf(likely, Chance(0.75)).size)
    }

    @Test fun hash() {
        assertEquals(likely.hashCode(), Chance(0.75).hashCode())
        assertEquals(Chance(0.3).hashCode(), (!!Chance(0.3)).hashCode())
    }

    @Test fun not() {
        assertEquals(unlikely, likely.not())
        assertEquals(likely, likely.not().not())
        assertEquals(likely, !!likely)
        assertEquals(Chance(0.3), !!Chance(0.3))
        assertEquals(impossible, certain.not())
        assertEquals(equallyLikely, equallyLikely.not())
    }

    @Test fun and() {
        assertEquals(unlikely, equallyLikely and equallyLikely)
        assertEquals(Chance(0.1875), likely and unlikely)
        assertEquals(likely and unlikely, unlikely and likely)
        assertEquals(impossible, likely and impossible)
        assertEquals(likely, certain and likely)
    }

    @Test internal fun or() {
        assertEquals(likely, equallyLikely or equallyLikely)
        assertEquals(Chance(0.8125), likely or unlikely)
        assertEquals(likely or unlikely, unlikely or likely)
        assertEquals(likely, likely or impossible)
        assertEquals(certain, certain or likely)
    }

    @Test internal fun `invalid fractions`() {
        assertThrows<java.lang.IllegalArgumentException> { Chance(-0.01) }
        assertThrows<java.lang.IllegalArgumentException> { Chance(1.01) }
    }
}