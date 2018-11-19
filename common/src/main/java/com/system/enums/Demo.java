package com.system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author weiyu
 * @version V1.0
 * @since 2018-10-04
 */
@Getter
@AllArgsConstructor
public enum Demo {

    SUCCESS("success","成功");

    private String code;
    private String msg;

    public static Demo getCode(String code){
        if (code != null){
            Demo[] $enums = Demo.values();
            for (Demo $enum : $enums){
                if ($enum.getCode().equals(code)){
                    return $enum;
                }
            }
        }
        return null;

    }
}
