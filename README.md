# Bottery
A Java based framework for chatbots

The goal of this framework is to provide a framework for writing chat service neutral bots without the need of a cloud 
environment. Currently the projects tries to provide basic plumbing like i18n, message storage and configuration as well
as a framework for simple scripted bots. The api already allows for using nlp and machine learning but will provide 
implementations and examples in a later release.

This is still in alpha state so expect api changes

## Features
- basic framework
- i18n support with rincl
- text template support with mustache
- console connector for testing
- telegram connector
- microsoft bot framework connector


## Getting started

There is a number of examples in the examples package however a very basic echo bot on the console takes no more than:

```java
public EchoBot() {
    super(new ConsoleConnector());

    setWelcomeDialog(new Utterance() {
        @Override
        public IModel<String> getText(IActivity request, ISession session) {
            return new Model<String>(request.getText());
        }
    });
}
```

## Core Concepts
The basic concepts of the framework are 
- Bot which acts as the application and controller
- Connectors which handle receiving and sending of "messages"
- Activitis which represent messages, status updates etc.
- Dialogs which are parts of a conversation and are responsible for interpreting the users intent and creating answers
- Session which holds all user related information

All components except the session are designed to be stateless so instances can be resused among a number of chat sessions. All data related to the individual chat must be stored in the session.


Bottery borrows some of its concepts from the apache wicket web framework. 

## History
The original implementation was part of an effort to bring voice and text assistants to openhab and eclipse smarthome.
Some of the concepts for voice made it into the eclipse smarthome core and the rest eventually evolved into this framework.

## Name
The name is a combination of the words bot, battery and potter
