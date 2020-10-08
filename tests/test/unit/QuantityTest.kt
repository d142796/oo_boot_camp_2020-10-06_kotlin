/*
 * Copyright (c) 2020 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package unit

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import quantity.Unit.Companion.cups
import quantity.Unit.Companion.gallons
import quantity.Unit.Companion.tablespoons
import quantity.Unit.Companion.teaspoons

// Ensures Quantity operates correctly
internal class QuantityTest {

    @Test internal fun `equality of like units`() {
        assertEquals(8.tablespoons, 8.tablespoons)
        assertNotEquals(8.tablespoons, 6.tablespoons)
        assertNotEquals(8.tablespoons, Any())
        assertNotEquals(8.tablespoons, null)
    }

    @Test internal fun `equality of different units`() {
        assertEquals(8.tablespoons, 0.5.cups)
        assertEquals(768.teaspoons, 1.gallons)
        assertNotEquals(8.tablespoons, 8.cups)
    }

    @Test fun `Quantity in hash set`() {
        assert(hashSetOf(8.tablespoons).contains(8.tablespoons))
        assert(hashSetOf(8.tablespoons).contains(0.5.cups))
        assertEquals(1, hashSetOf(8.tablespoons, 8.tablespoons).size)
    }

    @Test fun hash() {
        assertEquals(8.tablespoons.hashCode(), 8.tablespoons.hashCode())
        assertEquals(8.tablespoons.hashCode(), 0.5.cups.hashCode())
    }
}