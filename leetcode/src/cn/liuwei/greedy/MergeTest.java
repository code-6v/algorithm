package cn.liuwei.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeTest {

    /**
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
     *
     *
     *
     * 示例 1：
     *
     * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     * 示例 2：
     *
     * 输入：intervals = [[1,4],[4,5]]
     * 输出：[[1,5]]
     * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
     *
     *
     * 提示：
     *
     * 1 <= intervals.length <= 104
     * intervals[i].length == 2
     * 0 <= starti <= endi <= 104
     */

    public int[][] merge(int[][] intervals) {
        // 根据区间的开始值正序排序
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));
        // 遍历数组，把有交集的区间合并
        int[] prev = intervals[0];
        List<List<Integer>> merged = new ArrayList<>();
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            if (prev[1] >= cur[0]) {
                // 有交集
                prev[1] = Math.max(prev[1], cur[1]);
            } else {
                merged.add(Arrays.asList(prev[0], prev[1]));
                prev = cur;
            }
        }
        // 记录最后一段
        merged.add(Arrays.asList(prev[0], prev[1]));
        return merged.stream().map(item -> item.stream().mapToInt(Integer::intValue).toArray()).toArray(int[][]::new);
    }

}
