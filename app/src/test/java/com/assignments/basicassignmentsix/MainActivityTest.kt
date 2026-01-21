package com.assignments.basicassignmentsix

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.google.android.material.button.MaterialButton
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.intArrayOf


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [33])
class MainActivityTest {

    private lateinit var activity: MainActivity
    private lateinit var mainLayout: LinearLayout
    private lateinit var headerLayout: RelativeLayout
    private lateinit var buttonLayout: RelativeLayout

    private lateinit var button1: MaterialButton
    private lateinit var button2: MaterialButton
    private lateinit var button3: MaterialButton
    private lateinit var button4: MaterialButton
    private lateinit var button5: MaterialButton
    private lateinit var button6: MaterialButton

    @Before
    fun setup() {
        activity = Robolectric.buildActivity(MainActivity::class.java)
            .create()
            .start()
            .resume()
            .get()

        mainLayout = activity.findViewById(R.id.main_layout)
        headerLayout = activity.findViewById(R.id.header_layout)
        buttonLayout = activity.findViewById(R.id.button_layout)

        button1 = activity.findViewById(R.id.button1)
        button2 = activity.findViewById(R.id.button2)
        button3 = activity.findViewById(R.id.button3)
        button4 = activity.findViewById(R.id.button4)
        button5 = activity.findViewById(R.id.button5)
        button6 = activity.findViewById(R.id.button6)
    }

    // ---------- Activity ----------

    @Test
    fun activity_should_not_be_null() {
        assertNotNull(activity)
    }

    // ---------- Main Layout ----------

    @Test
    fun main_layout_should_be_linear_layout() {
        assertEquals(LinearLayout::class.java, mainLayout.javaClass)
    }

    @Test
    fun main_layout_should_be_vertical() {
        assertEquals(LinearLayout.VERTICAL, mainLayout.orientation)
    }

    @Test
    fun main_layout_should_have_padding_16dp() {
        val expectedPadding =
            (16 * activity.resources.displayMetrics.density).toInt()
        assertEquals(expectedPadding, mainLayout.paddingLeft)
    }

    // ---------- Header Layout ----------

    @Test
    fun header_should_contain_avatar_and_burger_icon() {
        val avatar = activity.findViewById<ImageView>(R.id.avatar_image)
        val burger = activity.findViewById<ImageView>(R.id.burger_icon)

        assertNotNull(avatar)
        assertNotNull(burger)
    }

    // ---------- Button Layout ----------

    @Test
    fun button_layout_should_exist() {
        assertNotNull(buttonLayout)
    }

    @Test
    fun should_have_six_buttons() {
        assertEquals(6, buttonLayout.childCount)
    }

    // ---------- Button Text ----------

    @Test
    fun buttons_should_have_correct_text() {
        assertEquals("1", button1.text.toString())
        assertEquals("2", button2.text.toString())
        assertEquals("3", button3.text.toString())
        assertEquals("4", button4.text.toString())
        assertEquals("5", button5.text.toString())
        assertEquals("6", button6.text.toString())
    }

    // ---------- Button Visibility ----------

    @Test
    fun all_buttons_should_be_visible() {
        listOf(button1, button2, button3, button4, button5, button6).forEach {
            assertEquals(View.VISIBLE, it.visibility)
        }
    }

    // ---------- Button Enabled ----------

    @Test
    fun all_buttons_should_be_enabled() {
        listOf(button1, button2, button3, button4, button5, button6).forEach {
            assertTrue(it.isEnabled)
        }
    }

    // ---------- Button Clickable ----------

    @Test
    fun all_buttons_should_be_clickable() {
        listOf(button1, button2, button3, button4, button5, button6).forEach {
            assertTrue(it.isClickable)
        }
    }

    // ---------- Button Text Color ----------

    @Test
    fun buttons_should_have_black_text_color() {
        val expectedColor = activity.getColor(android.R.color.black)

        listOf(button1, button2, button3, button4, button5, button6).forEach {
            assertEquals(expectedColor, it.currentTextColor)
        }
    }

    // ---------- Button Background Tint ----------

    @Test
    fun buttons_should_have_correct_background_tint() {
        val expectedColor = activity.getColor(R.color.background)

        listOf(button1, button2, button3, button4, button5, button6).forEach {
            val tintList = it.backgroundTintList
            assertNotNull(tintList)
            assertEquals(expectedColor, tintList!!.defaultColor)
        }
    }

    // ---------- Button Padding ----------

    @Test
    fun buttons_should_have_zero_padding() {
        assertEquals(0, button1.paddingLeft)
        assertEquals(0, button1.paddingTop)
        assertEquals(0, button1.paddingRight)
        assertEquals(0, button1.paddingBottom)
    }

    // ---------- Button Corner Radius ----------

    @Test
    fun buttons_should_have_zero_corner_radius() {
        assertEquals(0, button1.cornerRadius)
    }

    // ---------- Button Size ----------

    @Test
    fun buttons_should_be_100dp_square() {
        val expectedSize =
            (100 * activity.resources.displayMetrics.density).toInt()

        assertEquals(expectedSize, button1.layoutParams.width)
        assertEquals(expectedSize, button1.layoutParams.height)
    }

    // ---------- Button Layout Rules ----------

    @Test
    fun button5_should_be_below_button2() {
        val params = button5.layoutParams as RelativeLayout.LayoutParams
        assertEquals(R.id.button2, params.getRule(RelativeLayout.BELOW))
    }

    // ---------- Layout Order ----------

    @Test
    fun header_should_appear_before_button_layout() {
        assertTrue(
            mainLayout.indexOfChild(headerLayout) <
                    mainLayout.indexOfChild(buttonLayout)
        )
    }
}
