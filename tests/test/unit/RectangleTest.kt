/*
 * Copyright (c) 2020 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package unit

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import rectangle.Rectangle


// Ensures Rectangle operates correctly
internal class RectangleTest {

    @Test internal fun area() {
        assertEquals(24.0, Rectangle(4.0, 6.0).area())
        assertEquals(24.0, Rectangle(4, 6).area())
        assertEquals(24.0, Rectangle(4, 6).area)
        assertEquals(36.0, Rectangle.square(6.0).area())
    }

    @Test internal fun perimeter() {
        assertEquals(20.0, Rectangle(4.0, 6.0).perimeter())
        assertEquals(20.0, Rectangle(4, 6).perimeter())
        assertEquals(20.0, Rectangle(4, 6).perimeter)
        assertEquals(24.0, Rectangle.square(6).perimeter)
    }

    @Test internal fun `valid dimensions`() {
        assertThrows<IllegalArgumentException> { Rectangle(0.0, 6.0) }
        assertThrows<IllegalArgumentException> { Rectangle(4, 0) }
        assertThrows<IllegalArgumentException> { Rectangle.square(0.0) }
    }
}