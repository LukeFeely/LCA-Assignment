public Node getLCA(int i, int j) {



return null;


}

// is the symbol table empty?
public boolean isEmpty()
@@ -79,6 +90,10 @@ private int height(Node node)

return 1 + Math.max(height(node.left), height(node.right));
}





/**
 * Search BST for given key. Does there exist a key-value pair with given
 @@ -188,32 +203,7 @@ private String printKeysInOrder(Node node)
 }
 }
 
 /**
 * Pretty Printing the tree. Each node is on one line -- see assignment for
 * details.
 *
 * @return a multi-line string with the pretty ascii picture of the tree.
 */
public String prettyPrintKeys()
{
if (isEmpty()) { return NULL_STRING + NEXT_LINE; }

return prettyPrint(root, new String()) + NEXT_LINE;
}

private String prettyPrint(Node node, String tmp)
{
String result = "";
if (isNull(node))
{
result = tmp + NULL_STRING;
} else
{
result = tmp + "-" + node.val + NEXT_LINE + prettyPrint(node.left, tmp + " |") + NEXT_LINE
+ prettyPrint(node.right, tmp + "  ");
}
return result;
}

