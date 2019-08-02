package com.codinglab.hellodocker.dockerweb.conf

import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.web.client.RestTemplate

@Configuration
class RestConf {
  @Bean
  def restTemplate(): RestTemplate = {
    new RestTemplate()
  }
}
