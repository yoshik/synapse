package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.iteratee._
import play.api.libs.concurrent._
import scala.collection.mutable.ListBuffer

object Synapse extends Controller {

  object Brain{
    var listBuf = new ListBuffer[Synapse]
    private var id_count:Long=0;
    def makeId():Long={
      synchronized{
        id_count+=1;
        if(id_count==Long.MaxValue){id_count=0;}
        return id_count;
      }
    }
  }

  class Synapse (arg_tag:String)(arg_ws:PushEnumerator[String]) {

    val tag = arg_tag
    val ws = arg_ws
    val id:Long = Brain.makeId()

    //join
    Brain.listBuf.append(this)
    println("join "+"id:"+id+" tag:"+tag)

    def notify ( tag : String )( msg : String ) = {
    println("notify "+"id:"+id+" tag:"+tag+" msg:"+msg)
      Brain.listBuf.foreach { i=>
        if(i.tag == tag){
          i.ws.push(msg)
        }
      }
    }

    def remove () = {
      println("quit"+id+" tag:"+tag)
      for(i <- 0 to Brain.listBuf.length) {
        if(Brain.listBuf(i).id==id){
          Brain.listBuf.remove(i)
          //TODO slow...
        }
      }
    }

  }

  def ws(tag:String) = WebSocket.async[String] {
    request => {
      val out = Enumerator.imperative[String]()
      val Synapse = new Synapse(tag)(out)
      val in = Iteratee.foreach[String](text => {
        Synapse.notify(tag)(text)
      }).mapDone(_ => {
        Synapse.remove()
      })
      Promise.pure(in -> out)
    }
  }

}
