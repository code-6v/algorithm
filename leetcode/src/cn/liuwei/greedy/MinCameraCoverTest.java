package cn.liuwei.greedy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MinCameraCoverTest {

    /**
     * 给定一个二叉树，我们在树的节点上安装摄像头。
     * <p>
     * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
     * <p>
     * 计算监控树的所有节点所需的最小摄像头数量。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * <p>
     * 输入：[0,0,null,0,0]
     * 输出：1
     * 解释：如图所示，一台摄像头足以监控所有节点。
     * 示例 2：
     * <p>
     * <p>
     * <p>
     * 输入：[0,0,null,0,null,0,null,null,0]
     * 输出：2
     * 解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。
     * <p>
     * 提示：
     * <p>
     * 给定树的节点数的范围是 [1, 1000]。
     * 每个节点的值都是 0。
     */

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    @Test
    public void test1() {
        TreeNode treeNode1 = new TreeNode(0);
        TreeNode treeNode2 = new TreeNode(0);
        TreeNode treeNode3 = new TreeNode(0);
        TreeNode treeNode4 = new TreeNode(0);
        TreeNode treeNode5 = new TreeNode(0);
        TreeNode treeNode6 = new TreeNode(0);
        treeNode1.left = treeNode2;
        treeNode2.left = treeNode3;
        treeNode2.right = treeNode4;
        treeNode3.left = treeNode5;
        treeNode5.right = treeNode6;

        int count = minCameraCover(treeNode1);
        Assertions.assertEquals(2, count);
    }

    private int count = 0;


    public int minCameraCover(TreeNode root) {
        if (root.left == null && root.right == null) {
            return 1;
        }

        int val = traversal(root);

        return val == 0 ? count + 1 : count;
    }

    /**
     * 0 : 表示没有覆盖到
     * 1 : 覆盖到了，但是该节点没有摄像头
     * 2 : 覆盖到了，且该节点有摄像头
     */
    private int traversal(TreeNode root) {
        if (root == null) {
            // 不需要覆盖
            return 1;
        }

        int left = traversal(root.left);
        int right = traversal(root.right);
        // 左右节点有没有摄像头覆盖的，需要放置摄像头
        if (left == 0 || right == 0) {
            count++;
            return 2;
        }
        // 被覆盖了，左右节点有摄像头
        if (left == 2 || right == 2) {
            return 1;
        }
        // 左右节点都被覆盖了，其子节点没有摄像头
        return 0;
    }

}
