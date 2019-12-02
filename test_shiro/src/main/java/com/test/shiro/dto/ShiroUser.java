package com.test.shiro.dto;

import lombok.Setter;
import lombok.Getter;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ShiroUser {

    private Long id;

    private String loginName;

    private List<String> permissionList;

    private Map<Long,String> roleMap;

}
