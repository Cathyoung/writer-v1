package com.slingerxv.writer.dao.model;

import javax.persistence.*;

@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 客户名称
     */
    @Column(name = "c_name")
    private String cName;

    @Column(name = "c_mobile")
    private String cMobile;

    /**
     * 1使用0删除
     */
    @Column(name = "c_enable")
    private Byte cEnable;

    /**
     * 写作或写发
     */
    @Column(name = "c_type")
    private String cType;

    @Column(name = "c_qq")
    private String cQq;

    /**
     * 微信号
     */
    @Column(name = "c_wechat")
    private String cWechat;

    /**
     * 地区
     */
    @Column(name = "c_region")
    private String cRegion;

    /**
     * 描述
     */
    @Column(name = "c_description")
    private String cDescription;

    /**
     * 职称
     */
    @Column(name = "c_title")
    private String cTitle;

    /**
     * 科室
     */
    @Column(name = "c_department")
    private String cDepartment;

    /**
     * 医院
     */
    @Column(name = "c_hospital")
    private String cHospital;

    /**
     * 难度系数
     */
    @Column(name = "c_difficulty")
    private Byte cDifficulty;

    private Integer creator;

    @Column(name = "creat_name")
    private String creatName;

    @Column(name = "create_time")
    private Long createTime;

    @Column(name = "updat_name")
    private String updatName;

    @Column(name = "update_time")
    private Long updateTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取客户名称
     *
     * @return c_name - 客户名称
     */
    public String getcName() {
        return cName;
    }

    /**
     * 设置客户名称
     *
     * @param cName 客户名称
     */
    public void setcName(String cName) {
        this.cName = cName == null ? null : cName.trim();
    }

    /**
     * @return c_mobile
     */
    public String getcMobile() {
        return cMobile;
    }

    /**
     * @param cMobile
     */
    public void setcMobile(String cMobile) {
        this.cMobile = cMobile == null ? null : cMobile.trim();
    }

    /**
     * 获取1使用0删除
     *
     * @return c_enable - 1使用0删除
     */
    public Byte getcEnable() {
        return cEnable;
    }

    /**
     * 设置1使用0删除
     *
     * @param cEnable 1使用0删除
     */
    public void setcEnable(Byte cEnable) {
        this.cEnable = cEnable;
    }

    /**
     * 获取写作或写发
     *
     * @return c_type - 写作或写发
     */
    public String getcType() {
        return cType;
    }

    /**
     * 设置写作或写发
     *
     * @param cType 写作或写发
     */
    public void setcType(String cType) {
        this.cType = cType == null ? null : cType.trim();
    }

    /**
     * @return c_qq
     */
    public String getcQq() {
        return cQq;
    }

    /**
     * @param cQq
     */
    public void setcQq(String cQq) {
        this.cQq = cQq == null ? null : cQq.trim();
    }

    /**
     * 获取微信号
     *
     * @return c_wechat - 微信号
     */
    public String getcWechat() {
        return cWechat;
    }

    /**
     * 设置微信号
     *
     * @param cWechat 微信号
     */
    public void setcWechat(String cWechat) {
        this.cWechat = cWechat == null ? null : cWechat.trim();
    }

    /**
     * 获取地区
     *
     * @return c_region - 地区
     */
    public String getcRegion() {
        return cRegion;
    }

    /**
     * 设置地区
     *
     * @param cRegion 地区
     */
    public void setcRegion(String cRegion) {
        this.cRegion = cRegion == null ? null : cRegion.trim();
    }

    /**
     * 获取描述
     *
     * @return c_description - 描述
     */
    public String getcDescription() {
        return cDescription;
    }

    /**
     * 设置描述
     *
     * @param cDescription 描述
     */
    public void setcDescription(String cDescription) {
        this.cDescription = cDescription == null ? null : cDescription.trim();
    }

    /**
     * 获取职称
     *
     * @return c_title - 职称
     */
    public String getcTitle() {
        return cTitle;
    }

    /**
     * 设置职称
     *
     * @param cTitle 职称
     */
    public void setcTitle(String cTitle) {
        this.cTitle = cTitle == null ? null : cTitle.trim();
    }

    /**
     * 获取科室
     *
     * @return c_department - 科室
     */
    public String getcDepartment() {
        return cDepartment;
    }

    /**
     * 设置科室
     *
     * @param cDepartment 科室
     */
    public void setcDepartment(String cDepartment) {
        this.cDepartment = cDepartment == null ? null : cDepartment.trim();
    }

    /**
     * 获取医院
     *
     * @return c_hospital - 医院
     */
    public String getcHospital() {
        return cHospital;
    }

    /**
     * 设置医院
     *
     * @param cHospital 医院
     */
    public void setcHospital(String cHospital) {
        this.cHospital = cHospital == null ? null : cHospital.trim();
    }

    /**
     * 获取难度系数
     *
     * @return c_difficulty - 难度系数
     */
    public Byte getcDifficulty() {
        return cDifficulty;
    }

    /**
     * 设置难度系数
     *
     * @param cDifficulty 难度系数
     */
    public void setcDifficulty(Byte cDifficulty) {
        this.cDifficulty = cDifficulty;
    }

    /**
     * @return creator
     */
    public Integer getCreator() {
        return creator;
    }

    /**
     * @param creator
     */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /**
     * @return creat_name
     */
    public String getCreatName() {
        return creatName;
    }

    /**
     * @param creatName
     */
    public void setCreatName(String creatName) {
        this.creatName = creatName == null ? null : creatName.trim();
    }

    /**
     * @return create_time
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * @return updat_name
     */
    public String getUpdatName() {
        return updatName;
    }

    /**
     * @param updatName
     */
    public void setUpdatName(String updatName) {
        this.updatName = updatName == null ? null : updatName.trim();
    }

    /**
     * @return update_time
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}