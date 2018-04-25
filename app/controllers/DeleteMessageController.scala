package controllers

import javax.inject._

import models.Message
import play.api.mvc._
import play.api.i18n._
import scalikejdbc.AutoSession

@Singleton
class DeleteMessageController @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {

  def delete(messageId: Long): Action[AnyContent] = Action {
    implicit val session = AutoSession
    val result           = Message.deleteById(messageId)
    if (result > 0) Redirect(routes.GetMessagesController.index())
    else InternalServerError(Messages("DeleteMessageError"))
  }

}
