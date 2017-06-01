package com.wanari.cbexample.repository;

import com.wanari.cbexample.domain.UserCb;
import org.springframework.data.repository.CrudRepository;

public interface UserRepositoryCb extends CrudRepository<UserCb, String> {
}
