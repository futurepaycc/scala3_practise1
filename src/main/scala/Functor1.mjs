// https://juejin.cn/post/6844903621507678216

/* 
一个基本的函子，实现数据串操作
*/
class Functor {

  static of(val) {
    const res = new Functor(val)
    return res
  }

  constructor(value) {
    this.value = value
  }

  map(fn) {
    const res = Functor.of(fn(this.value))
    return res
  }

}

function test1() {
  const add5 = item => item + 5
  const double = item => item * 2
  let res1 = Functor.of(5).map(add5).map(double)
  console.log(res1)
}
test1()


/* 
一个特殊函子，体现了过滤操作的高阶函数特性
*/
class Maybe {
  static of(val){
    return new Maybe(val)
  }

  constructor(value) {
    this.value = value;
  }
  map(fn) {
    return this.value ? Maybe.of(fn(this.value)) : Maybe.of(null);
  }
}

function test2(){
  const res = Maybe.of(null).map(item=>item.toUpperCase());
  console.log(res)
}

test2()