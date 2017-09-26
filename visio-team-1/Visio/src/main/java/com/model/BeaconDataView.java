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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.web.multipart.MultipartFile;


public class BeaconDataView  {

    protected BeaconDataPK beaconDataPK;
    private MultipartFile data;
    private DataType dataType;
    private VIT vit;

    public BeaconDataView() {
    }

    public BeaconDataView(BeaconDataPK beaconDataPK) {
        this.beaconDataPK = beaconDataPK;
    }

    public BeaconDataView(BeaconDataPK beaconDataPK, MultipartFile data) {
        this.beaconDataPK = beaconDataPK;
        this.data = data;
    }

    public BeaconDataView(int beaconId, int vITId, int dataTypeId) {
        this.beaconDataPK = new BeaconDataPK(beaconId, vITId, dataTypeId);
    }

    public BeaconDataPK getBeaconDataPK() {
        return beaconDataPK;
    }

    public void setBeaconDataPK(BeaconDataPK beaconDataPK) {
        this.beaconDataPK = beaconDataPK;
    }

    public MultipartFile getData() {
        return data;
    }

    public void setData(MultipartFile data) {
        this.data = data;
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
        if (!(object instanceof BeaconDataView)) {
            return false;
        }
        BeaconDataView other = (BeaconDataView) object;
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
