package com.codinglab.hellodocker.dockerweb.controller

import org.springframework.web.bind.annotation.{GetMapping, RestController}

@RestController
class IndexController {
  @GetMapping(Array("/index"))
  def index() = {
    "微服务技术栈POC (Service Consumer)"
  }
}
