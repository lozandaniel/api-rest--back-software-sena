package com.example.backproyectswdistriquesos.services;

import com.example.backproyectswdistriquesos.models.Provider;
import com.example.backproyectswdistriquesos.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderService {
    @Autowired
    public ProviderRepository providerRepository;

    public Long getLengthProviders(){
        return providerRepository.count();
    }

    public List<Provider> getProviders(){
        return providerRepository.findAll();
    }

    public Provider createProvider (Provider provider) {
        return providerRepository.save(provider);
    }

    public Provider updateProvider (Long id, Provider provider) {
        return providerRepository
                .findById(id)
                .map(provider1 -> {
            if (provider.getName() != null) {
                provider1.setName(provider.getName());
            }
            if (provider.getIdentification() != null) {
                provider1.setIdentification(provider.getIdentification());
            }
            if (provider.getPhone() != null) {
                provider1.setPhone(provider.getPhone());
            }
            if (provider.getDirection() != null) {
                provider1.setDirection(provider.getDirection());
            }
            if(provider.getCity() != null){
                provider1.setCity(provider.getCity());
            }
            return providerRepository.save(provider1);
        }).orElseThrow(() -> new RuntimeException("Proveedor no se encuentra"));
    }

    public void deleteProvider(Long id) {
        providerRepository.deleteById(id);
    }


}
