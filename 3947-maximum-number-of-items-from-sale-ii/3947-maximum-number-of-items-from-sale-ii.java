
class Solution {

    public int maximumSaleItems(int[][] items, int budget) {

        int n = items.length;

        int[] freq = new int[n + 1];

        int minPrice = Integer.MAX_VALUE;
        int maxFactor = 0;

        // Step 1: Calculate frequencies and minimum price
        for (int[] item : items) {

            int factor = item[0];
            int price = item[1];

            freq[factor]++;

            minPrice = Math.min(minPrice, price);
            maxFactor = Math.max(maxFactor, factor);
        }

        // Step 2: Calculate bonus for each factor
        int[] bonus = new int[maxFactor + 1];

        for (int f = 1; f <= maxFactor; f++) {

            for (int j = f; j <= maxFactor; j += f) {

                bonus[f] += freq[j];
            }

            if (bonus[f] > 0) {
                bonus[f]--;
            }
        }

        // Step 3: Sort items by price
        Arrays.sort(items, (a, b) ->
            Integer.compare(a[1], b[1])
        );

        long ans = 0;
        long remaining = budget;

        // Step 4: Greedily purchase items with bonuses
        for (int[] item : items) {

            int factor = item[0];
            int price = item[1];

            // No profitable bonus purchase remains
            if (2L * price >= 2L * minPrice * 2) {
                break;
            }

            int count = bonus[factor];

            if (count == 0) {
                continue;
            }

            long take = Math.min(count, remaining / price);

            ans += take * 2;

            remaining -= take * price;
        }

        // Step 5: Purchase cheapest individual items
        ans += remaining / minPrice;

        return (int) ans;
    }
}