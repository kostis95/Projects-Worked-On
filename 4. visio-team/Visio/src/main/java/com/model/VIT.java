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
@Table(name = "vit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VIT.findAll", query = "SELECT v FROM VIT v"),
    @NamedQuery(name = "VIT.findByVITId", query = "SELECT v FROM VIT v WHERE v.vITId = :vITId"),
    @NamedQuery(name = "VIT.findByDescription", query = "SELECT v FROM VIT v WHERE v.description = :description")})
public class VIT implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "VITId")
    private Integer vITId;
    @Size(max = 45)
    @Column(name = "description")
    private String description;
  //  @OneToMany(cascade = CascadeType.ALL, mappedBy = "vit", fetch=FetchType.LAZY)
  //  private List<BeaconData> beaconDataList;
  ///  @OneToMany(cascade = CascadeType.ALL, mappedBy = "vITId")
  //  private List<ExternalUser> externalUserList;

    public VIT() {
    }

    public VIT(Integer vITId) {
        this.vITId = vITId;
    }

    public Integer getVITId() {
        return vITId;
    }
    
        public Integer getvITId() {
        return vITId;
    }

    public void setVITId(Integer vITId) {
        this.vITId = vITId;
    }

     public void setvITId(Integer vITId) {
        this.vITId = vITId;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

 /*   @XmlTransient
    public List<BeaconData> getBeaconDataList() {
        return beaconDataList;
    }

    public void setBeaconDataList(List<BeaconData> beaconDataList) {
        this.beaconDataList = beaconDataList;
    }*/

  //  @XmlTransient
 //   public List<ExternalUser> getExternalUserList() {
  //      return externalUserList;
 //   }

  //  public void setExternalUserList(List<ExternalUser> externalUserList) {
        //this.externalUserList = externalUserList;
  //  }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vITId != null ? vITId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VIT)) {
            return false;
        }
        VIT other = (VIT) object;
        if ((this.vITId == null && other.vITId != null) || (this.vITId != null && !this.vITId.equals(other.vITId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return description;
    }
    
}
