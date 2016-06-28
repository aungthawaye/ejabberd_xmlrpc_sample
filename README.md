Using Ejabberd 16.04 XMLRPC register command and mod_register in Java
===================

The purpose of this sample is to register new user in ejabberd server and add roster to target user's buddy list.

In this sample, I will be using Apache XMLRPC client **3.1.3** to communicate with **ejabberd 16.04**'s XML RPC. And for mod_register, I will use **Smack API 4.1.6**. (Prefer to use XML RPC)

----------

> **Important:**

> - mod_register is only for registering new user. 
> - check the source code for detail explanations.
> - for all the dependencies, please check pom.xml file.
