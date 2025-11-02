package com.Shoe.service.Impl;

import com.Shoe.converter.BrandDTOConverter;
import com.Shoe.dto.request.adminRequest.product.BrandCreateRequest;
import com.Shoe.model.product.Brand;
import com.Shoe.repository.product.BrandRepository;
import com.Shoe.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;
    @Override
    public ResponseEntity<?> addBrand(BrandCreateRequest brand) {
        Optional<Brand> brandFound=brandRepository.findByName(brand.getBrandName());
        if(brandFound.isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Brand with name "+brand.getBrandName()+" already exists");
        }else {
            Brand newBrand=new Brand();
            BrandDTOConverter.convertToEntity(brand);
            brandRepository.save(newBrand);
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> updateBrand(BrandCreateRequest brand) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteBrand(int id) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAllBrand() {
       List<Brand> brandList=brandRepository.findAll();
       return ResponseEntity.ok().body(brandList);
    }
}
