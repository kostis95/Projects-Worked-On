/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author geoffrey
 */
@Entity
@Table(name = "externaluser")
@NamedQueries({
    @NamedQuery(name = "ExternalUser.findAll", query = "SELECT e FROM ExternalUser e"),
    @NamedQuery(name = "ExternalUser.findByExternalUserId", query = "SELECT e FROM ExternalUser e WHERE e.externalUserId = :externalUserId"),
    @NamedQuery(name = "ExternalUser.findByBirthdate", query = "SELECT e FROM ExternalUser e WHERE e.birthdate = :birthdate"),
    @NamedQuery(name = "ExternalUser.findByGender", query = "SELECT e FROM ExternalUser e WHERE e.gender = :gender"),
    @NamedQuery(name = "ExternalUser.findByName", query = "SELECT e FROM ExternalUser e WHERE e.name = :name"),
    @NamedQuery(name = "ExternalUser.findByApiToken", query = "SELECT e FROM ExternalUser e WHERE e.apiToken = :apiToken")})
public class ExternalUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "externalUserId")
    private Integer externalUserId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "gender")
    private String gender;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "apiToken")
    private String apiToken;
    @ManyToMany(mappedBy = "externalUserList", fetch=FetchType.EAGER)
    @JsonIgnore
    private List<Route> routeList;
    @JoinColumn(name = "VITId", referencedColumnName = "VITId")
    @ManyToOne(optional = false)
    private VIT vITId;

    public ExternalUser() {
    }

    public ExternalUser(Integer externalUserId) {
        this.externalUserId = externalUserId;
    }

    public ExternalUser(Integer externalUserId, Date birthdate, String gender, String name, String apiToken) {
        this.externalUserId = externalUserId;
        this.birthdate = birthdate;
        this.gender = gender;
        this.name = name;
        this.apiToken = apiToken;
    }

    public Integer getExternalUserId() {
        return externalUserId;
    }

    public void setExternalUserId(Integer externalUserId) {
        this.externalUserId = externalUserId;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    @XmlTransient
    public List<Route> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<Route> routeList) {
        this.routeList = routeList;
    }

    public VIT getVITId() {
        return vITId;
    }

    public void setVITId(VIT vITId) {
        this.vITId = vITId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (externalUserId != null ? externalUserId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExternalUser)) {
            return false;
        }
        ExternalUser other = (ExternalUser) object;
        if ((this.externalUserId == null && other.externalUserId != null) || (this.externalUserId != null && !this.externalUserId.equals(other.externalUserId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.ExternalUser[ externalUserId=" + externalUserId + " ]";
    }
    
}
