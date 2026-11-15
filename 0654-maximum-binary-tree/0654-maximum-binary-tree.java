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

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        
        return build(nums, 0, nums.length-1);
    }

    private TreeNode build(int[] nums, int start, int end) {

        if(start > end) {
            return null;
        }
        int max = Integer.MIN_VALUE;
        int maxIdx = -1;
        for(int i=start; i <= end; i++){
            if(max < nums[i]){
                max = nums[i];
                maxIdx = i;
            }
        }
        TreeNode root = new TreeNode(max);

        root.left = build(nums, start, maxIdx-1);

        root.right = build(nums, maxIdx+1, end);

        return root;
    }
}