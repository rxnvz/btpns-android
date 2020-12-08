package com.example.android_crud.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class NasabahTest {
    private final Long id=1l;
    private final String nama="Testing Nama";
    private final String email="Testing Email";
    private final String alamat="Testing Alamat";

    @Mock
    Nasabah nasabah;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Mockito.when(nasabah.getId()).thenReturn(id);
        Mockito.when(nasabah.getNama()).thenReturn(nama);
        Mockito.when(nasabah.getEmail()).thenReturn(email);
        Mockito.when(nasabah.getAlamat()).thenReturn(alamat);
    }

    @Test
    public void testID() {
        Mockito.when(nasabah.getId()).thenReturn(id);
        Assert.assertEquals(new Long(1l),nasabah.getId());
    }

    @Test
    public void testNasabahNama(){
        Mockito.when(nasabah.getNama()).thenReturn(nama);
        Assert.assertEquals("Testing Nama",nasabah.getNama());
    }

    @Test
    public void testNasabahEmail(){
        Mockito.when(nasabah.getEmail()).thenReturn(email);
        Assert.assertEquals("Testing Email",nasabah.getEmail());
    }

    @Test
    public void testNasabahAlamat(){
        Mockito.when(nasabah.getAlamat()).thenReturn(alamat);
        Assert.assertEquals("Testing Alamat",nasabah.getAlamat());
    }

    @Test
    public void testIDIncorrect() {
        Mockito.when(nasabah.getId()).thenReturn(id);
        Assert.assertNotEquals(new Long(2l), nasabah.getId());
    }

    @Test
    public void testNewsNamaIncorrect(){
        Mockito.when(nasabah.getNama()).thenReturn(nama);
        Assert.assertNotEquals("Nama",nasabah.getNama());
    }

    @Test
    public void testNasabahEmailIncorrect(){
        Mockito.when(nasabah.getEmail()).thenReturn(email);
        Assert.assertNotEquals("Email",nasabah.getEmail());
    }

    @Test
    public void testNasabahAlamaIncorrectt(){
        Mockito.when(nasabah.getAlamat()).thenReturn(alamat);
        Assert.assertNotEquals("Alamat",nasabah.getAlamat());
    }
}
