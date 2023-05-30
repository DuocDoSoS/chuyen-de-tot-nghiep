package com.example.silver.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageModel {
    private Integer pageSize;
    private Integer pageNumber;
    private Long totalElement;
    private Integer totalPage;
}
