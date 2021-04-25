public class SeqAlign {
    String seqA;
    String seqB;
    int gap;
    int alphaSemiMatch;
    int alphaNoMatch;
    int match = 0;
    int[][] opt;

    String vowels = "aeiouAEIOU";

    public SeqAlign(String X, String Y, int delta, int alphaSemiMatch, int alphaNoMatch) {
        this.gap = delta;
        this.alphaSemiMatch = alphaSemiMatch;
        this.alphaNoMatch = alphaNoMatch;
        this.seqA = X;
        this.seqB = Y;
        opt = new int[X.length() + 1][Y.length() + 1];
    }

    public int OPT_D() { // Recursive
        int optCost = Integer.MAX_VALUE;

        for (int i = 1; i <= seqA.length(); i++)
            opt[i][0] = opt[i - 1][0] + gap;
        for (int j = 1; j <= seqB.length(); j++)
            opt[0][j] = opt[0][j - 1] + gap;

        // (vowels.charAt(seqA.charAt(i - 1)) == vowels.charAt(seqA.charAt(j - 1)) ?
        // alphaSemiMatch : // Both vowels or consonants

        for (int i = 1; i <= seqA.length(); i++) {
            for (int j = 1; j <= seqB.length(); j++) {
                int scoreDiag = opt[i - 1][j - 1] + (seqA.charAt(i - 1) == seqB.charAt(j - 1) ? match : // same symbol
                        (vowels.indexOf(seqA.charAt(i - 1)) == vowels.indexOf(seqB.charAt(j - 1))) ? alphaSemiMatch
                                : alphaNoMatch); // different symbol
                int scoreLeft = opt[i][j - 1] + gap; // insertion
                int scoreUp = opt[i - 1][j] + gap; // deletion
                // we take the minimum
                opt[i][j] = Math.min(Math.min(scoreDiag, scoreLeft), scoreUp);
                optCost = (opt[i][j] < optCost) ? opt[i][j] : optCost;
            }
        }
        return opt[seqA.length()][seqB.length()];
    }

    public int OPT_R() { // Iterative

        return 0;
    }

    public void printMatrix() {
        for (int i = seqA.length(); i >= 0; i--) {
            for (int j = 0; j <= seqB.length(); j++)
                System.out.print(opt[i][j] + "\t");
            System.out.println();
        }
    }

    public void showMatch() {
        int j = 1;
        int i = 1;
        String s1 = "";
        String s2 = "";
        while (i < seqA.length()) {
            // Need to use opt scores to determine what mutations happened to the string
            // (starting from topright corner)
            int compare = opt[i][j] - opt[i - 1][j - 1];
            if (compare == match || compare == alphaSemiMatch || compare == alphaNoMatch) {
                s1 += seqA.charAt(i);
                s2 += seqB.charAt(j);
                i++;
                j++;
            } else if (compare == gap) {
                s1 += seqA.charAt(i);
                s2 += " ";
                i++;
            } else {
                System.out.println(compare);
                break;
            }
            // System.out.println(String.format("%c\t%c", seqA.charAt(i), seqB.charAt(j)));
        }
        System.out.println(s1);
        System.out.println(s2);
    }
}