package com.bilgeadam.service;

import com.bilgeadam.dto.response.Sehir;
import com.bilgeadam.dto.response.TahminResponeDto;
import com.bilgeadam.manager.ISehirManager;
import com.bilgeadam.mapper.ITahminMapper;
import com.bilgeadam.repository.TahminRepository;
import com.bilgeadam.repository.entity.Tahmin;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class TahminService extends ServiceManager<Tahmin,Long> {

    private final TahminRepository tahminRepository;

    private final ISehirManager sehirManager;

    public TahminService(TahminRepository tahminRepository, ISehirManager sehirManager) {
        super(tahminRepository);
        this.tahminRepository = tahminRepository;
        this.sehirManager = sehirManager;
    }

    public TahminResponeDto olustur(Long userId) {
        Sehir sehir=sehirManager.sehirGetir(1L).getBody();
        System.out.println(sehir);
        Tahmin tahmin=Tahmin.builder().userId(userId).dogruCevap(sehir.getIsim()).blur(sehir.getBlur()).build();
        save(tahmin);
        return ITahminMapper.INSTANCE.toTahminResponeDto(tahmin);
    }
}
