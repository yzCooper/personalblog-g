package com.yzblog.datacenter.web.modules.sys.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单树，前端菜单展示
 *
 * @author yuzhou
 **/
public class RoleResourceVO implements Serializable {

    private String id;//资源ID
    private String text;//资源名称
    private String iconCls;//用来显示图标的 css class。
    private String checked;//节点是否被选中。
    private String state;//节点状态，'open' 或 'closed'
    private String attributes;
    private String target;

    private List<RoleResourceVO> children = new ArrayList<RoleResourceVO>();

    /**
     * 添加子资源
     *
     * @param menuTreeVO 资源对象
     */
    public void addSubResource(RoleResourceVO menuTreeVO) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(menuTreeVO);
    }

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

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<RoleResourceVO> getChildren() {
        return children;
    }

    public void setChildren(List<RoleResourceVO> children) {
        this.children = children;
    }
}
