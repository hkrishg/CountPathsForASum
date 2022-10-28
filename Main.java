import java.util.*;

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int val) {
    this.val = val;
  }
};

class Maximum {
  int max_no = Integer.MIN_VALUE;
}

class Main {
  static Maximum max = new Maximum();
  // Problem Statement #
  // Given a binary tree and a number ‘S’, find all paths in the tree such that
  // the sum of all the node values of each path equals ‘S’. Please note that the
  // paths can start or end at any node but all paths must follow direction from
  // parent to child (top to bottom).

  private static int countPaths(TreeNode root, int sum) {
    List<Integer> currentPath = new ArrayList<>();
    return countPathsRecursive(root, sum, currentPath);

  }

  private static int countPathsRecursive(TreeNode currentNode, int sum, List<Integer> currentPath) {
    if (currentNode == null)
      return 0;
    int pathSum = 0, pathCount = 0;

    // add current node to path
    currentPath.add(currentNode.val);

    // find the sums of all sub paths of currentpath list
    ListIterator<Integer> pathIerator = currentPath.listIterator(currentPath.size());
    while (pathIerator.hasPrevious()) {
      pathSum += pathIerator.previous();
      if (pathSum == sum)
        pathCount++;
    }
    pathCount += countPathsRecursive(currentNode.left, sum, currentPath);
    pathCount += countPathsRecursive(currentNode.right, sum, currentPath);

    // remove the current node from the path to backtrack
    currentPath.remove(currentPath.size() - 1);
    return pathCount;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(12);
    root.left = new TreeNode(7);
    root.right = new TreeNode(1);
    root.left.left = new TreeNode(4);
    root.right.left = new TreeNode(10);
    root.right.right = new TreeNode(5);
    System.out.println(Main.countPaths(root, 11));
  }
}