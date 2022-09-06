/*
范畴实验:
 * 不光是群还有一组态射
 */

// 这里使用类型的构造器 F[_] 来参数化构造器, List|Map 都是构造器
trait Functor[F[_]] {
  def functor_map[A, B](fa: F[A])(f: A => B): F[B]
}

val listFunctor = new Functor[List] {
  // 高阶函数直接定义: 一阶:原集合, 二阶: 具体的态射
  // def map[A, B](as: List[A])(f: A => B): List[B] = as map f  //后面的map是输入上的
  def functor_map[A, B](as: List[A])(f: A => B): List[B] =
    as.map(f) // 后面的map是输入上的
    // FIXME 手工实现map失败
    // var res = new List[B]()
    // for (item <- as)
    //     res.appended(item.toString())
    // res

    // 看源码都学不会
    // var res = new ::[B](as.head,Nil)
    // for (item <- as)
    //     res.appended(item.toString())
    // res
}

// 两次类型转换
// https://github.com/deanwampler/programming-scala-book-code-examples/blob/2.5.0/src/main/scala/progscala2/fp/categories/functor.scala
def custom_map[A, A2, B](func: A => A2)(f: A2 => B): A => B = { // <5>
  val functor = new Functor[({ type L[T] = A => T })#L] { // <6>
    def functor_map[A3, B2](func: A => A3)(f: A3 => B2): A => B2 = (a: A) =>
      f(func(a))
  }
  functor.functor_map(func)(f) // <7>
}


@main def Functor1 =
  val l1 = List(1, 2, 3)
  val res = listFunctor.functor_map(l1)(item => item.toString())
  println(res)

  // 两次类型转换的使用
  // https://github.com/deanwampler/programming-scala-book-code-examples/blob/2.5.0/src/main/scala/progscala2/fp/categories/functor.sc
  def int2int(item:Int):Int = item * 2
  def int2double(item:Int):Double = item * 2.1

  val cus_mapper = custom_map[Int,Int,Double]( int2int )( int2double )
  val res2 = cus_mapper(2)
  println(res2)
