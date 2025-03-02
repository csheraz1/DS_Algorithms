/**
 * @param {number[]} prices
 * @return {number}
 */
var maxProfit = function(prices) {
    var position = new Map();
    var max = 0;
    for(var i = prices.length-1; i>=0; i--){
        position.set(prices[i], i)
        
    } 
    var x = 0
    for(var i = prices.length-1; i>0; i--){
        for(var j=0; j<prices[i]; j++){ 
            if(position.has(j)){ 
                
                if(i > position.get(j)){
                    if(prices[i] - j > max){max = prices[i] - j}
                }
            }
        }
    }
    return max
};