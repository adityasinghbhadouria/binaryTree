import java.util.*;
public class BinaryTreeUse {


	public static BinaryTreeNode<Integer>takeTreeInputBetter(boolean isRoot,int parentData,boolean isLeft){
		if(isRoot) {
			System.out.println("Enter root data");
		}else {
			if(isLeft) {
				System.out.println("Enter Left child of " + parentData);
			}else {
				System.out.println("Enter Right child of " + parentData);
			}
		}
		Scanner sc = new Scanner(System.in);
		int rootData=sc.nextInt();

		if(rootData==-1) {
			return null;
		}

		BinaryTreeNode<Integer>root=new BinaryTreeNode<Integer>(rootData);             

		BinaryTreeNode<Integer>leftChild=takeTreeInputBetter(false,rootData,true);
		BinaryTreeNode<Integer>rightChild=takeTreeInputBetter(false,rootData,false);                                                          
		root.left=leftChild;
		root.right=rightChild;
		return root;
	}

	public static BinaryTreeNode<Integer>takeTreeInput(){
		System.out.println("Enter root data");
		Scanner sc = new Scanner(System.in);
		int rootData=sc.nextInt();

		if(rootData==-1) {
			return null;
		}

		BinaryTreeNode<Integer>root=new BinaryTreeNode<Integer>(rootData);             

		BinaryTreeNode<Integer>leftChild=takeTreeInput();
		BinaryTreeNode<Integer>rightChild=takeTreeInput();
		root.left=leftChild;
		root.right=rightChild;
		return root;
	}
	// this is only print tree detailed below
	public static <T> void printTree1(BinaryTreeNode <T> root ) {
		System.out.print(root.data + " : " );
		if(root.left!=null) {
			System.out.print(" L" + root.left.data );
		}
		if(root.right!=null) {
			System.out.print(" ," + " R" + root.right.data);
		}
		System.out.println();
		// BASE CASE
		if(root.left!=null) {
			printTree1(root.left);
		} 
		if(root.right!=null) {
			printTree1(root.right);
		} 
	}

	public static <T> void printTree2(BinaryTreeNode <T> root ) {
		if(root==null) {
			return;
		}
		System.out.println(root.data);
		printTree1(root.left);
		printTree1(root.right);
	}

	public static BinaryTreeNode<Integer>insert(BinaryTreeNode<Integer>node,int x){
		if(node==null) {
			BinaryTreeNode<Integer>newroot=new BinaryTreeNode<Integer>(x);
			return newroot;
		}
		if(x >= node.data) {
			node.right=insert(node.right, x);
		}else {
			node.left=insert(node.left,x);
		}
		return node;

	}

	public static int numNodes(BinaryTreeNode<Integer>root) {
		if(root==null) {
			return 0;
		}
		int leftNodeCount = numNodes(root.left);
		int rightNodeCount= numNodes(root.right);
		return 1+leftNodeCount+rightNodeCount;
	}

	public static int largestData(BinaryTreeNode<Integer>root) {

		if(root==null) {
			return -1;
		}
		int largestLeft=largestData(root.left);
		int largestRight=largestData(root.right);
		return Math.max(root.data, Math.max(largestLeft, largestRight));

	}

	public static int height(BinaryTreeNode<Integer>root) {
		if(root==null) {
			return 0;
		}
		return Math.max(height(root.left), height(root.right))+1;
	}

	public static int numLeaves(BinaryTreeNode<Integer>root) {
		if(root==null) {
			return 0;
		}
		if (root.left==null&&root.right==null) {
			return 1;
		}
		return numLeaves(root.left)+ numLeaves(root.right);
	}

	public static void printAtDepthK(BinaryTreeNode<Integer>root,int k) {
		if(root==null) {
			return;
		}
		if(k==0){
			System.out.println(root.data);
			return;
		}
		printAtDepthK(root.left, k-1);	
		printAtDepthK(root.right,k-1);

	}

	public static BinaryTreeNode<Integer>removeLeaves(BinaryTreeNode<Integer>root){
		if(root==null) {
			return null;
		}
		if(root.left==null&&root.right==null) {
			return null;
		}
		root.left=removeLeaves(root.left);
		root.right=removeLeaves(root.right);
		return root;
	}

