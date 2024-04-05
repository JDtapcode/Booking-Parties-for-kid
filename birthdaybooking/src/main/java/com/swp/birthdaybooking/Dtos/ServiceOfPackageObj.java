package com.swp.birthdaybooking.Dtos;

import com.swp.birthdaybooking.entities.Package;
import com.swp.birthdaybooking.entities.ServiceBirthday;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceOfPackageObj {
    private int serviceOfPackageId;
    private ServiceBirthday serviceBirthday;
    private Package servicePackage;
}
