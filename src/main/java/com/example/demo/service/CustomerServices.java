package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.ServiceResult.Status;

@Service
public class CustomerServices {

    @Autowired
    CustomerRepository customerRepo;

    private static final Logger logger = LoggerFactory.getLogger(CustomerServices.class);

    public ServiceResult findAll() {
        ServiceResult result = new ServiceResult();
        result.setData(customerRepo.findAll());
        return result;
    }

    public ServiceResult findById(int id) {
        ServiceResult result = new ServiceResult();
        Customer customer = customerRepo.findById(id).orElse(null);
        if (customer == null) {
            result.setMessage("Customer not found");
            logger.info("Customer not found");
            logger.info("debug enabled: {}", logger.isDebugEnabled());
            logger.trace("trace");
            logger.debug("debug");
            logger.info("info");
            logger.warn("warn");
            logger.error("error");
        }
        result.setData(customer);
        return result;
    }

    public ServiceResult create(Customer customer) {
        ServiceResult result = new ServiceResult();
        result.setData(customerRepo.save(customer));
        return result;
    }

    public ServiceResult update(Customer customer) {
        ServiceResult result = new ServiceResult();

        if (!customerRepo.findById(customer.getId()).isPresent()) {
            result.setStatus(Status.FAILED);
            result.setMessage("Customer not found");
        } else {
            result.setData(customerRepo.save(customer));
        }
        return result;
    }

    public ServiceResult delete(int id) {
        ServiceResult result = new ServiceResult();

        Customer customer = customerRepo.findById(id).orElse(null);
        if (customer == null) {
            result.setStatus(Status.FAILED);
            result.setMessage("Customer not found");
            logger.info("customer not found");
        } else {
            customerRepo.delete(customer);
            result.setMessage("success");
            logger.info("delete success");
        }
        return result;
    }
}
