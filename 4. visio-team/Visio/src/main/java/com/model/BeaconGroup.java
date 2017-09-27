/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author geoffrey
 */
@Entity
@Table(name = "beacongroup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BeaconGroup.findAll", query = "SELECT b FROM BeaconGroup b"),
    @NamedQuery(name = "BeaconGroup.findByBeaconGroupId", query = "SELECT b FROM BeaconGroup b WHERE b.beaconGroupId = :beaconGroupId"),
    @NamedQuery(name = "BeaconGroup.findByDescription", query = "SELECT b FROM BeaconGroup b WHERE b.description = :description")})
public class BeaconGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "beaconGroupId")
    private Integer beaconGroupId;
    @Size(max = 45)
    @Column(name = "description")
    private String description;
    //@OneToMany(mappedBy = "beaconGroupId")
   // private List<Beacon> beaconList;

    public BeaconGroup() {
    }

    public BeaconGroup(Integer beaconGroupId) {
        this.beaconGroupId = beaconGroupId;
    }

    public Integer getBeaconGroupId() {
        return beaconGroupId;
    }

    public void setBeaconGroupId(Integer beaconGroupId) {
        this.beaconGroupId = beaconGroupId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

 /*   @XmlTransient
    public List<Beacon> getBeaconList() {
        return beaconList;
    }

    public void setBeaconList(List<Beacon> beaconList) {
        this.beaconList = beaconList;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (beaconGroupId != null ? beaconGroupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BeaconGroup)) {
            return false;
        }
        BeaconGroup other = (BeaconGroup) object;
        if ((this.beaconGroupId == null && other.beaconGroupId != null) || (this.beaconGroupId != null && !this.beaconGroupId.equals(other.beaconGroupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.BeaconGroup[ beaconGroupId=" + beaconGroupId + " ]";
    }
    
}
