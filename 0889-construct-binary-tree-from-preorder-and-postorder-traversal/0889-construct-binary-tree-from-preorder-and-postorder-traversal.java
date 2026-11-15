/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    int preIndex = 0;

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {

        return build(preorder, postorder, 0, preorder.length-1);
    }

    private TreeNode build(int[] preorder, int[] postorder, int left, int right) {
        if(preIndex >= preorder.length || left > right ) {
            return null;
        }

        //first element of preorder is root
        TreeNode root = new TreeNode(preorder[preIndex++]);

        //olny one node left
        if(left == right) {
            return root;
        }
        //next preorder element is left subtree root
        int leftRoot = preorder[preIndex];

        //find left subtree root in postorder
        int index = left;

        while(postorder[index] != leftRoot) {
            index++;
        }
        // Construct left subtree
        root.left = build(preorder, postorder, left, index);

        // Construct right subtree
        root.right = build(preorder, postorder, index + 1, right - 1);

        return root;
    }
}