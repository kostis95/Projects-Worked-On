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
public class WaypointsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "routeId")
    private int routeId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "orderId")
    private int orderId;

    public WaypointsPK() {
    }

    public WaypointsPK(int routeId, int orderId) {
        this.routeId = routeId;
        this.orderId = orderId;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) routeId;
        hash += (int) orderId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WaypointsPK)) {
            return false;
        }
        WaypointsPK other = (WaypointsPK) object;
        if (this.routeId != other.routeId) {
            return false;
        }
        if (this.orderId != other.orderId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.WaypointsPK[ routeId=" + routeId + ", orderId=" + orderId + " ]";
    }
    
}
