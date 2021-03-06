package controllers

import java.time.ZonedDateTime

import javax.inject._
import forms.MessageForm
import models.Message
import play.api.i18n._
import play.api.mvc._
import scalikejdbc.AutoSession

@Singleton
class UpdateMessageController @Inject()(val messagesApi: MessagesApi)
    extends Controller
    with I18nSupport
    with MessageControllerSupport {

  def index(messageId: Long): Action[AnyContent] = Action { implicit request =>
    val result     = Message.findById(messageId).get
    val filledForm = form.fill(MessageForm(result.id, result.body))
    Ok(views.html.edit(filledForm))
  }

  def update: Action[AnyContent] = Action { implicit request =>
    form
      .bindFromRequest()
      .fold(
        formWithErrors => BadRequest(views.html.edit(formWithErrors)), { model =>
          implicit val session = AutoSession
          val result = Message
            .updateById(model.id.get)
            .withAttributes(
              'body     -> model.body,
              'updateAt -> ZonedDateTime.now()
            )
          if (result > 0) Redirect(routes.GetMessagesController.index())
          else InternalServerError(Messages("UpdateMessageError"))
        }
      )
  }

}