	public static boolean isBalanced (BinaryTreeNode<Integer>root) {
		if(root==null) {
			return true;
		}
		int leftHeight=height(root.left);
		int rightHeight=height(root.right);
		if(leftHeight-rightHeight>1||leftHeight-rightHeight<0) {
			return false;
		}
		boolean isLeftBlanced=isBalanced(root.left);
		boolean isRightBalanced=isBalanced(root.right);
		return isLeftBlanced&&isRightBalanced;
	}

	public static BalancedTreeReturn isBalancedBetter (BinaryTreeNode<Integer>root) {

		if(root==null) {
			int height=0;
			boolean isBalanced=true;
			BalancedTreeReturn ans=new BalancedTreeReturn();
			ans.height=height;
			ans.isBalanced=isBalanced;
			return ans;
		}
		BalancedTreeReturn leftOutput=isBalancedBetter(root.left);
		BalancedTreeReturn rightOutput=isBalancedBetter(root.right);
		boolean isBal=true;
		int height = 1+Math.max(leftOutput.height, rightOutput.height);
		if(leftOutput.height-rightOutput.height>1||leftOutput.height-rightOutput.height<0) {
			isBal=false;
		}
		if(!leftOutput.isBalanced||!rightOutput.isBalanced) {
			isBal=false;
		}

		BalancedTreeReturn ans=new BalancedTreeReturn();
		ans.height=height;
		ans.isBalanced=isBal;
		return ans;
	}

	public static BinaryTreeNode<Integer>takeTreeInputlevelWise(){
		Scanner sc=new Scanner (System.in);
		System.out.println(" Enter root data ");
		int rootData=sc.nextInt();
		if(rootData==-1) {
			return null;
		}
		BinaryTreeNode<Integer>root=new BinaryTreeNode<Integer>(rootData);
		Queue<BinaryTreeNode<Integer>>pendingChildren=new LinkedList<BinaryTreeNode<Integer>>();
		pendingChildren.add(root);

		while(!pendingChildren.isEmpty()) {
			BinaryTreeNode<Integer>front=pendingChildren.poll();
			//for left
			System.out.println("Enter left child of " + front.data);
			int left=sc.nextInt();
			if(left!=-1) {
				BinaryTreeNode<Integer>leftChild=new BinaryTreeNode<Integer>(left);
				front.left=leftChild;
				pendingChildren.add(leftChild);
			}

			//for right
			System.out.println("Enter right child of " + front.data);
			int right=sc.nextInt();
			if(right!=-1) {
				BinaryTreeNode<Integer>rightChild=new BinaryTreeNode<Integer>(right);
				front.right=rightChild;
				pendingChildren.add(rightChild);
			}
		}
		return root;

	}

	public static void printTreeLevelWise(BinaryTreeNode<Integer>root) {

		Queue<BinaryTreeNode<Integer>> pendingNodes=new LinkedList<>();
		pendingNodes.add(root);

		while(!pendingNodes.isEmpty()) {
			BinaryTreeNode<Integer> frontNode=pendingNodes.poll();

			if(frontNode==null) {
				System.out.println();

				if(!pendingNodes.isEmpty()) {
					pendingNodes.add(null);
				}
			}else {
				System.out.print(frontNode.data + ":L: ");

				if(frontNode.left!=null) {
					pendingNodes.add(frontNode.left);
					System.out.print(frontNode.left.data + ", R: ");
				}else {
					System.out.print(-1 + ", R: ");
				}
				if(frontNode.right!=null) {
					pendingNodes.add(frontNode.right);
					System.out.println(frontNode.right.data);
				}else {
					System.out.println(-1);
				}				
			}
		}
	}

