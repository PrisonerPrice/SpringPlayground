# SpringPlayground

Here is a repo including demo apps related to SpringBoot framework

## SimpleKafka

This is a simple demo shows how to use Kafa and SpringBoot to publish and listen to topics.

Install on macOS:

`
$ brew install kafka
`

Start zooKeeper and kafka services:

`
zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties
`

`
kafka-server-start /usr/local/etc/kafka/server.properties
`

## SpringTesseract

This demo shows how to convert Pdf file to plain String text, provides 2 ways:

1) Using pdfbox to convert pdf to images as the data source for Tesseract. And then use tess4J to do the OCR and get the text. Before using Tesseract, it is also needed to be installed on local by: `$ brew install tesseract`

2) Directly using pdfbox to convert pdf to plain String text.

References: 

https://stackabuse.com/tesseract-simple-java-optical-character-recognition/

https://www.baeldung.com/pdf-conversions-java

https://www.tutorialspoint.com/how-to-read-data-from-pdf-file-and-display-on-console-in-java

## ThymeleafDemo

Thymeleaf is a modern server-side Java template engine. This demo combines the basic demo provided by spring.io and an upload file example by Atta.

References: 

https://spring.io/guides/gs/serving-web-content/

https://attacomsian.com/blog/spring-boot-thymeleaf-file-upload

## reactiveMongoTesting

This demo shows how to configure embedded mongoDB when you are testing. Because of the nature of webflux, configuring the embedded mongoDB is much different from the general non-reactive way
