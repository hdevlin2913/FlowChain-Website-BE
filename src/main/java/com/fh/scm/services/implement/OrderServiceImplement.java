package com.fh.scm.services.implement;

import com.fh.scm.pojo.Order;
import com.fh.scm.repository.OrderRepository;
import com.fh.scm.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class OrderServiceImplement implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order get(UUID id) {
        return this.orderRepository.get(id);
    }

    @Override
    public void insert(Order order) {
        this.orderRepository.insert(order);
    }

    @Override
    public void update(Order order) {
        this.orderRepository.update(order);
    }

    @Override
    public void delete(UUID id) {
        this.orderRepository.delete(id);
    }

    @Override
    public void softDelete(UUID id) {
        this.orderRepository.softDelete(id);
    }

    @Override
    public void insertOrUpdate(Order order) {
        this.orderRepository.insertOrUpdate(order);
    }

    @Override
    public Long count() {
        return this.orderRepository.count();
    }

    @Override
    public Boolean exists(UUID id) {
        return this.orderRepository.exists(id);
    }

    @Override
    public List<Order> getAll(Map<String, String> params) {
        return this.orderRepository.getAll(params);
    }
}