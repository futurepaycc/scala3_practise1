/* 
Monoid初步:
* 是个集合 -> ∙ 群(只支持一种操作) =>环=>域 (抽像代数|近世代数)
* 支持连接
* 有单位元
 */

 /* 
 https://www.baeldung.com/scala/monoids-semigroups
 https://blog.csdn.net/qq_35835624/article/details/113453010
  */


trait Monoid[A]{
    def op(a1:A,a2:A):A

    def zero:A
}

val stringMonoid = new Monoid[String]{
    // 链接操作
    def op(a1: String, a2: String): String = a1 + a2
    // 单位元
    def zero: String = ""
}

@main def Monoid1 =
    val l1 = List("a","b","c","d")
    // 在List的foldLeft操作中，用一个初始元素从列表的左边元素开始操作，一直到对所有元素都操作完
    // val res = l1.foldLeft(stringMonoid.zero)(stringMonoid.op(_,_))
    val res = l1.foldLeft("")(stringMonoid.op(_,_))
    println(res)