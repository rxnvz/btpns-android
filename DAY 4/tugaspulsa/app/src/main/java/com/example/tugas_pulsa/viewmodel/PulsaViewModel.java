package com.example.tugas_pulsa.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tugas_pulsa.model.ProductResponse;
import com.example.tugas_pulsa.model.Pulsa;
import com.example.tugas_pulsa.model.PulsaResponse;
import com.example.tugas_pulsa.network.PulsaRepository;

public class PulsaViewModel extends ViewModel {
    private MutableLiveData<ProductResponse> productLiveData;
    private MutableLiveData<PulsaResponse> pulsaLiveData;
    private PulsaRepository pulRepo;

    public void init() {
        if (productLiveData != null) {
            return;
        }
        pulRepo = PulsaRepository.getInstance();
        productLiveData = pulRepo.getAll();
    }
    public LiveData<ProductResponse> getProductRepo() {
        return productLiveData;
    }

    public void refresh() {
        if (productLiveData != null) {
            productLiveData = pulRepo.getAll();
            return;
        }
        pulRepo = PulsaRepository.getInstance();
        productLiveData = pulRepo.getAll();
    }

    public LiveData<PulsaResponse> postPulsa(Pulsa payload) {
        if (pulsaLiveData == null) {
            pulRepo = PulsaRepository.getInstance();
        }
        pulsaLiveData = pulRepo.beliPulsa(payload);
        return pulsaLiveData;
    }
}
