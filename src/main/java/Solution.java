import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
//        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
//        System.out.println(Arrays.toString(removeDuplicates(nums)));
//        int[] prices = {5, 6, 4, 3, 3, 2, 6, 7, 8};
//        System.out.println(maxProfit2(prices));
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        rotate(nums, 3);
    }

    // 删除排序数组中的重复项
    public static int[] removeDuplicates(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] == nums[i - 1]) {
                // 后面的数组重新赋值
                for (int j = i; j < nums.length - 1; j++) {
                    nums[j] = nums[j + 1];
                }
                //数组缩容
                nums = Arrays.copyOf(nums, nums.length - 1);
            }
        }
        return nums;
    }

    // 买卖股票的最佳时机
    public static int maxProfit(int[] prices) {
//        int[] index = new int[prices.length];// 0:买 1：卖 2:其他（持有/观望）
        boolean isBuy = false;// 默认还没买
        int purchase = 0;// 买入总额
        int sale = 0;// 卖出总额
        for (int i = 0; i < prices.length; i++) {
            if (i < prices.length - 1) {
                if (prices[i] < prices[i + 1] && !isBuy) {
                    isBuy = true;
//                    index[i] = 0;
                    purchase += prices[i];
                } else if (prices[i] > prices[i + 1] && isBuy) {
                    isBuy = false;
//                    index[i] = 1;
                    sale += prices[i];
                }
//                else {
//                    index[i] = 2;
//                }
            } else {
                if (isBuy) {
                    isBuy = false;
//                    index[i] = 1;
                    sale += prices[i];
                }
            }
        }
        return sale - purchase;
    }

    // 买卖股票的最佳时机——累加利润就行
    public static int maxProfit2(int[] prices) {
        int profile = 0;
        for (int i = 0; i < prices.length; i++) {
            if (i < prices.length - 1 && prices[i] < prices[i + 1]) {
                profile += prices[i + 1] - prices[i];
            }
        }
        return profile;
    }

    // 旋转数组
    public static void rotate(int[] nums, int k) {
        int realPosition = k % nums.length;// 实际向右轮转的位置
        int[] newNums = Arrays.copyOf(nums, nums.length + realPosition);
        for (int i = newNums.length - 1; i >= 0; i--) {
            if (i > realPosition - 1) {
                newNums[i] = newNums[i - realPosition];
            } else {
                newNums[i] = newNums[i + nums.length];
            }
        }
        System.arraycopy(newNums, 0, nums, 0, nums.length);
    }

    // 存在重复元素
    public static boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int num : nums) {
            set.add(num);
        }
        return set.size() != nums.length;
    }
}
