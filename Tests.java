@Test
public void testContains()
{
@@ -37,10 +36,10 @@ public void testContains()
assertEquals("Check Contins 5", true, tree.contains(5));

}



@Test
public void testCalls() {
public void testCalls()
{

// Check Empty
BST<Integer, Integer> tree = new BST<Integer, Integer>();
@@ -52,7 +51,7 @@ public void testCalls() {
tree.put(50, 50);
assertEquals("Check Root 30", "30", String.valueOf(tree.get(30)));
assertEquals("Check Solo Right Node", "50", String.valueOf(tree.get(50)));


tree = new BST<Integer, Integer>();
tree.put(80, 80);
tree.put(90, 90);
@@ -63,6 +62,11 @@ public void testCalls() {
tree.put(50, 50);
tree.put(60, 60);

assertNull("Check no Node", tree.get(0));
assertEquals("Check Node Right", "90", String.valueOf(tree.get(90)));
assertEquals("Check Left Node", "30", String.valueOf(tree.get(30)));
assertEquals("Check Double Node", "40", String.valueOf(tree.get(40)));

tree = new BST<Integer, Integer>();
tree.put(10, 10);
tree.put(20, 20);
@@ -72,15 +76,118 @@ public void testCalls() {
assertEquals("Check the ability to get root node with two childs and that is all.", "10",
String.valueOf(tree.get(10)));


}

assertNull("Check no Node", tree.get(0));
assertEquals("Check Node Right", "90", String.valueOf(tree.get(90)));
assertEquals("Check Left Node", "30", String.valueOf(tree.get(30)));
assertEquals("Check Double Node", "40", String.valueOf(tree.get(40)));
@Test
public void DeleteTest()
{
BST<Integer, Integer> bst = new BST<Integer, Integer>();
bst.delete(1);
assertEquals("Deleting from empty tree", "()", bst.printKeysInOrder());

bst.put(7, 7); // _7_
bst.put(8, 8); // / \
bst.put(3, 3); // _3_ 8
bst.put(1, 1); // / \
bst.put(2, 2); // 1 6
bst.put(6, 6); // \ /
bst.put(4, 4); // 2 4
bst.put(5, 5); // \
// 5

assertEquals("Checking order of constructed tree", "(((()1(()2()))3((()4(()5()))6()))7(()8()))",
bst.printKeysInOrder());

bst.delete(9);
assertEquals("Deleting non-existent key", "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());

bst.delete(8);
assertEquals("Deleting leaf", "(((()1(()2()))3((()4(()5()))6()))7())", bst.printKeysInOrder());

bst.delete(6);
assertEquals("Deleting node with single child", "(((()1(()2()))3(()4(()5())))7())", bst.printKeysInOrder());

bst.delete(3);
assertEquals("Deleting node with two children", "(((()1())2(()4(()5())))7())", bst.printKeysInOrder());
}

@Test
public void testHeight()
{
BST<Integer, Integer> tree = new BST<Integer, Integer>();
assertEquals("Empty Height", "-1", "" + tree.height());

tree.put(100, 100);
tree.put(200, 200);
tree.put(300, 300);
tree.put(150, 150);
tree.put(250, 250);
tree.put(600, 600);
tree.put(50, 50);
tree.put(40, 40);

assertEquals("Check the height of the BST", "3", "" + tree.height());

tree = new BST<Integer, Integer>();
tree.put(1, 1);
tree.put(2, 2);

assertEquals("Check 1 Node Height", "1", String.valueOf(tree.height()));

}

@Test
public void testPut()
{
// Check Null
BST<Integer, Integer> tree = new BST<Integer, Integer>();
tree.put(1, null);
assertNull("Check Null", tree.get(1));

// Check Put
tree = new BST<Integer, Integer>();
tree.put(80, 80);
tree.put(90, 90);
tree.put(40, 40);
tree.put(20, 20);
tree.put(30, 30);
tree.put(70, 70);
tree.put(50, 50);
tree.put(60, 60);

// root
assertEquals("Check the ability to get root node.", "80", "" + tree.get(80));
// Level 1
assertEquals("Check Left", "30", "" + tree.get(30));
assertEquals("Check Right", "90", "" + tree.get(90));
// Level 2
assertEquals("Check Right Child", "20", "" + tree.get(20));
assertEquals("Check Left Child", "70", "" + tree.get(70));
assertEquals("Check Two Child Node", "40", "" + tree.get(40));
// assertNull("Check the ability to show an error if there is no node to
// check", tree.get(100));

// Check Double put
tree = new BST<Integer, Integer>();
tree.put(100, 100);
tree.put(100, 100);
assertEquals("Check Double put with same key", Integer.valueOf(100), tree.get(100));
}


@Test
public void testLCA() {

//simple above node LCA
BST<Integer, Integer> tree = new BST<Integer, Integer>();
tree.put(80, 80);
tree.put(40, 40);
tree.put(100, 100);
tree.put(20, 20);
tree.put(60, 60);

tree.getLCA(20, 60);

}

} 

