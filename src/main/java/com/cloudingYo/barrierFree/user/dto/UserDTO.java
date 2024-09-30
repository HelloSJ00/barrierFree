package com.cloudingYo.barrierFree.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

        private String username;
        private String email;
        private String password;

        public UserDTO(String username, String email, String password) {
            this.username = username;
            this.email = email;
            this.password = password;
        }
}
