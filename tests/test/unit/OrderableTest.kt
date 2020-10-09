/*
 * Copyright (c) 2020 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George
 */

package unit

import order.bestOrNull
import org.junit.jupiter.api.Test
import probability.Chance
import rectangle.Rectangle
import kotlin.test.assertEquals
import kotlin.test.assertNull

// Ensures Orderable operates correctly
internal class OrderableTest {

    @Test internal fun `rectangle with largest area`() {
        assertEquals(36.0, listOf(Rectangle(2, 3), Rectangle.square(6.0), Rectangle(5, 7.0)).bestOrNull()?.area())
        assertNull(emptyList<Rectangle>().bestOrNull())
    }

    @Test internal fun `least likely chance`() {
        assertEquals(Chance(0), listOf(Chance(0.1), Chance(0.7), Chance(0), Chance(0.9)).bestOrNull())
        assertNull(emptyList<Chance>().bestOrNull())
    }
}