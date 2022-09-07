// https://blog.csdn.net/shudaqi2010/article/details/113401577

package functional

import scala.util.{Try, Success, Failure}

// 1. Option测试, None和Some都是Option的子类
private def divide1(a:Double,b:Double):Option[Double] = 
  if (b == 0.0)
    None
  else
    Some(a/b)

// 2. Try测试
private def divide2(a:Double,b:Double):Try[Double] = 
  Try(a/b)

// 3. Either测试
private def divide3(a:Double,b:Double):Either[String,Double] = 
  if (b == 0.0) Left("出现了除0操作")
  else Right(a/b)


@main def Option1 = 
  var res = divide1(3.0,2.0)
  println(res)

  res = divide1(1.0, 0.0)
  println(res)

  // scala3 除0开始返回: Infinity 
  var res2 = divide2(2.0,0.0)
  res2 match
    case Success(value) => println(s"Success value = $value")
    case Failure(value) => println(s"Failure value = $value")
  println(res2.get > 0)

  divide3(3.0,0.0) match
    case Left(value) => println(s"Left value = $value")
    case Right(value) => println(s"Right value = $value")
  divide3(3.0,1.0) match
    case Left(value) => println(s"Left value = $value")
    case Right(value) => println(s"Right value = $value")
  