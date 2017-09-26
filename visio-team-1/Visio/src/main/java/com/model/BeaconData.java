/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author geoffrey
 */
@Entity
@Table(name = "beacondata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BeaconData.findAll", query = "SELECT b FROM BeaconData b"),
    @NamedQuery(name = "BeaconData.findByVITId", query = "SELECT b FROM BeaconData b WHERE b.beaconDataPK.vITId = :vITId"),
    @NamedQuery(name = "BeaconData.findByDataTypeId", query = "SELECT b FROM BeaconData b WHERE b.beaconDataPK.dataTypeId = :dataTypeId"),
    @NamedQuery(name = "BeaconData.findByBeaconbeaconId", query = "SELECT b FROM BeaconData b WHERE b.beaconDataPK.beaconbeaconId = :beaconbeaconId")})
public class BeaconData implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected BeaconDataPK beaconDataPK;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "Data")
    private String data;
    @JoinColumn(name = "Beacon_beaconId", referencedColumnName = "beaconId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Beacon beacon;
    @JoinColumn(name = "dataTypeId", referencedColumnName = "dataTypeId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DataType dataType;
    @JoinColumn(name = "VITId", referencedColumnName = "VITId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private VIT vit;

    public BeaconData() {
    }

    public BeaconData(BeaconDataPK beaconDataPK) {
        this.beaconDataPK = beaconDataPK;
    }

    public BeaconData(BeaconDataPK beaconDataPK, String data) {
        this.beaconDataPK = beaconDataPK;
        this.data = data;
    }

    public BeaconData(int vITId, int dataTypeId, int beaconbeaconId) {
        this.beaconDataPK = new BeaconDataPK(vITId, dataTypeId, beaconbeaconId);
    }

    public BeaconDataPK getBeaconDataPK() {
        return beaconDataPK;
    }

    public void setBeaconDataPK(BeaconDataPK beaconDataPK) {
        this.beaconDataPK = beaconDataPK;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Beacon getBeacon() {
        return beacon;
    }

    public void setBeacon(Beacon beacon) {
        this.beacon = beacon;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public VIT getVit() {
        return vit;
    }

    public void setVit(VIT vit) {
        this.vit = vit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (beaconDataPK != null ? beaconDataPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BeaconData)) {
            return false;
        }
        BeaconData other = (BeaconData) object;
        if ((this.beaconDataPK == null && other.beaconDataPK != null) || (this.beaconDataPK != null && !this.beaconDataPK.equals(other.beaconDataPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.BeaconData[ beaconDataPK=" + beaconDataPK + " ]";
    }
    
}
