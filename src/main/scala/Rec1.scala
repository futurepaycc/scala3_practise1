def sum_list(l:List[Int]):Int = 
  val res = l match {
    case hd::tl=> hd + sum_list(tl)
    case Nil => 0
  }
  return res

@main def Rec1 = 
  val res = sum_list(List(1,2,3))
  println(res)