# RabbitChat
Minimalistic client-server chat application based on RabbitMQ message routing.

##Purpose
This project has been part of a software engineering lecture at university. It is being used to illustrate the core functionality of a message oriented middleware, using RabbitMQ as a server-based message broker, and a few clients implementing the RabbitMQ client library. The software has intentionally been kept minimalistic and is not intended to be used in a productive environment.

##Contents of this Application
The Application consists of the following two parts:
* Client: Used for sending and receiving messages
* Server: Used for Server-based routing, based on RabbitMQ server binaries

Both parts of the application make use of the RabbitChat-common base library for message sending and receiving.

##External libraries
The following external libraries have been used:
* RabbitMQ: Used for MOM functionality on both client and server
* XStream: Used for serializing message containers as well as configuration settings in a human-friendly XML format. 
