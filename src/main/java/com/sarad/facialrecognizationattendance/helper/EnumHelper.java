package com.sarad.facialrecognizationattendance.helper;

import com.sarad.facialrecognizationattendance.entity.Role;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class EnumHelper {

    public Role roleEnumHelper(String role){
        if(Objects.equals(role, "ADMIN")){
            return Role.ADMIN;
        } else if (Objects.equals(role, "EMPLOYEE")) {
            return Role.EMPLOYEE;
        }else{
            return null;
        }
    }

}
