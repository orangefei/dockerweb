package com.codinglab.hellodocker.dockerweb.controller

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.web.bind.annotation._
import org.springframework.web.client.RestTemplate

@RequestMapping(Array("/web"))
@RestController
class TestController {
  private val log = LoggerFactory.getLogger(classOf[TestController])

  @Value("${docker.app.domain}")
  var appDomain: String = _

  @Autowired
  var restTemplate: RestTemplate = _

  @PutMapping(Array("/redis/username"))
  def setRedisName(@RequestParam name: String) = {
    log.info(s"设置Redis Name, name: ${name}")


    restTemplate.getForObject(s"${appDomain}/app/redis/set-username?name=${name}", classOf[String])
    log.info(s"已完成设置Redis Name.")
    true
  }

  @GetMapping(Array("/redis/username"))
  def getNameFromRedis() = {
    log.info(s"获取Redis Name.")
    val name = restTemplate.getForObject(s"${appDomain}/app/redis/username", classOf[String])
    log.info(s"已完成获取Redis Name, name=${name}")
    name
  }

  @GetMapping(Array("/merchant/{merchantId}"))
  def getMerchant(@PathVariable merchantId: String) = {
    log.info(s"查询${merchantId}商户信息.")
    val merchant = restTemplate.getForObject(s"${appDomain}/app/merchant/${merchantId}", classOf[String])

    log.info(s"查询${merchantId}商户信息,结果: ${merchant}")
    merchant
  }

  @PostMapping(Array("/merchant"))
  def newMerchant(@RequestParam merchantId: String,
                  @RequestParam merchantName: String) = {
    log.info(s"新增商户信息: merchantId=${merchantId}, merchantName=${merchantName}")
    val merchant = restTemplate.getForObject(s"${appDomain}/app/merchant?merchantId=${merchantId}&merchantName=${merchantName}", classOf[String])
    log.info(s"完成新增商户信息.")
    merchant
  }
}
