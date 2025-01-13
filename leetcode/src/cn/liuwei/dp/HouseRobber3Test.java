package cn.liuwei.dp;

public class HouseRobber3Test {

    /**
     * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
     * <p>
     * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
     * <p>
     * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
     * <p>
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * <p>
     * <p>
     * 输入: root = [3,2,3,null,3,null,1]
     * 输出: 7
     * 解释: 小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7
     * 示例 2:
     * <p>
     * <p>
     * <p>
     * 输入: root = [3,4,5,1,3,null,1]
     * 输出: 9
     * 解释: 小偷一晚能够盗取的最高金额 4 + 5 = 9
     * <p>
     * <p>
     * 提示：
     * <p>
     * <p>
     * 树的节点数在 [1, 104] 范围内
     * 0 <= Node.val <= 104
     */

    public int rob(TreeNode root) {
        // 数组索引0的值对应不偷当前节点的最高金额，数组索引1的值对应偷当前节点的最高金额
        int[] rs = recursion(root);
        return Math.max(rs[0], rs[1]);
    }

    private int[] recursion(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }

        int[] leftRs = recursion(root.left);
        int[] rightRs = recursion(root.right);

        int robCur = root.val + leftRs[0] + rightRs[0];
        int noRobCur = Math.max(leftRs[0], leftRs[1]) + Math.max(rightRs[0], rightRs[1]);
        return new int[]{noRobCur, robCur};
    }

}
