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
import quantity.Unit.Companion.chains
import quantity.Unit.Companion.cups
import quantity.Unit.Companion.feet
import quantity.Unit.Companion.furlongs
import quantity.Unit.Companion.gallons
import quantity.Unit.Companion.inches
import quantity.Unit.Companion.miles
import quantity.Unit.Companion.ounces
import quantity.Unit.Companion.pints
import quantity.Unit.Companion.quarts
import quantity.Unit.Companion.tablespoons
import quantity.Unit.Companion.teaspoons
import quantity.Unit.Companion.yards

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
        assertEquals(1.miles, (12 * 5280).inches)
    }

    @Test fun `Quantity in hash set`() {
        assert(hashSetOf(8.tablespoons).contains(8.tablespoons))
        assert(hashSetOf(8.tablespoons).contains(0.5.cups))
        assertEquals(1, hashSetOf(8.tablespoons, 8.tablespoons).size)
    }

    @Test fun hash() {
        assertEquals(8.tablespoons.hashCode(), 8.tablespoons.hashCode())
        assertEquals(8.tablespoons.hashCode(), 0.5.cups.hashCode())
        assertEquals(18.inches.hashCode(), 0.5.yards.hashCode())
    }

    @Test fun arithmetic() {
        assertEquals(0.5.quarts, +6.tablespoons + 13.ounces)
        assertEquals((-6).tablespoons, -6.tablespoons)
        assertEquals((-0.5).pints, 10.tablespoons - 13.ounces)
        assertEquals(-4.feet, 24.inches - 2.yards)
        assertEquals(8.chains, 1.furlongs - 44.yards)
    }

    @Test fun `cross metric types`() {
        assertNotEquals(1.inches, 1.teaspoons)
        assertNotEquals(4.ounces, 2.feet)
    }

    @Test fun `incompatible units`() {
        assertThrows<IllegalArgumentException> { 3.yards - 4.tablespoons}
    }
}