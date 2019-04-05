package com.yzblog.datacenter.web.modules.sys.vo;

import java.io.Serializable;

/**
 * 菜单树，前端菜单展示
 *
 * @author yuzhou
 **/
public class UserRoleVO implements Serializable {

    /**
     * 角色ID
     */
    private String id;
    /**
     * 角色名称
     */
    private String text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
