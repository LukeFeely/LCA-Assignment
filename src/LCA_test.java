import static org.junit.Assert.*;

import org.junit.Test;

public class LCA_test {

	
	@Test
	public void testLCA() {
		
		LCA<Integer, Integer> bst = new LCA<Integer, Integer>();
		
		assertSame("Testing LCA for null root", null, bst.lowestCommonAncestor(bst.root, 1, 2));
		
		bst.put(7, 7);
		bst.put(8, 8);   
		bst.put(3, 3);   
		bst.put(1, 1);   
		bst.put(2, 2);  
		bst.put(6, 6);   
		bst.put(4, 4); 
		bst.put(5, 5);  		 	
		assertSame("Testing LCA left side", 3, bst.lowestCommonAncestor(bst.root, 2,6));
		assertSame("Testing LCA right side", 7, bst.lowestCommonAncestor(bst.root, 8,3));
		assertSame("Testing LCA where LCA is one of the nodes", 7, bst.lowestCommonAncestor(bst.root, 7,8));
		assertSame("Testing LCA where LCA is one of the nodes", 7, bst.lowestCommonAncestor(bst.root, 3,7));
	}



	@Test
	public void testPrettyPrint() {
		LCA<Integer, Integer> LCA = new LCA<Integer, Integer>();
		assertEquals("Checking pretty printing of empty tree",
				"-null\n", LCA.prettyPrintKeys());

		
		LCA.put(7, 7); 
		LCA.put(8, 8);     
		LCA.put(3, 3);
		LCA.put(1, 1);    
		LCA.put(2, 2);       
		LCA.put(6, 6);      
		LCA.put(4, 4);   
		LCA.put(5, 5);      
		

		String result = 
				"-7\n" +
						" |-3\n" + 
						" | |-1\n" +
						" | | |-null\n" + 
						" | |  -2\n" +
						" | |   |-null\n" +
						" | |    -null\n" +
						" |  -6\n" +
						" |   |-4\n" +
						" |   | |-null\n" +
						" |   |  -5\n" +
						" |   |   |-null\n" +
						" |   |    -null\n" +
						" |    -null\n" +
						"  -8\n" +
						"   |-null\n" +
						"    -null\n";
		assertEquals("Checking pretty printing of non-empty tree", result, LCA.prettyPrintKeys());


	}

	@Test
	public void testDelete() {
		LCA<Integer, Integer> LCA = new LCA<Integer, Integer>();
		LCA.delete(1);
		assertEquals("Deleting from empty tree", "()", LCA.printKeysInOrder());

		LCA.put(7, 7);   //        _7_
		LCA.put(8, 8);   //      /     \
		LCA.put(3, 3);   //    _3_      8
		LCA.put(1, 1);   //  /     \
		LCA.put(2, 2);   // 1       6
		LCA.put(6, 6);   //  \     /
		LCA.put(4, 4);   //   2   4
		LCA.put(5, 5);   //        \
		//         5

		assertEquals("Checking order of constructed tree",
				"(((()1(()2()))3((()4(()5()))6()))7(()8()))", LCA.printKeysInOrder());

		LCA.delete(9);
		assertEquals("Deleting non-existent key",
				"(((()1(()2()))3((()4(()5()))6()))7(()8()))", LCA.printKeysInOrder());

		LCA.delete(8);
		assertEquals("Deleting leaf", "(((()1(()2()))3((()4(()5()))6()))7())", LCA.printKeysInOrder());

		LCA.delete(6);
		assertEquals("Deleting node with single child",
				"(((()1(()2()))3(()4(()5())))7())", LCA.printKeysInOrder());

		LCA.delete(3);
		assertEquals("Deleting node with two children",
				"(((()1())2(()4(()5())))7())", LCA.printKeysInOrder());
	}

	@Test
	public void testPut() {
		LCA<Integer, Integer> LCA = new LCA<Integer, Integer>();
		LCA.put(1, null);
		LCA.put(10, 1);
		LCA.put(15,2);
		LCA.put(15, 15);	 

		assertEquals("Putting nodes", "(()10(()15()))", LCA.printKeysInOrder());
	}

	@Test
	public void testGet() {
		LCA<Integer, Integer> LCA = new LCA<Integer, Integer>();
		assertEquals("Testing empty", false, LCA.contains(5));
		LCA.put(1, null);
		LCA.put(10, 1);
		LCA.put(5, 9);
		LCA.put(15,2);
		LCA.put(9, 15);	 

		assertEquals("Testing left", "9", LCA.get(5).toString());
		assertEquals("Testing right then right", "2", LCA.get(15).toString());
		assertEquals("Testing right then left", "15", LCA.get(9).toString());
		assertEquals("Testing root", "1", LCA.get(10).toString());
	}

	@Test
	public void testHeight() {
		LCA<Integer, Integer> LCA = new LCA<Integer, Integer>();

		assertEquals("Testing height", -1, LCA.height());

		LCA.put(7, 7);  

		assertEquals("Testing height", 0, LCA.height());

		LCA.put(8, 8);   
		LCA.put(3, 3);  

		assertEquals("Testing height", 1, LCA.height());

		LCA.put(1, 1);      
		LCA.put(2, 2);   

		assertEquals("Testing height", 3, LCA.height());

		LCA.put(6, 6);   
		LCA.put(4, 4);   
		LCA.put(5, 5);

		assertEquals("Testing height", 4, LCA.height());

	}

	@Test
	public void testMedian() {
		LCA<Integer, Integer> LCA = new LCA<Integer, Integer>();
		assertEquals("Testing median", null, LCA.median());
		LCA.put(7, 7); 
		assertEquals("Testing median", "7", LCA.median().toString());
		LCA.put(8, 8);   
		LCA.put(3, 3);  
		assertEquals("Testing median", "7", LCA.median().toString());
		LCA.put(1, 1);      
		LCA.put(2, 2); 
		assertEquals("Testing median", "3", LCA.median().toString());
		LCA.put(6, 6);   
		LCA.put(4, 4);   
		LCA.put(5, 5);
		assertEquals("Testing median", "4", LCA.median().toString());
	}
	@Test
	public void testContains() {
		LCA<Integer, Integer> LCA = new LCA<Integer, Integer>();
		assertEquals("Testing contains", false, LCA.contains(1));
		LCA.put(7, 7); 
		assertEquals("Testing contains", true, LCA.contains(7));
	}


}