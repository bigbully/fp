package com.alibaba.bak

/**
  * User: bigbully
  * Date: 16/7/14
  * Time: 下午11:02
  */
object ScalaClass1 {

  def factorial(n: Int): Int = {
    @annotation.tailrec
    def go(n: Int, acc: Int): Int =
      if (n <= 0) acc
      else go(n-1, n*acc)

    go(n, 1)
  }

  def formatResult(name: String, n: Int, f: Int => Int):String = {
    val msg = "The %s of %d is %d."
    msg.format(name, n, f(n))
  }

  def formatResult_[A, B](name: String, n: A, f: A => B):String = {
    val msg = "The %s of %s is %s."
    msg.format(name, n, f(n))
  }

  def formatResult1[A, B](name: String, n: A)(f: A => B):String = {
    val msg = "The %s of %s is %s."
    msg.format(name, n, f(n))
  }

  def formatResultOne[A, B](name: String, n: A):((A => B) => String) = {
    f => {
      val msg = "The %s of %s is %s."
      msg.format(name, n, f(n))
    }
  }

  def main(args:Array[String]) = {
    formatResult_("Factorial", 2, factorial)
    formatResult_("Double2String", 2.0, (d :Double)=> String.valueOf(d))
    formatResult1("Double2String", 2.0)(d => String.valueOf(d))
  }

}
