package com.adm.count;

import lombok.Data;

@Data
public class EmployeeCountDto {

    private Long OECount;
    private Long CRMCount;
    private Long CMCount;
    private Long AHCount;
    private Long AdminCount;
    private Long UserCount;

    // Constructor to match the query's result with Long data types
    public EmployeeCountDto(Long OECount, Long CRMCount, Long CMCount, Long AHCount, Long AdminCount, Long UserCount) {
        this.OECount = OECount;
        this.CRMCount = CRMCount;
        this.CMCount = CMCount;
        this.AHCount = AHCount;
        this.AdminCount = AdminCount;
        this.UserCount = UserCount;
    }
}
