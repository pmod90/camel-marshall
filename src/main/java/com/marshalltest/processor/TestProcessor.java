package com.marshalltest.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.marshalltest.model.Blog;

public class TestProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {
		Blog blog = exchange.getIn().getBody(Blog.class);
		blog.setStatus("new status");
		exchange.getIn().setBody(blog);
	}

}
