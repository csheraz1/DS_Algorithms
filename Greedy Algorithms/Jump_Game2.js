/**
 * @param {number[]} nums
 * @return {number}
 */
var jump = function(nums) { 
    var jumps = [nums.length]
    for(let i = 0; i < nums.length; i++){
        jumps[i] = 0
    } 
    var prevJump = 0
    if(nums.length == 1) return 0;
    for(let i = 0; i<nums.length; i++){ 
         
        prevJump = jumps[i];  
        if(jumps[i+nums[i]]==0 || i + nums[i]>nums.length-1){
             
            for(let j = i; j<=i+nums[i]; j++){
                if(jumps[j] == 0) jumps[j] = 1 + prevJump;
            }
        }
         
    }
    
    return jumps[jumps.length-1]
};