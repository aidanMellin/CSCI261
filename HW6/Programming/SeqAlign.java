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

    public int OPT_D() { // Iterative
        int optCost = Integer.MAX_VALUE;

        for (int i = 1; i <= seqA.length(); i++)
            opt[i][0] = opt[i - 1][0] + gap;
        for (int j = 1; j <= seqB.length(); j++)
            opt[0][j] = opt[0][j - 1] + gap;

        for (int i = 1; i <= seqA.length(); i++) {
            for (int j = 1; j <= seqB.length(); j++) {
                int scoreDiag = opt[i - 1][j - 1] + (seqA.charAt(i - 1) == seqB.charAt(j - 1) ? match : // same symbol
                        (vowels.indexOf(seqA.charAt(i - 1)) == vowels.indexOf(seqB.charAt(j - 1))) ? alphaSemiMatch // Both
                                                                                                                    // vowels
                                                                                                                    // or
                                                                                                                    // consonants
                                : alphaNoMatch); // Different totally
                int scoreLeft = opt[i][j - 1] + gap;
                int scoreUp = opt[i - 1][j] + gap;
                // we take the minimum
                opt[i][j] = Math.min(Math.min(scoreDiag, scoreLeft), scoreUp);
                optCost = (opt[i][j] < optCost) ? opt[i][j] : optCost;
            }
        }
        return opt[seqA.length()][seqB.length()];
    }

    public int OPT_R() { // Recursive

        return opt[seqA.length()][seqB.length()];
    }

    public void printMatrix() {
        for (int i = seqA.length(); i >= 0; i--) {
            for (int j = 0; j <= seqB.length(); j++)
                System.out.print(opt[i][j] + "\t");
            System.out.println();
        }
    }

    public void showMatch() {
        int i = 0;
        int j = 0;
        String rA = "";
        String rB = "";
        while (i < seqA.length()) {
            int compare = opt[i + 1][j + 1];
            if (j >= seqB.length())
                break;
            if (compare - opt[i][j] != gap) {
                rA += seqA.charAt(i);
                rB += seqB.charAt(j);
                i++;
                j++;
            } else {
                rA += seqA.charAt(i);
                rB += " ";
                i++;
            }
        }
        System.out.println(rA);
        System.out.println(rB);
    }
}