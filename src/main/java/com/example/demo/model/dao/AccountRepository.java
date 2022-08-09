package com.example.demo.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.entity.Account;

public interface AccountRepository extends CrudRepository<Account, Long>  {

}