	public static BinaryTreeNode<Integer>buildTreeFromPreInHelper(int[]pre,int[]in,int siPre,int eiPre,int siIn,int eiIn){
		if(siPre>eiPre) {
			return null;
		}
		int rootData = pre[siPre];
		BinaryTreeNode<Integer>root=new BinaryTreeNode<Integer>(rootData);

		int rootIndex=-1;
		for(int i =siIn;i<=eiIn;i++) {
			if(in[i]==rootData) {
				rootIndex=i;
				break;
			}			
		}


		int siPreLeft= siPre+1;
		int siInLeft = siIn   ;	
		int eiInLeft =rootIndex-1 ;
		int siInRight = rootIndex+1 ;
		int eiPreRight = eiPre;
		int eiInRight = eiIn;

		int leftInSubtreelength=((eiInLeft-siInLeft)+1);

		int eiPreLeft = ((siPreLeft+leftInSubtreelength)-1);
		int siPreRight = eiPreLeft+1;


		BinaryTreeNode<Integer> left=buildTreeFromPreInHelper(pre, in, siPreLeft, eiPreLeft, siInLeft, eiInLeft);
		BinaryTreeNode<Integer> right=buildTreeFromPreInHelper(pre, in, siPreRight, eiPreRight, siInRight, eiInRight);
		root.left=left;
		root.right=right;
		return root;
	}
	public static BinaryTreeNode<Integer> buildTreeFromPreIn(int[]pre,int[]in){                                                                                              

		BinaryTreeNode<Integer>root=buildTreeFromPreInHelper(pre, in, 0, pre.length-1, 0, in.length-1);

		return root;

	}

	public static boolean searchBST(BinaryTreeNode<Integer>root,int data) {
		if(root==null) {
			return false;
		}
		if(root.data==data) {
			return true;
		}
		if(root.data>data) {
			return searchBST(root.left, data);
		}
		return searchBST(root.right, data);
	}

	public static void printBetweenK1K2(BinaryTreeNode<Integer>root,int k1,int k2) {
		if(root==null) {
			return;
		}
		if(root.data<k1) {
			printBetweenK1K2(root.right, k1, k2);
		}
		else if(root.data>k2) {
			printBetweenK1K2(root.left, k1, k2);
		}
		else {
			System.out.println(root.data);
			printBetweenK1K2(root.left, k1, k2);
			printBetweenK1K2(root.right, k1, k2);

		}

	}

	public static BinaryTreeNode<Integer>arrayToBSTHelper(int[]arr,int si,int ei){
		if(si>ei) {
			return null;
		}
		int mi=si+(ei-si)/2;

		BinaryTreeNode<Integer>root=new BinaryTreeNode<Integer>(arr[mi]);

		root.left=arrayToBSTHelper(arr, si, mi-1);
		root.right=arrayToBSTHelper(arr, mi+1, ei);

		return root;
	}
	public static BinaryTreeNode<Integer>arrayToBST(int[]arr,int n){
		return arrayToBSTHelper(arr, 0, n-1);

	}

	public static int minimum(BinaryTreeNode<Integer>root) {
		if(root==null) {
			return Integer.MAX_VALUE; 
		}
		int leftMin=minimum(root.left);
		int rightMin=minimum(root.right);
		return Math.min(root.data, Math.min(leftMin, rightMin));
		//because of this line when it hits the null then it return the root
		//data as we asked to return which ever is lower 
	}

	public static boolean isBST(BinaryTreeNode<Integer>root) {
		if(root==null) {
			return true;
		}
		int leftMax=largestData(root.left);
		if(leftMax>=root.data) {
			return false;
		}

		int rightMax=minimum(root.right);
		if(rightMax<root.data) {
			return false;
		}

		boolean isLeftBST=isBST(root.left);
		boolean isRightBST=isBST(root.right);

		return isLeftBST&&isRightBST;

	}

	public static isBSTReturn isBST2(BinaryTreeNode<Integer>root) {

		if(root==null) {
			isBSTReturn ans=new isBSTReturn(Integer.MAX_VALUE,Integer.MIN_VALUE,true);
			return ans;
		}

		isBSTReturn leftAns=isBST2(root.left);
		isBSTReturn rightAns=isBST2(root.right);

		int min=Math.min(root.data, Math.min(leftAns.min, rightAns.min));	

		int max=Math.max(root.data, Math.max(leftAns.max, rightAns.max));	

		boolean isBST=true;

		if(leftAns.max>=root.data) {
			isBST=false;
		}
		if(rightAns.min<root.data) {
			isBST=false;
		}
		if(!leftAns.isBST) {
			isBST=false;
		}
		if(!rightAns.isBST) {
			isBST=false;
		}
		isBSTReturn ans=new isBSTReturn(min,max,isBST);
		return ans;
	}

