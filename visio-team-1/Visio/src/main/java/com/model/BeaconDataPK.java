/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author geoffrey
 */
@Embeddable
public class BeaconDataPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "VITId")
    private int vITId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataTypeId")
    private int dataTypeId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Beacon_beaconId")
    private int beaconbeaconId;

    public BeaconDataPK() {
    }

    public BeaconDataPK(int vITId, int dataTypeId, int beaconbeaconId) {
        this.vITId = vITId;
        this.dataTypeId = dataTypeId;
        this.beaconbeaconId = beaconbeaconId;
    }

    public int getVITId() {
        return vITId;
    }

    public void setVITId(int vITId) {
        this.vITId = vITId;
    }

    public int getDataTypeId() {
        return dataTypeId;
    }

    public void setDataTypeId(int dataTypeId) {
        this.dataTypeId = dataTypeId;
    }

    public int getBeaconbeaconId() {
        return beaconbeaconId;
    }

    public void setBeaconbeaconId(int beaconbeaconId) {
        this.beaconbeaconId = beaconbeaconId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) vITId;
        hash += (int) dataTypeId;
        hash += (int) beaconbeaconId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BeaconDataPK)) {
            return false;
        }
        BeaconDataPK other = (BeaconDataPK) object;
        if (this.vITId != other.vITId) {
            return false;
        }
        if (this.dataTypeId != other.dataTypeId) {
            return false;
        }
        if (this.beaconbeaconId != other.beaconbeaconId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.BeaconDataPK[ vITId=" + vITId + ", dataTypeId=" + dataTypeId + ", beaconbeaconId=" + beaconbeaconId + " ]";
    }
    
}
