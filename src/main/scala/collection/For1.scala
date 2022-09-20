package collection

// class不能包内同名,加上private也不行
private case class Friend(id:Int,name:String)
private case class Person1(id:Int,name:String,friends:List[Friend])

def build_db:Map[Int, Person1] = 
  // 生成friends
  val friend_names = List("liunix1","liunix2","liunix3")
  val friends = friend_names.zipWithIndex.map( (item,idx)=>Friend(idx,item) )

  // 生成Persons
  val names = List("jack1","jack2","jack3")
  val persons = names.zipWithIndex.map( (item,idx)=>Person1(idx,item,friends) )
  var db = Map[Int, Person1]()
  persons.zipWithIndex.foreach((item,idx)=>db+=(idx->item))
  return db

def find_person_byId(id:Int):Option[Person1]=
  val db = build_db
  return db.get(id)

@main
def For1 = 
  val persons = (0 to 2).toList.flatMap(id=>find_person_byId(id))
  val res = for {
    person <- persons
    friend <- person.friends
  } yield (person.name, friend.name)

  println(res)
