/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author geoffrey
 */
@Entity
@Table(name = "datatype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DataType.findAll", query = "SELECT d FROM DataType d"),
    @NamedQuery(name = "DataType.findByDataTypeId", query = "SELECT d FROM DataType d WHERE d.dataTypeId = :dataTypeId"),
    @NamedQuery(name = "DataType.findByDescription", query = "SELECT d FROM DataType d WHERE d.description = :description")})
public class DataType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "dataTypeId")
    private Integer dataTypeId;
    @Size(max = 45)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dataType")
    private List<BeaconData> beaconDataList;

    public DataType() {
    }

    public DataType(Integer dataTypeId) {
        this.dataTypeId = dataTypeId;
    }

    public Integer getDataTypeId() {
        return dataTypeId;
    }

    public void setDataTypeId(Integer dataTypeId) {
        this.dataTypeId = dataTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public List<BeaconData> getBeaconDataList() {
        return beaconDataList;
    }

    public void setBeaconDataList(List<BeaconData> beaconDataList) {
        this.beaconDataList = beaconDataList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dataTypeId != null ? dataTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DataType)) {
            return false;
        }
        DataType other = (DataType) object;
        if ((this.dataTypeId == null && other.dataTypeId != null) || (this.dataTypeId != null && !this.dataTypeId.equals(other.dataTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.DataType[ dataTypeId=" + dataTypeId + " ]";
    }
    
}
