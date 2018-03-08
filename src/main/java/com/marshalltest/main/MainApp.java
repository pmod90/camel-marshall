package com.marshalltest.main;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import com.marshalltest.route.SimpleRouteBuilder;

public class MainApp {

	public static void main(String[] args) {
		SimpleRouteBuilder routeBuilder = new SimpleRouteBuilder();
		CamelContext ctx = new DefaultCamelContext();

		try {
			ctx.addRoutes(routeBuilder);
			ctx.start();
			Thread.sleep(5000);
			ctx.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}