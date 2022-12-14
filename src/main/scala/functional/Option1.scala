// https://blog.csdn.net/shudaqi2010/article/details/113401577

package functional

import scala.util.{Try, Success, Failure}

// 1. Option测试, None和Some都是Option的子类
private def divide1(a:Double,b:Double):Option[Double] = 
  if (b == 0.0)
    None
  else
    Some(a/b)

// 2. Try测试, FIXME 测试有问题: 这里没有报异常 // scala3 除0开始返回: Infinity 
private def divide2(a:Double,b:Double):Try[Double] = 
  Try(a/b)

// 3. Either测试
private def divide3(a:Double,b:Double):Either[String,Double] = 
  if (b == 0.0) Left("出现了除0操作")
  else Right(a/b)

def test_option1 = 
  // Option测试
  var res = divide1(3.0,2.0)
  println(res)

  res = divide1(1.0, 0.0)
  println(res)
  
  // Try测试
  var res2 = divide2(2.0,0.0)
  res2 match
    case Success(value) => println(s"Success value = $value")
    case Failure(value) => println(s"Failure value = $value")
  println(res2.get > 0)

  // Either测试
  divide3(3.0,0.0) match
    case Left(value) => println(s"Left value = $value")
    case Right(value) => println(s"Right value = $value")
  divide3(3.0,1.0) match
    case Left(value) => println(s"Left value = $value")
    case Right(value) => println(s"Right value = $value")

// 模拟数据据查询并使用模式匹配进行动作分派
def test_option2 = 
  var db = Map[Int,String]()
  db += (0->"liunix")
  db += (1->"jack")
  db += (2->"asdf")

  val person = db.get(3)
  println(person.isEmpty)

  val ubox = person.getOrElse("empty")
  println("getOrElse结果:"+ubox)

  // 使用模式匹配进行动作分派
  person match {
    case Some(result) => println("有数据动作:"+result)
    case None => println("空数据动作")
  }

@main def Option1 =
  test_option1
  test_option2