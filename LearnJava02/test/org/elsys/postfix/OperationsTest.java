package org.elsys.postfix;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class OperationsTest {	
	
	@Test
	public void PlusTest() {
		Plus op = new Plus();
		
		double res = op.calc(25.0, 10.0);
		
		assertEquals(35.0, res, 0.0001);
 	}
	
	@Test
	public void MinusTest() {
		Minus op = new Minus();
		
		double res = op.calc(5.0, 10.0);
		
		assertEquals(5.0, res, 0.0001);
 	}
	
	@Test
	public void MultiplyTest() {
		Multiply op = new Multiply();
		
		double res = op.calc(2.0, 10.0);
		
		assertEquals(20.0, res, 0.0001);
 	}
	
	@Test
	public void Devide() {
		Divide op = new Divide();
		
		double res = op.calc(10.0, 25.0);
		
		assertEquals(2.5, res, 0.0001);
 	}
	
 } 