package com.pos.pos.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pos.pos.model.UserModel;

@Repository
public interface UserRepo extends JpaRepository<UserModel, String> {

}
