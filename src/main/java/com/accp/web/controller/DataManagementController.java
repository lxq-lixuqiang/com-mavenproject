package com.accp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.accp.web.util.Common;
import com.accp.web.util.WebTools;
/**
 * 数据管理控制器
 * @author Y2项目:李旭强
 *
 */
@Controller
public class DataManagementController {

	@GetMapping("dataManagement")
	public String dataManagement() {
		WebTools.setSession(Common.DATA_MANAGEMENT,"dataManagement");
		return "dataManagement";
	}
}
