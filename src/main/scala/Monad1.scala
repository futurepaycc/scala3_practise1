// https://github.com/fpinscala/fpinscala/blob/second-edition/src/main/scala/fpinscala/answers/monads/Monad.scala

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

@main def Monad1 =
  var res = Id("hello, ").flatMap(a => Id("monad!").flatMap(b => Id(a + b)))
  println(res)

  res = for {
    a <- Id("hello, ")
    b <- Id("monad!") //这里用到map函数
  } yield a + b

  println(res)