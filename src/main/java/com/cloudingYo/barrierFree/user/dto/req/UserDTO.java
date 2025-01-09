package com.cloudingYo.barrierFree.user.dto.req;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
        private String username;
        private String email;
        private String password;
}
