package com.woniu.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page {
    private Integer pageIndex;
    private Integer pageCount;
    private Integer dataCount;
}
