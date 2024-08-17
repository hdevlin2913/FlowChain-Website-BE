package com.fh.scm.services.implement;

import com.fh.scm.pojo.Invoice;
import com.fh.scm.repository.InvoiceRepository;
import com.fh.scm.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class InvoiceServiceImplement implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public Invoice get(UUID id) {
        return this.invoiceRepository.get(id);
    }

    @Override
    public void insert(Invoice invoice) {
        this.invoiceRepository.insert(invoice);
    }

    @Override
    public void update(Invoice invoice) {
        this.invoiceRepository.update(invoice);
    }

    @Override
    public void delete(UUID id) {
        this.invoiceRepository.delete(id);
    }

    @Override
    public void softDelete(UUID id) {
        this.invoiceRepository.softDelete(id);
    }

    @Override
    public void insertOrUpdate(Invoice invoice) {
        this.invoiceRepository.insertOrUpdate(invoice);
    }

    @Override
    public Long count() {
        return this.invoiceRepository.count();
    }

    @Override
    public Boolean exists(UUID id) {
        return this.invoiceRepository.exists(id);
    }

    @Override
    public List<Invoice> getAll(Map<String, String> params) {
        return this.invoiceRepository.getAll(params);
    }
}