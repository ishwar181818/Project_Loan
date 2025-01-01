package com.cjc.count;

import lombok.Data;

@Data
public class EnquiryCountDto {
	
	
	private Long pendingCount;
    private Long approvedCount;
    private Long rejectedCount;

    // Constructor to match the query's result with Long data types
    public EnquiryCountDto(Long pendingCount, Long approvedCount, Long rejectedCount) {
        this.pendingCount = pendingCount;
        this.approvedCount = approvedCount;
        this.rejectedCount = rejectedCount;
    }

}
