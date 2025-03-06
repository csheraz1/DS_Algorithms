/**
 * @param {number[]} height
 * @return {number}
 */
var maxArea = function(height) {
    var pointerLeft = 0
    var pointerRight = height.length - 1
    var maxWater = 0
    var water = 0
    while(pointerLeft <= pointerRight){
        water = Math.min(height[pointerLeft], 
        height[pointerRight])*(pointerRight-pointerLeft)
        if(water > maxWater) maxWater = water;
        (height[pointerLeft] < height[pointerRight]) ? 
        (pointerLeft++) : (pointerRight--)
    }
    
    return maxWater
};