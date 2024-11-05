package com.sugianto.nutech.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagingResponse<T> {

    private Integer offset;

    private Integer limit;

    private T records;

}
