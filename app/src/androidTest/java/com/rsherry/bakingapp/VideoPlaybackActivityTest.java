package com.rsherry.bakingapp;

import android.support.test.espresso.Espresso;
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
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class VideoPlaybackActivityTest {
    @Rule public ActivityTestRule<MainActivity> mVideoPlaybackActivityRule
            = new ActivityTestRule<>(MainActivity.class);

//    @Before
//    public void showFragment(){
//        mVideoPlaybackActivityRule.getActivity()
//                .getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.layout.fragment_steps, new StepsFragment())
//                .commit();
//    }

    @Test
    public void clickRecipeTest() {
        //1. Find View
        //2. Perform action
        for(int i = 0; i < 4; i++) {
        onView(ViewMatchers.withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(i, click()));

                pressBack();

            }
//                .onView(withId(R.id.playStepVideo)).perform(click())
//                //3. Check if result is what is expected
//                .check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        //Verify View Text
        onView(withId(R.id.viewPager)).perform(swipeLeft());

        
    }
}
