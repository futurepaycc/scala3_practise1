// 二叉树
sealed trait BinTree[+A]
case object Leaf extends BinTree[Nothing]
case class  Branch[A](value: A, left: BinTree[A], right: BinTree[A]) extends BinTree[A]

// 等号右边都已在函数内(也可看作一个大号的表达式)
def buildTree[A](list: List[A]): BinTree[A] = list match {
  case Nil => Leaf
  case x :: xs => {
    val k = xs.length / 2
    Branch(x, buildTree(xs.take(k)), buildTree(xs.drop(k)))
  }
}

@main def Tree1={

  // 构建二叉树, 从左到右，自顶向下
  val list1 = (1 to 9).toList //range to list
  val tree1 = buildTree(list1)
  println(tree1)    

}