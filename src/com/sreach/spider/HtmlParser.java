package com.sreach.spider;

import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.luzou.bean.NewsBean;

public class HtmlParser {

	public static NewsBean getNewsList(String url) {
		NewsBean nb = new NewsBean();
		try {
			Parser parser = new Parser(url);
			parser.setEncoding("utf-8"); //
			AndFilter title_filter = new AndFilter(new TagNameFilter("h1"),
					new HasAttributeFilter("id", "artical_topic"));
			NodeList title_nodes = parser.parse(title_filter);
		
			nb.setTitle(title_nodes.asString());
			parser = new Parser(url);
			parser.setEncoding("utf-8");
			AndFilter content_filter = new AndFilter(new TagNameFilter("div"),
					new HasAttributeFilter("id", "main_content"));
			NodeList contetn_nodes = parser.parse(content_filter);
		
			nb.setContent(contetn_nodes.asString());

		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return nb;
	}

}
