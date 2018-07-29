package com.rsherry.bakingapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.not;

@RunWith(AndroidJUnit4.class)
public class IntentTesting {
    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void confirmIntentWithRecipeDataSent() {
        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.viewPager)).perform(swipeLeft());
        onView(withId(R.id.stepsListRecyclerView)).perform(RecyclerViewActions
                .actionOnItemAtPosition(0, RecyclerViewActionClickChild.clickChildViewWithId(R.id.playStepVideo)));
        intended(hasComponent(VideoPlaybackActivity.class.getName()));
    }

    @Test
    public void confirmIntentResult() {

        //Setup intent and result
        Intent resultData = new Intent();
        String url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd9a6_2-mix-sugar-crackers-creampie/2-mix-sugar-crackers-creampie.mp4" ;
        resultData.putExtra("videoUrl", url);
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);

        // provide stub
        intending(toPackage("com.android.bakingapp")).respondWith(result);

        //verify action used to launch activity that produces stub result

        onView(withId(R.id.recipeRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.viewPager)).perform(swipeLeft());
        onView(withId(R.id.stepsListRecyclerView)).perform(RecyclerViewActions
                .actionOnItemAtPosition(0, RecyclerViewActionClickChild.clickChildViewWithId(R.id.playStepVideo)));

    }
}

class RecyclerViewActionClickChild {

    public static ViewAction clickChildViewWithId(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Click on child button in RecyclerView.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(id);
                v.performClick();
            }
        };
    }

}