	public static boolean isBST3(BinaryTreeNode<Integer>root,int minRange,int maxRange) {
		if(root==null) {
			return true;
		}
		if(root.data<minRange||root.data>maxRange) {
			return false; 
		}
		boolean isLeftWithinRange=isBST3(root.left,minRange,root.data-1);
		boolean isRightWithinRange=isBST3(root.right,root.data+1,maxRange);

		return isLeftWithinRange&&isRightWithinRange;
	}

	public static ArrayList<Integer>nodeToRootPath(BinaryTreeNode<Integer>root,int x){
		if(root==null) {
			return null;
		}

		if(root.data==x) {
			ArrayList<Integer>output=new ArrayList<>();
			output.add(root.data);
			return output;
		}

		ArrayList<Integer>leftOutput=nodeToRootPath(root.left,x);
		if(leftOutput!=null) {
			leftOutput.add(root.data);
			return leftOutput;
		}

		ArrayList<Integer>rightOutput=nodeToRootPath(root.right, x);
		if(rightOutput!=null) {
			rightOutput.add(root.data);
			return rightOutput;
		}

		return null;
	}


	
	

	public static void main(String[] args) {


		//		BinaryTreeNode<Integer>root = new BinaryTreeNode<Integer>(1);
		//		BinaryTreeNode<Integer>rootLeft = new BinaryTreeNode<Integer>(2);                   
		//		root.left=rootLeft;
		//		BinaryTreeNode<Integer>rootRight = new BinaryTreeNode<Integer>(3);
		//		root.right=rootRight;
		//		BinaryTreeNode<Integer>twoLeft = new BinaryTreeNode<Integer>(4);
		//		rootLeft.left=twoLeft;
		//		BinaryTreeNode<Integer>twoRight = new BinaryTreeNode<Integer>(5);
		//		rootLeft.right=twoRight;
		//		BinaryTreeNode<Integer>threeLeft = new BinaryTreeNode<Integer>(6);            
		//		rootRight.left=threeLeft;
		//
		//		//	root.right.left=threeLeft;
		//
		//		printTree1(root);
		//		System.out.println();
		//		System.out.println();
		//		//	printTree2(root);

		//BinaryTreeNode<Integer>root = takeTreeInputBetter(true,0,true);
		//BinaryTreeNode<Integer>root = takeTreeInputlevelWise();

		//printTreeLevelWise(root);
		//System.out.println(isBalancedBetter(root).isBalanced);

		//		int in[]= {1,2,3,4,50,6,7};
		//		int pre[]= {4,2,1,3,6,50,7};
		//		BinaryTreeNode<Integer>root=buildTreeFromPreIn(pre, in);
		//		printTree1(root);
		//		//System.out.println(searchBST(root, 30));
		//
//		int[]arr= {15 , 10 , 5 , 9 , 18 , 29 , 3 , 2};
//						  		
//		Arrays.sort(arr);
//		BinaryTreeNode<Integer>root=arrayToBST(arr, arr.length);
//		printTree1(root);
		//		//System.out.println(isBST(root));
		//		isBSTReturn ans=isBST2(root);
		//		System.out.println(ans.min+" "+ans.max+" "+ans.isBST);
		//		System.out.println(isBST3(root,Integer.MIN_VALUE,Integer.MAX_VALUE));
		//		BinaryTreeNode<Integer>root=takeTreeInputBetter(true, 0, true);
		//		ArrayList<Integer>path=nodeToRootPath(root, 5);
		//		for(int i:path) {
		//			System.out.println(i);
		//		}
		//		int arr[]= {15 , 10 , 5 , 9 , 18 , 29 , 3 , 2};
		//		BinaryTreeNode<Integer>root=arrayToBST(arr, arr.length);
		//		printTreeLevelWise(root);
	} 
}























