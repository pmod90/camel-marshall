package com.marshalltest.route;

import javax.xml.bind.JAXBContext;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.converter.jaxb.JaxbDataFormat;

import com.marshalltest.model.Blog;
import com.marshalltest.processor.TestProcessor;

public class SimpleRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		JaxbDataFormat xmlDataFormat = new JaxbDataFormat();
		JAXBContext con = JAXBContext.newInstance(Blog.class);
		xmlDataFormat.setContext(con);

		JacksonDataFormat jsonDataFormat = new JacksonDataFormat(Blog.class);

		from("file:data/input?noop=true")
		.doTry()
		.unmarshal(jsonDataFormat)
		.process(new TestProcessor())
		.marshal(xmlDataFormat)
		.to("file:data/output")
		.doCatch(Exception.class)
		.process(new Processor() {
					public void process(Exchange exchange) throws Exception {
						Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
						System.out.println(cause);
					}
				});

	}

}
