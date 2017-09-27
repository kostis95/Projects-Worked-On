/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 *
 * @author geoffrey
 */
@Entity
@Table(name = "waypoints")
@NamedQueries({
    @NamedQuery(name = "Waypoints.findAll", query = "SELECT w FROM Waypoints w"),
    @NamedQuery(name = "Waypoints.findByRouteId", query = "SELECT w FROM Waypoints w WHERE w.waypointsPK.routeId = :routeId"),
    @NamedQuery(name = "Waypoints.findByOrderId", query = "SELECT w FROM Waypoints w WHERE w.waypointsPK.orderId = :orderId")})
public class Waypoints implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected WaypointsPK waypointsPK;
    @JoinColumn(name = "Beacon_beaconId", referencedColumnName = "beaconId")
    @ManyToOne(optional = false, fetch=FetchType.EAGER)
    private Beacon beaconbeaconId;
    @JoinColumn(name = "routeId", referencedColumnName = "routeId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Route route;
    
    public Waypoints() {
        
    }

    public Waypoints(WaypointsPK waypointsPK) {
        this.waypointsPK = waypointsPK;
    }

    public Waypoints(int routeId, int orderId) {
        this.waypointsPK = new WaypointsPK(routeId, orderId);
    }

    public WaypointsPK getWaypointsPK() {
        return waypointsPK;
    }

    public void setWaypointsPK(WaypointsPK waypointsPK) {
        this.waypointsPK = waypointsPK;
    }

    public Beacon getBeaconbeaconId() {
        return beaconbeaconId;
    }

    public void setBeaconbeaconId(Beacon beaconbeaconId) {
        this.beaconbeaconId = beaconbeaconId;
    }

    public Route getRoute() {
        return route;
    }
    
    public void setRoute(Route route) {
        this.route = route;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (waypointsPK != null ? waypointsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Waypoints)) {
            return false;
        }
        Waypoints other = (Waypoints) object;
        if ((this.waypointsPK == null && other.waypointsPK != null) || (this.waypointsPK != null && !this.waypointsPK.equals(other.waypointsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.Waypoints[ waypointsPK=" + waypointsPK + " ]";
    }
    
}
