import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BinaryTree {
    TreeNode root;
    public BinaryTree(TreeNode root) {
        this.root = root;
    }

    //111. Minimum Depth of Binary Tree
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if(left == 0 || right == 0) return left + right + 1;
        return Math.min(left, right) + 1;
    }

    //222. Count Complete Tree Nodes
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    //366. Find Leaves of Binary Tree
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        while(root != null) {
            List<Integer> list = new ArrayList<>();
            root = dfs1(root, list);
            res.add(list);
        }
        return res;

    }
    public TreeNode dfs1(TreeNode node, List<Integer> list) {
        if(node == null) return null;
        if(node.left == null && node.right == null) {
            list.add(node.val);
            return null;
        }
        node.left = dfs1(node.left, list);
        node.right = dfs1(node.right, list);
        return node;
    }


    //515. Find Largest Value in Each Tree Row
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            while(size-- > 0) {
                TreeNode temp = queue.poll();
                max = Math.max(max, temp.val);
                if(temp.left != null) queue.offer(temp.left);
                if(temp.right != null) queue.offer(temp.right);
            }
            res.add(max);
        }
        return res;
    }

    //872. Leaf-Similar Trees
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        dfs(root1, list1);
        dfs(root2, list2);
        return list1.equals(list2);
    }

    public void dfs(TreeNode root, List<Integer> list) {
        if(root == null) return;
        if(root.left == null && root.right == null) list.add(root.val);
        dfs(root.left, list);
        dfs(root.right, list);
    }

    //1302. Deepest Leaves Sum
    public int deepestLeavesSum(TreeNode root) {
        int sum = 0;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            sum = 0;
            while(size-- > 0) {
                TreeNode temp = queue.poll();
                sum += temp.val;//calculate sum of each level, when while loop ends sum will be updated to the deepest level
                if(temp.left != null) queue.offer(temp.left);
                if(temp.right != null) queue.offer(temp.right);
            }
        }
        return sum;
    }







}
