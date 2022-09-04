// 定义函数完整格式
def sum1(a:Int,b:Int):Int={
  return a + b;
}
// 定义函数简化格式? 不写返回值也行
def sum2(a:Int,b:Int)={
  a + b
}
// 定义算子格式
def sum3(a:Int,b:Int) = a + b

// 递归sum和,match用法
def rec_sum(l1:List[Int]):Int = l1 match {
    case Nil => 0;
    case h::t => h + rec_sum(t) //对尾部进行递归
}

// 直接定义可运行对象 => 不需要main方法
@main def Basic1 = {
  println(sum1(2,3))
  println(sum2(3,4))
  println(sum3(3,4))

  // range及foreach循环
  (0 to 3).foreach(println(_))

  // for 循环
  for(item <- 1.to(3))
    print("for:"+item)

  // list的初步使用
  var list1 = List(1,2,3)
  println(list1)

  list1 = 1::(2::Nil)
  println(list1)

  list1 = 1::2::Nil
  println(list1)

  list1 = (1 to 9).toList //range to list
  println(list1)

  for (item <- list1)
    printf("%d-",item)
  println

  // 产生式与过滤函数
  var list2 = for (item <- list1 if item%2 != 0) yield item //产生式
  var list3 = list1.filter(_%2 != 0)
  assert(list2.equals(list3))

  // list的递归求和
  var res = rec_sum(list1)
  println(s"结果为: $res") //模板字符串,s前注

}
