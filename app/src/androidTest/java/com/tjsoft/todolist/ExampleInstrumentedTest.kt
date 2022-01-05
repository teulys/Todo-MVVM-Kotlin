package com.tjsoft.todolist

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tjsoft.todolist.view.MainActivity
import com.tjsoft.todolist.view.adapter.TodoViewHolder
import org.hamcrest.EasyMock2Matchers.equalTo
import org.hamcrest.Matcher
import org.hamcrest.Matchers.containsString
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.tjsoft.todolist", appContext.packageName)
    }

    @get: Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_isListVisible_onAppLauch(){
        onView(withId(R.id.rvTodoList)).check(matches(isDisplayed()))
    }

    @Test
    fun insert_new_task() {
        val task = "Automatically task test"
        //Find EditTest and write a task
        onView(withId(R.id.etTodoName))
            .perform(typeText(task))

        //Close Keyboard
        //onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
        pressBack()

        //Click add button
        onView(withId(R.id.ivButtomAdd))
            .perform(click())

        //Find new Item added in RecycleView
        onView(withId(R.id.rvTodoList))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))

        //Check if item have the text
        onView(withText(task))
            .check(matches(isDisplayed()))
    }
}