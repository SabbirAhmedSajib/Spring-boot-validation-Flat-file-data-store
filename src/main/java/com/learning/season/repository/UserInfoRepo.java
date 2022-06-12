package com.learning.season.repository;

import com.learning.season.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepo  extends JpaRepository<UserInfoEntity, Integer>{


}
