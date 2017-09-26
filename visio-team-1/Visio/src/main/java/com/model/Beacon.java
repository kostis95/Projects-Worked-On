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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author geoffrey
 */
@Entity
@Table(name = "beacon")
@NamedQueries({
    @NamedQuery(name = "Beacon.findAll", query = "SELECT b FROM Beacon b"),
    @NamedQuery(name = "Beacon.findByBeaconId", query = "SELECT b FROM Beacon b WHERE b.beaconId = :beaconId"),
    @NamedQuery(name = "Beacon.findByUuid", query = "SELECT b FROM Beacon b WHERE b.uuid = :uuid"),
    @NamedQuery(name = "Beacon.findByMajor", query = "SELECT b FROM Beacon b WHERE b.major = :major"),
    @NamedQuery(name = "Beacon.findByMinor", query = "SELECT b FROM Beacon b WHERE b.minor = :minor"),
    @NamedQuery(name = "Beacon.findByDangerLevel", query = "SELECT b FROM Beacon b WHERE b.dangerLevel = :dangerLevel"),
    @NamedQuery(name = "Beacon.findByLongitude", query = "SELECT b FROM Beacon b WHERE b.longitude = :longitude"),
    @NamedQuery(name = "Beacon.findByLatitude", query = "SELECT b FROM Beacon b WHERE b.latitude = :latitude")})
public class Beacon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "beaconId")
    private Integer beaconId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "uuid")
    private String uuid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "major")
    private int major;
    @Basic(optional = false)
    @NotNull
    @Column(name = "minor")
    private int minor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dangerLevel")
    private int dangerLevel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "longitude")
    private float longitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "latitude")
    private float latitude;
   // @OneToMany(cascade = CascadeType.ALL, mappedBy = "beacon")
   //  private List<BeaconData> beaconDataList;
    @JoinColumn(name = "beaconGroupId", referencedColumnName = "beaconGroupId")
    @ManyToOne
    private BeaconGroup beaconGroupId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "beaconbeaconId", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Waypoints> waypointsList;

    public Beacon() {
    }

    public Beacon(Integer beaconId) {
        this.beaconId = beaconId;
    }

    public Beacon(Integer beaconId, String uuid, int major, int minor, int dangerLevel, float longitude, float latitude) {
        this.beaconId = beaconId;
        this.uuid = uuid;
        this.major = major;
        this.minor = minor;
        this.dangerLevel = dangerLevel;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Integer getBeaconId() {
        return beaconId;
    }

    public void setBeaconId(Integer beaconId) {
        this.beaconId = beaconId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public int getMinor() {
        return minor;
    }

    public void setMinor(int minor) {
        this.minor = minor;
    }

    public int getDangerLevel() {
        return dangerLevel;
    }

    public void setDangerLevel(int dangerLevel) {
        this.dangerLevel = dangerLevel;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

 /*   @XmlTransient
    public List<BeaconData> getBeaconDataList() {
        return beaconDataList;
    }

    public void setBeaconDataList(List<BeaconData> beaconDataList) {
        this.beaconDataList = beaconDataList;
    }*/

    public BeaconGroup getBeaconGroupId() {
        return beaconGroupId;
    }

    public void setBeaconGroupId(BeaconGroup beaconGroupId) {
        this.beaconGroupId = beaconGroupId;
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
        hash += (beaconId != null ? beaconId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Beacon)) {
            return false;
        }
        Beacon other = (Beacon) object;
        if ((this.beaconId == null && other.beaconId != null) || (this.beaconId != null && !this.beaconId.equals(other.beaconId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.Beacon[ beaconId=" + beaconId + " ]";
    }
    
}
