package com.rsherry.bakingapp;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.rsherry.bakingapp.MainActivity;
import com.rsherry.bakingapp.R;
import com.rsherry.bakingapp.StepsFragment;
import com.rsherry.bakingapp.data.Recipe;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class MultipleRecyclerViewNavigationActivityTests {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRecyclerViewTests
            = new ActivityTestRule<>(MainActivity.class);

    //Verify text on recipes list
    @Test
    public void testRecipeListText() {
        onView(ViewMatchers.withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, scrollTo()))
                .check(matches(hasDescendant(withText("Yellow Cake"))));
    }

    // Verify all recipes can be clicked and can go back each time to show previous fragment in backstack
    @Test
    public void clickRecipeTest() {
        //1. Find View
        //2. Perform action
        for (int i = 0; i < 4; i++) {
            onView(ViewMatchers.withId(R.id.recipeRecyclerView))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(i, click()));

            pressBack();

        }

        onView(ViewMatchers.withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

    }

    //Verify steps list fragment that is part of viewpager

    @Test
    public void testViewPagerSwipe() {
        onView(ViewMatchers.withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
        onView(withId(R.id.viewPager)).perform(swipeLeft());
    }

}