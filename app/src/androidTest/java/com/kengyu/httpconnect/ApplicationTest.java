package com.kengyu.httpconnect;

import android.support.test.runner.AndroidJUnit4;

import com.kengyu.httpconnect.store.LoginAvtivity;
import com.kengyu.httpconnect.store.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by StevenLiao on 2017/8/22.
 */
@RunWith(AndroidJUnit4.class)

public class ApplicationTest {
    @Rule public final ActivityRule<LoginAvtivity> main = new ActivityRule<>(LoginAvtivity.class);

    @Test
    public void startTest() {

    }
}
