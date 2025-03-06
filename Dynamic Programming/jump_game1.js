//Can be done with greedy which is optimal solution of O(n)
/**
 * @param {number[]} nums
 * @return {boolean}
 */
var canJump = function(nums) {
    // Tabulation array. Each entry at position i contains a boolean
    // that specifies whether end index is reachable
    let reach = new Array(nums.length); 
    for (let i=0; i<nums.length; i++) reach[i] = false;
    // Set last element to true
    reach[reach.length - 1] = true
    for(let i = nums.length-1; i>=0; i--){
        for(let j = 0; j <= nums[i]; j++){
            if(i+j >= nums.length){
                break;
            }
            if(reach[i+j]){
                reach[i] = true;
                break;
            }
        }
    }
    return reach[0]
};