package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {
  def index       = Action { Ok(views.html.index()) }
  def demo_simple = Action { Ok(views.html.demo_simple()) }
  def demo_chat   = Action { Ok(views.html.demo_chat()) }
}