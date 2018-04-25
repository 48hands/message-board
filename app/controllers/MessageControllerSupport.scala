package controllers

import forms.MessageForm

import play.api.mvc._
import play.api.data.Forms._
import play.api.data._

trait MessageControllerSupport { this: Controller =>

  protected val form = Form(
    mapping(
      "id"   -> optional(longNumber),
      "body" -> nonEmptyText
    )(MessageForm.apply)(MessageForm.unapply)
  )

}
