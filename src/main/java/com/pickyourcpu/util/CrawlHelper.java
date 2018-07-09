package com.pickyourcpu.util;

public class CrawlHelper {

    private CrawlHelper() {
    }

    public static double computeMatchingDensity( String a, String b ) {
        int n = a.length();
        int m = b.length();

        int dp[][] = new int[n + 1][m + 1];

        for ( int i = 0; i < n; i++ ) {
            for ( int j = 0; j < m; j++ ) {
                if ( a.charAt( i ) == b.charAt( j ) ) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max( dp[i + 1][j], dp[i][j + 1] );
                }
            }
        }
        return (double) dp[n][m] / Math.min( n, m ) * 100;
    }
}
