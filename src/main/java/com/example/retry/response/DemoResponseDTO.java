package com.example.retry.response;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 李勇勇
 * @version 1.0
 * @date 2021-06-22 14:53
 */
@Getter
@Setter
@ToString
@Builder
public class DemoResponseDTO implements Serializable {

    private static final long serialVersionUID = 579477819023651766L;

    private String name;

    private String age;
}
