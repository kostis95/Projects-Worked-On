/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.dao.DataTypeDAO;
import com.model.DataType;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gabriël
 */
@Service
@Transactional
public class DataTypeService {
    
    @Autowired
    private DataTypeDAO dataTypeDAO;
    
    public DataType getDataType(int id) {
        return dataTypeDAO.getDataType(id);
    }
    public void addDataType(DataType dataType) {
        dataTypeDAO.addDataType(dataType);
    }
    
    public void updateDataType(DataType dataType) {
        dataTypeDAO.updateDataType(dataType);
    }
        
    public void deleteDataType(int id) {
        dataTypeDAO.deleteDataType(id);
    }
    
    public List<DataType> getDataTypes() {
        return dataTypeDAO.getDataTypes();
    }
}
