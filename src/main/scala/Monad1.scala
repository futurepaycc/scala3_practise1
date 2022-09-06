// https://github.com/fpinscala/fpinscala/blob/second-edition/src/main/scala/fpinscala/answers/monads/Monad.scala
// ------------------------------------------------------------------------------------- 测试1
/* 
一个啥也没干的Monad
尝试理解下吧:
  * 面向类型角度理解
  * 假设原来的String是一个自定义类型，但满足了连接接语义，但没有map|flatmap能力
  * Id类型为实现了map|floatMap语义的类型
  * 结果: 组合String '类型' 的元素级concat能力 和Id '类型' 的map能力
*/

case class Id[+A](value: A):

  def map[B](f: A => B): Id[B] =
    Id(f(value))

  def flatMap[B](f: A => Id[B]): Id[B] =
    f(value)

def test1 =
  var res = Id("hello, ").flatMap(a => Id("monad!").flatMap(b => Id(a + b)))
  println(res)

  res = for {
    a <- Id("hello, ")
    b <- Id("monad!") //这里用到map函数
  } yield a + b

  println(res)



// https://github.com/deanwampler/programming-scala-book-code-examples/blob/release-2.1.0/src/main/scala/progscala2/fp/categories/monad.scala
// ------------------------------------------------------------------------------------- 测试2
/* 
理解: 说Functor是Map的泛化, Monad是flatMap的泛化!!!!!!!!!!

具体的东西: optional就是一个 幺元, 同范畴与哪个元素运算还是那个元素

// https://www.jdon.com/idea/monad.html (Jdon: Monoid是元素对象的组合的范畴, 那么Monad是自函子的组合范畴, 自函子可以理解为映射范畴C到另外一个范畴C???? 这你妹不像人话啊)
// https://www.ruanyifeng.com/blog/2015/07/monad.html (阮一峰翻译的图解，说: Monad就是一种设计模式，表示将一个运算过程，通过函数拆解成互相连接的多个步骤)
我们需要的是这样一种函数：它的输入和输出都是数据类型。
  * 数据类型就是对值的一种封装，不仅包括值本身，还包括相关的属性和方法。
  * 只能在数据类型的场景（context）中使用
  * 因为数据类型是带有运算方法的，如果每一步返回的都是数据类型的实例，我们就可以把它们连接起来。
  
*/

trait Monad[M[_]] {                                                // <1>
  def flatMap[A, B](fa: M[A])(f: A => M[B]): M[B]                  // <2>
  def unit[A](a: => A): M[A]                                       // <3>
}

object SeqM extends Monad[Seq] {
  def flatMap[A, B](seq: Seq[A])(f: A => Seq[B]): Seq[B] = seq flatMap f
  def unit[A](a: => A): Seq[A] = Seq(a)
}

object OptionM extends Monad[Option] {
  def flatMap[A, B](opt: Option[A])(f: A => Option[B]):Option[B]= opt flatMap f
  def unit[A](a: => A): Option[A] = Option(a)
}

def test2 = 
  val fn_range: Int => Seq[Int] = i => 1 to i
  val fn_option: Int => Option[Int] = i => Option(i + 1)

  var res1 = SeqM.flatMap(List(1,2,3))(fn_range)             // Seq[Int]: List(1,1,2,1,2,3)
  println(res1)
  res1 = SeqM.flatMap(List.empty[Int])(fn_range)         // Seq[Int]: List()
  println(res1)

  var res2 = OptionM.flatMap(Some(2))(fn_option)              // Option[Int]: Some(3)
  println(res2)
  res2 = OptionM.flatMap(Option.empty[Int])(fn_option)    // Option[Int]: None  
  println(res2)

@main def Monad1 = 
  test2