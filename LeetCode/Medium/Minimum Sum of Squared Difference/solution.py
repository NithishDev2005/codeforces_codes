from collections import Counter
from typing import List

class Solution:
    def minSumSquareDiff(self, nums1: List[int], nums2: List[int], k1: int, k2: int) -> int:
        k = k1 + k2
        diff_counts = Counter(abs(a - b) for a, b in zip(nums1, nums2))

        if sum(diff * count for diff, count in diff_counts.items()) <= k:
            return 0

        for diff in range(max(diff_counts), 0, -1):
            if k == 0:
                break
            
            count = diff_counts[diff]
            if not count:
                continue

            take = min(k, count)
            diff_counts[diff] -= take
            diff_counts[diff - 1] += take
            k -= take

        return sum(diff * diff * count for diff, count in diff_counts.items())