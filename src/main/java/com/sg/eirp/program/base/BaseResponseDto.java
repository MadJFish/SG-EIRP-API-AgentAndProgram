package com.sg.eirp.program.base;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponseDto<T> {
    private boolean status;
    private String errorCode;
    private String errorMsg;
    private T body;
}
