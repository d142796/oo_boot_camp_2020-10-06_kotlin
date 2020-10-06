/*
 * Copyright (c) 2020 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package unit

import org.junit.jupiter.api.Assertions.*
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
    }

    @Test fun not() {
        assertEquals(unlikely, likely.not())
        assertEquals(likely, likely.not().not())
        assertEquals(likely, !!likely)
        assertEquals(impossible, certain.not())
        assertEquals(equallyLikely, equallyLikely.not())
    }
}