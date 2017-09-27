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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 *
 * @author geoffrey
 * @author Youri Dudock, head developer at team visio
 */
@Entity
@Table(name = "route")
@NamedQueries({
    @NamedQuery(name = "Route.findAll", query = "SELECT r FROM Route r"),
    @NamedQuery(name = "Route.findByRouteId", query = "SELECT r FROM Route r WHERE r.routeId = :routeId"),
    @NamedQuery(name = "Route.findByDescription", query = "SELECT r FROM Route r WHERE r.description = :description"),
    @NamedQuery(name = "Route.findByBegin", query = "SELECT r FROM Route r WHERE r.begin = :begin"),
    @NamedQuery(name = "Route.findByEnd", query = "SELECT r FROM Route r WHERE r.end = :end")})
public class Route implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "routeId")
    private Integer routeId;
    @Size(max = 45)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "begin")
    private String begin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "end")
    private String end;
    @JoinTable(name = "route_externaluser", joinColumns = {
        @JoinColumn(name = "routesId", referencedColumnName = "routeId")}, inverseJoinColumns = {
        @JoinColumn(name = "externalUserId", referencedColumnName = "externalUserId")})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<ExternalUser> externalUserList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "route", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Waypoints> waypointsList;

    public Route() {
    }

    public Route(Integer routeId) {
        this.routeId = routeId;
    }

    public Route(Integer routeId, String begin, String end) {
        this.routeId = routeId;
        this.begin = begin;
        this.end = end;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }


    public List<ExternalUser> getExternalUserList() {
        return externalUserList;
    }

    public void setExternalUserList(List<ExternalUser> externalUserList) {
        this.externalUserList = externalUserList;
    }
    
    public List<Waypoints> getWaypointsList() {
        return waypointsList;
    }
  
    public void setWaypointsList(List<Waypoints> waypointsList) {
        this.waypointsList = waypointsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (routeId != null ? routeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Route)) {
            return false;
        }
        Route other = (Route) object;
        if ((this.routeId == null && other.routeId != null) || (this.routeId != null && !this.routeId.equals(other.routeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.Route[ routeId=" + routeId + " ]";
    }
    
}
