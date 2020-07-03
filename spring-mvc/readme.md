
  
## Spring - rest basic    
 This module has the basics of spring rest api and exception handling at spring level.    
    
**What is Rest api?**    
 **REST** - Representational State Transfer.    
Word looks little daunting, but the concept behind it is quite simple.    
    
Client requests a _representation_(description) of a "resource" _state_(data) stored on a server. Example : If resource is a comment stored in a db, then its "representational state" could be a simple list of values like (comment message,   timestamp, location, tags, commented by...) in a database record.    
    
In simple words, **Representational State Transfer** is a _transfer of representational states between server and client_.  In this concept, client don't even care the server's internal implementation. Client cares about is the representation     
that it gets from the server. The common format used for resource representation is JSON (JavaScript Object Notation). It's simply a name-value pair notation.    
    
Example :     

	{    
		"comment": "This a comment related to spring-boot",    
	    "commentBy": "John",    
	    "likes": 2,    
	    "tags": ["code", "java", "spring-boot"],    
	    "timeStamp": "25-06-2019'T'12:10:01.002+05:30" 
	} 

In this application we have implemented 5 rest endpoints with 5 HTTP request Methods. Here is a small info about http and it's request methods.  
  
**HTTP** : Hypertext Transfer Protocol is an application-layer protocol for transmitting hypermedia documents, such as HTML. It was designed for web browsers and web servers, but it can also be used for other purposes.  
  
HTTP defines a set of **request methods** to represent the desired action(like get, update, delete,..) to be performed for a given resource.  
  
| Http Method | Description                                                    | URI                     |  
|-------------|----------------------------------------------------------------|--------------------------------|  
| *GET* | requests a representation of the specified resource             | /users/{id}                    |  
| *POST* | submit an entity to the specified resource for specified action  | /users/register                |  
| *PUT* | update/replace the specified resource                           | /users/update/{id}             |  
| *PATCH* | partially updates the specified resource                        | /users/update/{id}             |  
| *DELETE* | deletes the specified resource                                  | /users/delete/{id}             |  
  
**Steps to run the code :**   
    1. Clone the repo  
    2. Get into the application folder (cloned location)/spring-mvc  
    3. Go to command prompt and run mvn spring-boot:run  
    4. Go to browser and check for localhost:8070  
    5. Append the above uri's to localhost:8070 and provide required data.


#### Have a happy learning :thumbsup:
Do remember me in your prayers...