/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.DataType;
import com.service.DataTypeService;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Gabriël
 */
@Controller
@RequestMapping(value = "/datatypes")
public class DataTypeController {

    @Autowired
    private DataTypeService dataTypeService;
    
    @ModelAttribute("DataType")
    public DataType getModelAttribute() {
        return new DataType();
    }

    @RequestMapping(value = "/overview", method = RequestMethod.GET)
    public ModelAndView overview() {
        ModelAndView view = new ModelAndView("datatype/datatypes");
      
        view.addObject("dataTypes", dataTypeService.getDataTypes());
        return view;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addDataTypePage() throws IOException {

        return new ModelAndView("datatype/add");
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addDataType(@ModelAttribute DataType dataType) throws IOException {
        ModelAndView view = new ModelAndView("datatype/datatypes");
        
        dataTypeService.addDataType(dataType);
        String message = "Data type has been added";
        view.addObject("message", message);
        view.addObject("dataTypes", dataTypeService.getDataTypes());
       
        return view;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editDataTypePage(@PathVariable("id") Integer id) throws IOException {

        ModelAndView editView = new ModelAndView("/datatype/edit");
        DataType dataType = dataTypeService.getDataType(id);
        editView.addObject("dataTypes", dataType);

        return editView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editDataTypeView(@ModelAttribute("dataType") DataType dataType) throws IOException {
        ModelAndView editView = new ModelAndView("/datatype/datatypes");

        dataTypeService.updateDataType(dataType);
        editView.addObject("dataTypes", dataTypeService.getDataTypes());

        String message = "Data type was succesfully edited.";
        editView.addObject("message", message);

        return editView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteDataType(HttpServletRequest request, @PathVariable Integer id) throws IOException {
        ModelAndView dataTypeView = new ModelAndView("/datatype/datatypes");

        dataTypeService.deleteDataType(id);

        List<DataType> dataTypes = dataTypeService.getDataTypes();
        dataTypeView.addObject("dataTypes", dataTypes);

        String message = "Data type is deleted";
        dataTypeView.addObject("message", message);

        return dataTypeView;
    }

}
