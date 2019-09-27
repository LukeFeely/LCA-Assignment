import java.util.ArrayList;
import java.util.List;

/*************************************************************************
 * Binary Search Tree class. Adapted from Sedgewick and Wayne.
 *
 @@ -11,16 +14,20 @@
 public static final int EMPTY_ERROR = -1;
 public static final String NULL_STRING = "-null";
 public static final String NEXT_LINE = "\n";
 private Node root; // root of BST
 Node root; // root of BST
 
 /**
 * Private node class.
 */
private class Node

private List<Value> path1 = new ArrayList<>();
private List<Value> path2 = new ArrayList<>();

public class Node
{
    private Key key; // sorted by key
    private Value val; // associated data
    private Node left, right; // left and right subtrees
    Node left, right; // left and right subtrees
    private int N; // number of nodes in subtree
    
    public Node(Key key, Value val, int N)
    @@ -30,16 +37,46 @@ public Node(Key key, Value val, int N)
    this.N = N;
}
}



public Node getLCA(int i, int j) {



return null;



public Value getLCA(Value i, Value j)
{
path1.clear();
path2.clear();
return findLCAInternal(root, i, j);

}

private Value findLCAInternal(Node root, Value n1, Value n2)
{

if (!findPath(root, n1, path1) || !findPath(root, n2, path2)) return null;

int i;
for (i = 0; i < path1.size() && i < path2.size(); i++)

if (!path1.get(i).equals(path2.get(i))) break;

return path1.get(i - 1);
}

public boolean findPath(Node root, Value n2, List<Value> path)
{
// base case
if (root == null) { return false; }

// Store this node . The node will be removed if
// not in path from root to n.
path.add(root.val);

if (root.val == n2) { return true; }

if (root.left != null && findPath(root.left, n2, path)) { return true; }

if (root.right != null && findPath(root.right, n2, path)) { return true; }

path.remove(path.size() - 1);

return false;
}

// is the symbol table empty?
@@ -90,10 +127,6 @@ private int height(Node node)

return 1 + Math.max(height(node.left), height(node.right));
}





/**
 * Search BST for given key. Does there exist a key-value pair with given
 @@ -166,7 +199,6 @@ else if (cmp > 0)
 return x;
 }
 
 
 /**
 * Print all keys of the tree in a sequence, in-order. That is, for each
 * node, the keys in the left subtree should appear before the key in the
 @@ -203,8 +235,6 @@ private String printKeysInOrder(Node node)
 }
 }
 
 
 
 /**
 * Deteles a key from a tree (if the key is in the tree). Note that this
 * method works symmetrically from the Hibbard deletion: If the node to be
 @@ -259,21 +289,6 @@ private Node delMax(Node node)
 return node;
 }
 
 public int rank(Key key)
 {
 return rank(key, root);
 }
 
 private int rank(Key key, Node node)
 {
 int cmp = key.compareTo(node.key);
 
 if (cmp > 0)
 return 1 + rank(key, node.right) + size(node.left);
 else
 return size(node.left);
 }

