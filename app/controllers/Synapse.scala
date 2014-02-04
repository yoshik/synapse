package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.iteratee._
import play.api.libs.concurrent._
import scala.collection.mutable.ListBuffer

object Synapse extends Controller {

  // object PubNode{
  //   var synapseList = new ListBuffer[Synapse]
  //   private var id_count:Long=0
  //   def makeId():Long={
  //     synchronized{
  //       id_count+=1
  //       if(id_count==Long.MaxValue){id_count=0}
  //       return id_count
  //     }
  //   }
  // }

  // class Synapse (pushEnum:PushEnumerator[String]) {

  //   val tagList = new ListBuffer[String]
  //   val out = pushEnum
  //   val id:Long = PubNode.makeId()

  //   //start
  //   println("start "+"id:"+id)
  //   PubNode.synapseList.append(this)


  //   def join ( tag : String ) = {
  //     println("join "+"id:"+id+" tag:"+tag)
  //     leave(tag)
  //     tagList.append(tag)
  //     dump()
  //   }

  //   def leave ( tag : String ) = {
  //     println("leave "+"id:"+id+" tag:"+tag)
  //     for(i <- 0 to tagList.length-1) {
  //       if(tagList(i)==tag){
  //         tagList.remove(i)
  //       }
  //     }
  //     dump()
  //   }

  //   def notify ( tag : String )( msg : String ) = {
  //     println("notify "+"id:"+id+" tag:"+tag+" msg:"+msg)
  //     PubNode.synapseList.foreach { i =>
  //       i.tagList.foreach{ j=>
  //         if(j == tag){
  //           i.out.push(msg)
  //         }
  //       }
  //     }
  //   }

  //   def quit () = {
  //     println("quit id:"+id)
  //     for(i <- 0 to PubNode.synapseList.length-1) {
  //       if(PubNode.synapseList(i).id==id){
  //         PubNode.synapseList.remove(i)
  //       }
  //     }
  //   }

  //   def dump () = {
  //     println("  +---dump---")
  //     println("  +  id:"+id)
  //     for(i <- 0 to PubNode.synapseList.length-1) {
  //       if(PubNode.synapseList(i).id==id){
  //         PubNode.synapseList(i).tagList map {x=>println("  +tag:"+x)}
  //       }
  //     }
  //     println("  +----------")
  //   }
  // }

     def ws = TODO

//   def ws = WebSocket.async[String] {
//     request => {
//       val out = Enumerator.imperative[String]()
//       val synapse = new Synapse(out)
//       val in = Iteratee.foreach[String](in => {
//         val json = Json.parse(in)
//         val kind : Option[String] = (json \ "kind").asOpt[String]
//         val tag : Option[String] = (json \ "tag").asOpt[String]
//         val message : Option[String] = (json \ "message").asOpt[String]
//         kind.getOrElse("") match {
//             case "notify" => synapse.notify(tag.getOrElse(""))(message.getOrElse(""))
//             case "join" => synapse.join(tag.getOrElse(""))
//             case "leave" => synapse.quit()
//             case x:String => println("json err:"+x)
//             case _ => println("json err")
//           }
//       }).mapDone(_ => {
//         synapse.quit()
//       })
//       Promise.pure(in -> out)
//     }
//   }
// //TODO join with auth
}
