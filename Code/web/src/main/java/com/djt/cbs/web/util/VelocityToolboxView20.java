/**
 * mbaobao.com Inc. Copyright (c) 2010-2012 All Rights Reserved.
 */
package com.djt.cbs.web.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.context.Context;
import org.apache.velocity.tools.Scope;
import org.apache.velocity.tools.ToolManager;
import org.apache.velocity.tools.view.ViewToolContext;
import org.springframework.web.servlet.view.velocity.VelocityToolboxView;

/**
 * spring 扩展模块默认支持的是VelocityTool 1.X的版本的 这个类针对使用VelocityTool2.0
 * @author jun.panhj
 * @email duixia@mbaobao.com
 * 
 * @version Id:VelocityToolboxView20.java v 0.1 2011-12-8 下午06:46:41
 */
public class VelocityToolboxView20 extends VelocityToolboxView {
	@Override
	protected Context createVelocityContext(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ViewToolContext ctx = new ViewToolContext(getVelocityEngine(), request, response,
				getServletContext());

		ctx.putAll(model);

		if (this.getToolboxConfigLocation() != null) {
			ToolManager tm = new ToolManager();
			tm.setVelocityEngine(getVelocityEngine());
			tm.configure(getServletContext().getRealPath(getToolboxConfigLocation()));
			if (tm.getToolboxFactory().hasTools(Scope.REQUEST)) {
				ctx.addToolbox(tm.getToolboxFactory().createToolbox(Scope.REQUEST));
			}
			if (tm.getToolboxFactory().hasTools(Scope.APPLICATION)) {
				ctx.addToolbox(tm.getToolboxFactory().createToolbox(Scope.APPLICATION));
			}
			if (tm.getToolboxFactory().hasTools(Scope.SESSION)) {
				ctx.addToolbox(tm.getToolboxFactory().createToolbox(Scope.SESSION));
			}
		}
		
		return ctx;
	}
}