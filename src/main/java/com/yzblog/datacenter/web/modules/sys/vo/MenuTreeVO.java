package com.yzblog.datacenter.web.modules.sys.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 菜单树，前端菜单展示
 *
 * @author yuzhou
 **/
public class MenuTreeVO implements Serializable {

    /**
     * 资源id
     */
    private Long resId;

    /**
     * 资源代码
     */
    private String resCode;

    /**
     * 资源名称
     */
    private String resName;

    /**
     * 资源地址
     */
    private String resUrl;

    /**
     * 资源图标样式
     */
    private String resIcon;

    /**
     * 资源样式，0：树，1：叶子
     */
    private Short resStyle;

    /**
     * 资源状态
     */
    private Short resStatus;

    /**
     * 资源类型
     */
    private Short resType;

    /**
     * 子资源
     */
    private List<MenuTreeVO> children;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 资源顺序
     */
    private String resRid;
    /**
     * 资源描述
     */
    private String resDesc;

    /**
     * 资源父ID
     */
    private String resPid;

    /**
     * 添加子资源
     *
     * @param menuTreeVO 资源对象
     */
    public void addSubResource(MenuTreeVO menuTreeVO) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(menuTreeVO);
    }

    public String getResDesc() {
        return resDesc;
    }

    public void setResDesc(String resDesc) {
        this.resDesc = resDesc;
    }

    public List<MenuTreeVO> getChildren() {
        return children;
    }

    public void setChildren(List<MenuTreeVO> children) {
        this.children = children;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getResRid() {
        return resRid;
    }

    public void setResRid(String resRid) {
        this.resRid = resRid;
    }

    public String getResPid() {
        return resPid;
    }

    public void setResPid(String resPid) {
        this.resPid = resPid;
    }

    public Long getResId() {
        return resId;
    }

    public String getResName() {
        return resName;
    }

    public String getResUrl() {
        return resUrl;
    }

    public String getResIcon() {
        return resIcon;
    }

    public void setResId(Long resId) {
        this.resId = resId;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    public void setResIcon(String resIcon) {
        this.resIcon = resIcon;
    }

    public Short getResStyle() {
        return resStyle;
    }

    public void setResStyle(Short resStyle) {
        this.resStyle = resStyle;
    }

    public Short getResStatus() {
        return resStatus;
    }

    public void setResStatus(Short resStatus) {
        this.resStatus = resStatus;
    }

    public Short getResType() {
        return resType;
    }

    public void setResType(Short resType) {
        this.resType = resType;
    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }
}
