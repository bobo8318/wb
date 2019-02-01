package org.hao.tools;

import org.dom4j.Attribute;
import org.dom4j.CDATA;
import org.dom4j.Comment;
import org.dom4j.Document;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.Entity;
import org.dom4j.Namespace;
import org.dom4j.ProcessingInstruction;
import org.dom4j.Text;
import org.dom4j.Visitor;

public class XMLVisitorImply implements Visitor {

	
	@Override
	public void visit(Element element) {
		// TODO Auto-generated method stub
		System.out.println(element.getName()+":"+element.getStringValue());
	}

	@Override
	public void visit(Attribute attr) {
		// TODO Auto-generated method stub
		System.out.println(attr.getName());
	}

	@Override
	public void visit(Document arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(DocumentType arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(CDATA arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Comment arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Entity arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Namespace arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ProcessingInstruction arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Text arg0) {
		// TODO Auto-generated method stub
		
	}

	 

}
