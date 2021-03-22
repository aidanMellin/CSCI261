package git.CSCI261.HW4;

public class DisjHeight extends DisjSets {

    /*
     * performs unions based on tree height, always making the tree with less height
     * the child of the tree with greater height. If the trees have the same height,
     * the second tree becomes the child of the first. The root of a tree is the
     * negative of the tree height – 1.
     * 
     * @param n
     */
    DisjHeight(int n) {
        super(n);
        // TODO Auto-generated constructor stub
    }

}
