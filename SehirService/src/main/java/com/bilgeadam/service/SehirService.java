package com.bilgeadam.service;

import com.bilgeadam.dto.request.SehirRequestDto;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.exception.SehirManagerException;
import com.bilgeadam.mapper.ISehirMapper;
import com.bilgeadam.repository.SehirRepository;
import com.bilgeadam.repository.entity.Sehir;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SehirService extends ServiceManager<Sehir,Long> {

private  final SehirRepository sehirRepository;

    public SehirService( SehirRepository sehirRepository) {
        super(sehirRepository);
        this.sehirRepository = sehirRepository;
    }

    public Sehir ekle(SehirRequestDto dto) {
        Sehir sehir=ISehirMapper.INSTANCE.toSehir(dto);
        sehir.setBlur(sehir.getIsim().substring(0,2));
        return  save(sehir);
    }

    public Sehir sehirGetir(Long id) {
        Optional<Sehir> sehir=findById(id);
        if (sehir.isEmpty()){
            throw  new SehirManagerException(ErrorType.SEHIR_NOT_FOUND);
        }
        return sehir.get() ;
    }
}
