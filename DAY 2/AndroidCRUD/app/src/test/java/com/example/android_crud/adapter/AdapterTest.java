package com.example.android_crud.adapter;

import android.app.Application;
import android.content.Context;
import android.view.View;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AdapterTest {
    private final int count = 11;

    @Mock
    NasabahAdapter adapter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Mockito.when(adapter.getItemCount()).thenReturn(count);
    }

    @Test
    public void getItemCountTest() {
        int expected = 11;
        Assert.assertEquals(expected, adapter.getItemCount());
    }
}
