package com.fh.scm.services.implement;

import com.fh.scm.pojo.SupplierCosting;
import com.fh.scm.repository.SupplierCostingRepository;
import com.fh.scm.services.SupplierCostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class SupplierCostingServiceImplement implements SupplierCostingService {

    @Autowired
    private SupplierCostingRepository supplierCostingRepository;

    @Override
    public SupplierCosting get(UUID id) {
        return this.supplierCostingRepository.get(id);
    }

    @Override
    public void insert(SupplierCosting supplierCosting) {
        this.supplierCostingRepository.insert(supplierCosting);
    }

    @Override
    public void update(SupplierCosting supplierCosting) {
        this.supplierCostingRepository.update(supplierCosting);
    }

    @Override
    public void delete(UUID id) {
        this.supplierCostingRepository.delete(id);
    }

    @Override
    public void softDelete(UUID id) {
        this.supplierCostingRepository.softDelete(id);
    }

    @Override
    public void insertOrUpdate(SupplierCosting supplierCosting) {
        this.supplierCostingRepository.insertOrUpdate(supplierCosting);
    }

    @Override
    public Long count() {
        return this.supplierCostingRepository.count();
    }

    @Override
    public Boolean exists(UUID id) {
        return this.supplierCostingRepository.exists(id);
    }

    @Override
    public List<SupplierCosting> getAll(Map<String, String> params) {
        return this.supplierCostingRepository.getAll(params);
    }
}