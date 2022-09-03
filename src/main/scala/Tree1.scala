// 二叉树
sealed trait BinTree[+A]
case object Leaf extends BinTree[Nothing]
case class Branch[A](value: A, left: BinTree[A], right: BinTree[A]) extends BinTree[A]

def buildTree[A](list: List[A]): BinTree[A] = list match {
  case Nil => Leaf
  case x :: xs => {
    val k = xs.length / 2
    Branch(x, buildTree(xs.take(k)), buildTree(xs.drop(k)))
  }
}

@main def Tree1={

  // 构建二叉树
  val list1 = (1 to 9).toList //range to list
  val tree1 = buildTree(list1)
  println(tree1)    

}