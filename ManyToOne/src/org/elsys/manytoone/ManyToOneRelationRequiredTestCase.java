package org.elsys.manytoone;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ManyToOneRelationRequiredTestCase {

	@Test
	public void testEqualsTrue() throws Exception {
		ManyToOneRelation<String, Integer> relation1 = new ManyToOneRelation<String, Integer>();
		ManyToOneRelation<String, Integer> relation2 = new ManyToOneRelation<String, Integer>();
		relation1.connect("Integer1", new Integer(1));
		relation1.connect("Integer2", new Integer(2));
		relation2.connect("Integer1", new Integer(1));
		relation2.connect("Integer2", new Integer(2));
		assertTrue(relation1.equals(relation2));
	}

	@Test
	public void testEqualsFalse() throws Exception {
		ManyToOneRelation<String, Integer> relation1 = new ManyToOneRelation<String, Integer>();
		ManyToOneRelation<String, Integer> relation2 = new ManyToOneRelation<String, Integer>();
		relation1.connect("Integer1", new Integer(1));
		relation2.connect("Integer1", new Integer(1));
		relation2.connect("Integer2", new Integer(2));
		assertFalse(relation1.equals(relation2));
	}
}
