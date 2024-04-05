package com.swp.birthdaybooking.Dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteUserRequest {
    private Integer accountId;
    private Boolean status;
}
