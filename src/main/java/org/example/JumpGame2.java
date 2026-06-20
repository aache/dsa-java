package org.example;

public class JumpGame2 {
    public static void main(String[] args) {
        int result = new JumpGame2().jump(new int[] {2,4,1,2,3,1,1,2});
        System.out.println(result);
    }

    public int jump(int[] nums) {
       int range = 0;
       int lastJumpIndex = 0;
       int totalJumps = 0;

       if(nums == null || nums.length <= 1){
           return 0;
       }

       for(int i = 0; i < nums.length; i++){
           range = Math.max(range, i + nums[i]);

           if(i == lastJumpIndex){
               lastJumpIndex = range;
               totalJumps++;

               if(range >= nums.length - 1){
                   break;
               }
           }
       }
       return totalJumps;
    }
}
