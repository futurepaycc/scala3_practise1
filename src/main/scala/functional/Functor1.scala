package functional
/*
函子实验: => map的泛化 => 感觉没啥用啊 => 实际上日常的操作都进行了lambda内联了
map泛化理解: 就是将普通的无副作用元素级的算子(op|expr|函数|映射)进行map化
 */


// ---------------------------------------------------------------------------------------------------- 函子实验1

//step1: 定义一个函子的形式 ( 这里使用类型的构造器 F[_] 来参数化构造器, List|Map 都是构造器 )
trait Functor[F[_]] {
  def functor_map[A, B](fa: F[A])(f: A => B): F[B]
}

//step2: 实现一个具体List型的函子
val listFunctor = new Functor[List] {
  // 高阶函数直接定义: 一阶:原集合, 二阶: 具体的态射
  // def map[A, B](as: List[A])(f: A => B): List[B] = as map f  //后面的map是输入上的
  def functor_map[A, B](as: List[A])(f: A => B): List[B] =
    // as.map(f) // 后面的map是输入上的
    // 使用生成式自定义map实现: https://docs.scala-lang.org/scala3/book/fun-write-map-function.html
    for x <- as yield f(x)
}

def test1 =
  val l1 = List(1, 2, 3)
  val res = listFunctor.functor_map(l1)(item => item.toString())
  println(res)



// ---------------------------------------------------------------------------------------------------- 函子实验2
// 两次类型转换, 这里的type应该是类型反射
// https://github.com/deanwampler/programming-scala-book-code-examples/blob/2.5.0/src/main/scala/progscala2/fp/categories/functor.scala
def custom_map[A, A2, B](func: A => A2)(f: A2 => B): A => B = { // <5>
  val functor = new Functor[({ type L[T] = A => T })#L] { // <6>
    def functor_map[A3, B2](func: A => A3)(f: A3 => B2): A => B2 = (a: A) =>
      f(func(a))
  }
  functor.functor_map(func)(f) // <7>
}

def test2 =
  // 两次类型转换的使用
  // https://github.com/deanwampler/programming-scala-book-code-examples/blob/2.5.0/src/main/scala/progscala2/fp/categories/functor.sc
  def int2int(item: Int): Int = item * 2
  def int2double(item: Int): Double = item * 2.1

  val cus_mapper = custom_map[Int, Int, Double](int2int)(int2double)
  val res2 = cus_mapper(2)
  println(res2)


@main def Functor1 =
  test1
  test2