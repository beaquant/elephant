package com.djt.cbs.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.djt.cbs.web.util.ResponseUtil;

@Controller
@RequestMapping("/experiment")
public class ExperimentController {

    private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(ExperimentController.class);

    @RequestMapping(value = { "/config.html" }, method = { RequestMethod.GET })
    public String experiment_config(HttpServletRequest request, ModelMap model) {

        return "experiment/config";
    }

    @RequestMapping(value = { "/chart.html" }, method = { RequestMethod.GET })
    public String experiment_chart(HttpServletRequest request, ModelMap model) {

        return "experiment/chart";
    }

    /**
     * 获取实验结果数据
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = { "/get_experiment_data.html" }, method = { RequestMethod.GET })
    public void getMeStatisticDataForInviter(HttpServletRequest request,
                                             HttpServletResponse response,
                                             @RequestParam(required = false) Integer expId) {
        //返回json字符串
        response.setContentType("text/json");

        String responseData = "{\"success\":true, \"ilist\": [], \"mslist\": []}";
        ResponseUtil.flushJson(response, responseData);
        return;
    }

}
