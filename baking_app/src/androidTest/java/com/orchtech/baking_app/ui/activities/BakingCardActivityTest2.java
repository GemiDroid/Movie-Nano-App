package com.orchtech.baking_app.ui.activities;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class BakingCardActivityTest2 {

    @Rule
    public ActivityTestRule<BakingCardActivity> mActivityTestRule = new ActivityTestRule<>(BakingCardActivity.class);

    @Test
    public void bakingCardActivityTest2() {
    }

}
