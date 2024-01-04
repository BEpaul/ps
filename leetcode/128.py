class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        if not nums:
            return 0
        
        nums = list(set(nums))
        flag = [0] * len(nums)
        nums.sort()
        answer_list = []
        dict = {}
        for num in nums:
            dict[num] = 1

        for idx, num in enumerate(nums):
            count = 1
            while num+1 in dict and not flag[idx]:
                count += 1
                num += 1
                flag[idx] = 1
                idx += 1

            answer_list.append(count)
        
        return max(answer_list)