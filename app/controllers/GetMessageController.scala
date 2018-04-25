package controllers

import models.Message

import javax.inject._
import play.api.mvc._
import play.api.i18n._

@Singleton
class GetMessageController @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {

  def index(messageId: Long): Action[AnyContent] = Action { implicit request =>
    val message = Message.findById(messageId).get
    Ok(views.html.show(message))
  }
}
