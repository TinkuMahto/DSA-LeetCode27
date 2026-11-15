class Solution {

    int  mod = 1_000_000_007;

    public int numOfSubarrays(int[] arr) {

        int prefixSum = arr[0];

        for(int i=1; i < arr.length; i++) {
            prefixSum = prefixSum + arr[i]; 
            arr[i] = prefixSum; 
        }

        int evenCount = 1;
        int oddCount = 0;
        int count = 0;

        for(int i=0; i<arr.length; i++) {
            if(arr[i] % 2 == 0) {
                count = (count + oddCount) % mod;
                evenCount++;
            }else{
                count = (count + evenCount) % mod;
                oddCount++;
            }
        }
        return count;
    }
}