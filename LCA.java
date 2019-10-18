public class LCA <Key extends Comparable<Key>, Value>{


	Node root;             				

	class Node {
		private Key key;           				
		private Value val;         				
		private Node left, right; 				
		private int N;             				

		public Node(Key key, Value val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
		}

	}
	
	class DAG{
		private Node root;
		public DAG(Node root){
			this.root = root;
		}
	}
	
	public int size() { 
		return size(root); 
	}
	
	public boolean isEmpty() { 
		return size() == 0; 
	}
	private int size(Node x) {
		if (x == null) return 0;

		//N = #nodes
		else return x.N;
	}

	
	public boolean contains(Key key) {
		return get(key) != null;
	}

	public Value get(Key key) { 
		return get(root, key); 
	}

	private Value get(Node node, Key key) {
		if (node == null) return null;
		int cmp = key.compareTo(node.key);
		if      (cmp < 0) return get(node.left, key);
		else if (cmp > 0) return get(node.right, key);
		else return node.val;
		
	}

	/**
	 *  Insert key-value pair into BST.
	 *  If key already exists, update with new value.
	 */
	public void put(Key key, Value val) {
		if (val == null) { delete(key); return; }
		root = put(root, key, val);
	}

	private Node put(Node node, Key key, Value val) {
		if (node == null) return new Node(key, val, 1); //new bst
		int cmp = key.compareTo(node.key);
		if      (cmp < 0) node.left  = put(node.left,  key, val);
		else if (cmp > 0) node.right = put(node.right, key, val);
		else              node.val   = val; //updating value
		node.N = 1 + size(node.left) + size(node.right); // value child1 + value child2 + 1
		return node;
	}

	/**
	 * Tree height.
	 *
	 * worst case O(N): either all the nodes are to right or all the nods are to the left
	 * (N is the number of nodes in the BST)
	 */

	public int height() { 
		return height(root); 
	}


	private int height(Node x) {
		if (x == null) {
			return -1;
		}
		else {
			return 1 + Math.max(height(x.left), height(x.right));
		}
	}

	public Key median() {
		if (isEmpty()) return null; //returning null if the bst is empty

		else {
			int median=(((size(root)+1)/2)-1);   //add 1 to size(root)	
			//need to change int to key?
			return intToKey(median);
		}
	}

	private Key intToKey(int passedInt) {                       
		Node node = intToKey(root, passedInt);
		return node.key;
	}
	//find the node with equivalent key given passedInt
	private Node intToKey(Node node, int passedInt) {     
		int leftSize = (size(node.left));  
		
		//check is it in the left or right subtree
		if (leftSize > passedInt) {
			return intToKey(node.left,  passedInt); 
		}
		else if (leftSize < passedInt) {
			return intToKey(node.right, passedInt-leftSize-1); 
		}
		else {
			return node; 
		}
	} 

	public String printKeysInOrder() {
		String res= "";
		if (isEmpty()){
			return res += "()";
		}
		else {
			return res = printKeysInOrder(root);
		}
	
	}
	
	 private String printKeysInOrder(Node node) {
		 String res = "";
		 if (node == null) {
			 return res += "()";
		 }
		 
		 else {
			return res += ("(" + printKeysInOrder(node.left) + node.key.toString() + printKeysInOrder(node.right) + ")");
		 }
		 
	 }
	public String prettyPrintKeys() {
		if(isEmpty()) return "-null\n";
	     return prettyPrint(root,"");
	}
	
	private String prettyPrint(Node node, String prefix) {
		if (node == null) {
			return (prefix + "-null\n");
		}
		else {
			
			return (prefix+"-"+node.key.toString()+"\n" +prettyPrint(node.left,(prefix+" |"))+ prettyPrint(node.right,(prefix+"  ")));
    	}
		
	}

	public void delete(Key key) {
		root = delete(root, key);	
	}
	
	private Node delete (Node node, Key key) {
		if (node == null) { 
			return null;
		}
		
        int compare = key.compareTo(node.key);
        
        if   (compare > 0) {
        	node.right = delete(node.right, key);
        	node.left  = delete(node.left,  key);
        }
        else if (compare < 0) {
        	node.left  = delete(node.left,  key);
        }
        else {
        	if (node.right == null) {
        		return node.left;
        	}
            if (node.left  == null) {
            	return node.right;
            }
            Node temp = node;
            node = max(temp.left);                              
            node.left = deleteMax(temp.left);                 
            node.right = temp.right; 
        }
        
        node.N = size(node.left) + size(node.right) + 1;
        return node;
		
	}
	
	private Node deleteMax(Node node) 
    {
        if (node.right == null) return node.left;
        node.right = deleteMax(node.right);
        node.N = size(node.left) + size(node.right) + 1;                                 
        return node;
    }
	
	public Node max(Node node)
	   {
	     if(node.right!=null) {
	       return max(node.right);
	     }
	     return node;
	   }
	
	 	public Key lowestCommonAncestor (Node node, Key key1, Key key2){
	 		if (node == null)
	             return null;
	 		if (node.key == key1) {
	 			return node.key;
	 		}
	 		if (node.key == key2) {
	 			return node.key;
	 		}
	 		int cmp1 = node.key.compareTo(key1);
	 		int cmp2 = node.key.compareTo(key2);
	 		
	         if (cmp1 >= 0 && cmp2 >= 0)
	             return lowestCommonAncestor(node.left, key1, key2);
	   
	         if (cmp1 <= 0 && cmp2 <= 0)
	             return lowestCommonAncestor(node.right, key1, key2);
	   
	         return node.key;
	 	}

	
}