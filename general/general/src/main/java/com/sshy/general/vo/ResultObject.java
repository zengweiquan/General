package com.sshy.general.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 结果封装返回
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultObject implements Serializable {
    private static final long serialVersionUID = 8724301444798961689L;

    private String message;
    private Object data;
    private Integer state;

    /**
     * 失败
     * @param err
     */
    public ResultObject(Throwable err){
            this.state=0;
            this.message=err.getMessage();
    }
    /**
     *成功
     * @param data
     */
    public ResultObject(Object data){
            this.state=1;
            this.data=data;
    }
    public ResultObject(Integer state,String message){
        this.state=1;
        this.message=message;
    }

}
