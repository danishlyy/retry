package com.example.retry.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 李勇勇
 * @version 1.0
 * @date 2021-06-22 14:53
 * @JsonInclude(Include.NON_EMPTY) 当属性的值为空（null或者""）时，不进行序列化，可以减少数据传输
 * @JsonIgnoreProperties({"name"}) 序列化、反序列化忽略的属性，多个时用“,”隔开
 */
@Getter
@Setter
@ToString
@Builder
@JsonInclude(Include.NON_EMPTY)
public class DemoResponseDTO implements Serializable {

    private static final long serialVersionUID = 579477819023651766L;

    // 序列化、反序列化时，属性的名称
    @JsonProperty("userName")
    private String name;

    private String age;

    //序列化、反序列化忽略属性
    @JsonIgnore
    private String address;

    // 为反序列化期间要接受的属性定义一个或多个替代名称，可以与@JsonProperty一起使用
    @JsonAlias({"pass_word", "passWord"})
    @JsonProperty("pwd")
    private String password;

    //序列化、反序列化时，格式化时间
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;

}
